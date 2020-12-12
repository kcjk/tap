package com.nemosw.spigot.tap.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityEventHandler
{

    Class<?> extractor() default EntityExtractor.class;

    EntityEventPriority priority() default EntityEventPriority.NORMAL;

    boolean ignoreCancelled() default false;

}
