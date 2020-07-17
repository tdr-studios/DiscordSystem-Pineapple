package de.dseelp.discordsystem.api.modules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NewModule {
    String name();
    String[] authors();
    String description() default "";
    String version();

    String[] dependencies() default {};
    String[] loadBefore() default {};
}
