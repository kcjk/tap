package com.nemosw.spigot.tap.event;

import com.google.common.reflect.TypeToken;
import com.github.noonmaru.tools.asm.ClassDefiner;
import org.bukkit.event.Event;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import static org.objectweb.asm.Opcodes.*;

public final class ASMEntityEventExecutor
{

    private static final DefaultExtractor[] DEFAULT_EXTRACTORS;

    private static final String EXECUTOR_CONST_DESC = "(L" + Type.getInternalName(EntityExtractor.class) + ";)V";

    private static final String SUPER_NAME = Type.getInternalName(EntityEventExecutor.class);

    private static final String SUPER_CONST_DESC = Type.getConstructorDescriptor(EntityEventExecutor.class.getConstructors()[0]);

    private static final String EXECUTE_DESC;

    private static final String PRIORITY_NAME = Type.getInternalName(EntityEventPriority.class);

    private static final String PRIORITY_SIGN = 'L' + PRIORITY_NAME + ';';

    private static final Map<Method, EntityEventExecutor> CACHE = new HashMap<>();

    private static final Map<Class<?>, EntityExtractor<?>> EXTRACTORS_BY_CLASS = new HashMap<>();

    private static int EXECUTOR_IDs;

    private static class DefaultExtractor
    {
        final Class<?> eventClass;

        final Class<?> extractorClass;

        @SuppressWarnings({"unchecked"})
        DefaultExtractor(Class<?> extractorClass)
        {
            this.eventClass = (Class<? extends Event>) ((ParameterizedType) extractorClass.getGenericSuperclass()).getActualTypeArguments()[0];
            this.extractorClass = extractorClass;
        }
    }

    static
    {
        try
        {
            EXECUTE_DESC = Type.getMethodDescriptor(EntityEventExecutor.class.getMethod("execute", EntityListener.class, Event.class));

        }
        catch (Exception e)
        {
            throw new AssertionError(e);
        }

        Class<?>[] classes = EntityExtractor.class.getDeclaredClasses();
        List<DefaultExtractor> defaultExtractors = new ArrayList<>(classes.length);

        for (Class<?> clazz : classes)
        {
            if (EntityExtractor.class.isAssignableFrom(clazz) && !Modifier.isPublic(clazz.getModifiers()))
                defaultExtractors.add(new DefaultExtractor(clazz));
        }

        DEFAULT_EXTRACTORS = defaultExtractors.toArray(new DefaultExtractor[0]);
    }

    private static Class<?> getDefaultExtractorClass(Class<?> clazz)
    {
        for (DefaultExtractor extractor : DEFAULT_EXTRACTORS)
        {
            if (extractor.eventClass.isAssignableFrom(clazz))
                return extractor.extractorClass;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    static EntityEventExecutor[] createExecutors(Class<? extends EntityListener> clazz)
    {
        int mod = clazz.getModifiers();

        if (!Modifier.isPublic(mod))
            throw new IllegalArgumentException("EntityEventListener's modifier must be public");

        Method[] methods = clazz.getMethods();

        class ListenerMaterial
        {
            final Method method;
            final Class<?> handlerClass;
            final EntityExtractor<? extends Event> entityExtractor;
            final EntityEventPriority priority;
            final boolean ignoreCancelled;

            ListenerMaterial(Method method, Class<?> handlerClass, EntityExtractor<? extends Event> entityExtractor, EntityEventPriority priority, boolean ignoreCancelled)
            {
                this.method = method;
                this.handlerClass = handlerClass;
                this.entityExtractor = entityExtractor;
                this.priority = priority;
                this.ignoreCancelled = ignoreCancelled;
            }
        }

        ArrayList<ListenerMaterial> materials = new ArrayList<>(methods.length);
        Set<? extends Class<?>> supers = TypeToken.of(clazz).getTypes().rawTypes();

        for (Method method : methods)
        {
            for (Class<?> superClass : supers)
            {
                try
                {
                    Method real = superClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    EntityEventHandler entityEventHandler = real.getAnnotation(EntityEventHandler.class);

                    if (entityEventHandler != null)
                    {
                        Class<?>[] parameterTypes = real.getParameterTypes();

                        if (parameterTypes.length != 1)
                            throw new IllegalArgumentException("@EntityEventHandler methods must require a single argument: " + method);

                        if (method.getReturnType() != void.class)
                            throw new IllegalArgumentException("@EntityEventHandler methods must return 'void': " + method);

                        Class<?> eventClass = parameterTypes[0];

                        if (!Event.class.isAssignableFrom(eventClass))
                            throw new IllegalArgumentException("'" + eventClass.getName() + "' is not event class : " + method);

                        Class<?> handlerClass;

                        try
                        {
                            handlerClass = eventClass.getMethod("getHandlerList").getDeclaringClass();
                        }
                        catch (NoSuchMethodException e)
                        {
                            throw new IllegalArgumentException('\'' + eventClass.getName() + "' has no HandlerList: " + method);
                        }

                        Class<?> extractorClass = entityEventHandler.extractor();

                        if (extractorClass == EntityExtractor.class)
                        {
                            extractorClass = getDefaultExtractorClass(eventClass);

                            if (extractorClass == null)
                                throw new NullPointerException("Not found default EntityExtractor for " + eventClass);
                        }

                        EntityExtractor<? extends Event> entityExtractor = EXTRACTORS_BY_CLASS.get(extractorClass);

                        if (entityExtractor == null)
                        {
                            try
                            {
                                entityExtractor = (EntityExtractor<? extends Event>) extractorClass.newInstance();
                            }
                            catch (Exception e)
                            {
                                throw new AssertionError(e);
                            }

                            EXTRACTORS_BY_CLASS.put(extractorClass, entityExtractor);
                        }

                        materials.add(new ListenerMaterial(method, handlerClass, entityExtractor, entityEventHandler.priority(), entityEventHandler.ignoreCancelled()));
                    }
                }
                catch (NoSuchMethodException | SecurityException ignored)
                {}
            }
        }

        int size = materials.size();
        EntityEventExecutor[] executors = new EntityEventExecutor[size];

        for (int i = 0; i < size; i++)
        {
            ListenerMaterial material = materials.get(i);

            executors[i] = createListener(material.method, material.handlerClass, material.entityExtractor, material.priority, material.ignoreCancelled);
        }

        return executors;
    }

    private static EntityEventExecutor createListener(Method method, Class<?> handlerClass, EntityExtractor<?> entityExtractor, EntityEventPriority priority, boolean ignoreCancelled)
    {
        if (CACHE.containsKey(method))
            return CACHE.get(method);

        try
        {
            Class<?> declaredClass = method.getDeclaringClass();
            Class<?> eventClass = method.getParameterTypes()[0];
            String name = EntityEventExecutor.class.getName() + "_" + EXECUTOR_IDs++;
            String desc = name.replace('.', '/');
            String instType = Type.getInternalName(declaredClass);
            String eventType = Type.getInternalName(eventClass);

            ClassWriter cw = new ClassWriter(0);
            MethodVisitor mv;

            cw.visit(V1_6, ACC_PUBLIC | ACC_SUPER, desc, null, SUPER_NAME, null);
            cw.visitSource(".dynamic", null);

            {
                mv = cw.visitMethod(ACC_PUBLIC, "<init>", EXECUTOR_CONST_DESC, null, null);
                mv.visitCode();
                mv.visitVarInsn(ALOAD, 0);
                mv.visitLdcInsn(Type.getType(eventClass));
                mv.visitLdcInsn(Type.getType(handlerClass));
                mv.visitVarInsn(ALOAD, 1);
                mv.visitFieldInsn(GETSTATIC, PRIORITY_NAME, priority.name(), PRIORITY_SIGN);
                mv.visitInsn(ignoreCancelled ? ICONST_1 : ICONST_0);
                mv.visitMethodInsn(INVOKESPECIAL, SUPER_NAME, "<init>", SUPER_CONST_DESC, false);
                mv.visitInsn(RETURN);
                mv.visitMaxs(6, 2);
                mv.visitEnd();
            }

            {
                mv = cw.visitMethod(ACC_PUBLIC, "execute", EXECUTE_DESC, null, null);
                mv.visitCode();
                mv.visitVarInsn(ALOAD, 1);
                mv.visitTypeInsn(CHECKCAST, instType);
                mv.visitVarInsn(ALOAD, 2);
                mv.visitTypeInsn(CHECKCAST, eventType);
                mv.visitMethodInsn(INVOKEVIRTUAL, instType, method.getName(), Type.getMethodDescriptor(method), false);
                mv.visitInsn(RETURN);
                mv.visitMaxs(2, 3);
                mv.visitEnd();
            }

            EntityEventExecutor executor = (EntityEventExecutor) ClassDefiner.defineClass(name, cw.toByteArray(), declaredClass.getClassLoader()).getConstructor(EntityExtractor.class)
                    .newInstance(entityExtractor);

            CACHE.put(method, executor);

            return executor;
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            throw new AssertionError(t);
        }
    }

}
