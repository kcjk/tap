package com.nemosw.spigot.tap.attach;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AgentLoader
{
    private static AgentLoaderInterface agentLoader;

    interface AgentLoaderInterface
    {
        void loadAgent(File agentFile);
    }

    public static void attachAgentToJVM(ClassLoader loader, File agentFile)
    {
        getAgentLoader().loadAgent(agentFile);
    }

    private synchronized static AgentLoaderInterface getAgentLoader()
    {
        if (agentLoader != null)
            return agentLoader;

        Class<?> agentLoaderClass;

        try
        {
            Class.forName("com.sun.tools.attach.VirtualMachine"); //default jdk or hotspot agent
            agentLoaderClass = Class.forName("com.nemosw.spigot.tap.attach.agent.AgentLoaderHotSpot");
        }
        catch (Exception ex)
        {
            Platform platform = Platform.getPlatform();
            ClassLoader systemLoader = ClassLoader.getSystemClassLoader();

            String path = Tools.TOOLS_DIR + Tools.ATTACH_DIR + "classes/";
            String platformPath = path + platform.getDir();

            List<String> shaded = new ArrayList<>(14);
            shaded.addAll(Stream.of(
                    "AttachProvider",
                    "VirtualMachine",
                    "VirtualMachineDescriptor",
                    "HotSpotVirtualMachine",
                    "HotSpotAttachProvider",
                    "HotSpotAttachProvider$HotSpotVirtualMachineDescriptor",
                    "AgentInitializationException",
                    "AgentLoadException",
                    "AttachNotSupportedException",
                    "AttachOperationFailedException",
                    "AttachPermission"
            ).map(s -> path + s).collect(Collectors.toList()));
            shaded.addAll(platform.getClasses().stream().map(s -> platformPath + s).collect(Collectors.toList()));
            shaded.add("com/nemosw/spigot/tap/attach/agent/AttachProviderPlaceHolder");

            for (String s : shaded)
            {
                try
                {
                    Tools.defineClass(systemLoader, AgentLoader.class.getResourceAsStream("/" + s + ".class"));
                }
                catch (Exception e)
                {
                    throw new RuntimeException("Error defining: " + s, e);
                }
            }

            try
            {
                agentLoaderClass = Tools.defineClass(systemLoader, AgentLoader.class.getResourceAsStream("/com/nemosw/spigot/tap/attach/agent/AgentLoaderHotSpot.class"));
            }
            catch (Exception e)
            {
                throw new RuntimeException("Error loading AgentLoader implementation", e);
            }
        }

        try
        {
            Object agentLoaderObject = agentLoaderClass.newInstance();

            agentLoader = agentFile -> {
                try
                {
                    final Method loadAgentMethod = agentLoaderObject.getClass().getMethod("attachAgentToJVM", File.class);
                    loadAgentMethod.invoke(agentLoaderObject, agentFile);
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            };
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error getting agent loader implementation", e);
        }

        return agentLoader;
    }

    public static File generateAgentJar(File file, ClassLoader classLoader, String agentClass, List<String> resources) throws IOException
    {
        file.delete();
        file.deleteOnExit();

        Manifest manifest = new Manifest();
        Attributes mainAttributes = manifest.getMainAttributes();

        mainAttributes.put(Name.MANIFEST_VERSION, "1.0");
        mainAttributes.put(new Name("Agent-Class"), agentClass);
        mainAttributes.put(new Name("Can-Retransform-Classes"), "true");
        mainAttributes.put(new Name("Can-Redefine-Classes"), "true");

        try (JarOutputStream jos = new JarOutputStream(new FileOutputStream(file), manifest))
        {
            String agentPath = unqualify(agentClass);
            jos.putNextEntry(new JarEntry(agentPath));
            jos.write(Tools.getBytesFromStream(classLoader.getResourceAsStream(agentPath)));
            jos.closeEntry();

            for (String name : resources)
            {
                jos.putNextEntry(new JarEntry(name));
                jos.write(Tools.getBytesFromStream(classLoader.getResourceAsStream(name)));
                jos.closeEntry();
            }

            jos.flush();
        }

        return file;
    }

    private static String unqualify(String className)
    {
        return className.replace('.', '/') + ".class";
    }

}
