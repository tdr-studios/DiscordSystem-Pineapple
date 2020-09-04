package de.dseelp.discordsystem.core.impl.modules;

import de.dseelp.discordsystem.api.modules.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class NewModuleLoader implements ModuleLoader {

    private final Class<?> moduleClass;

    public NewModuleLoader(Class<?> moduleClass) {
        this.moduleClass = moduleClass;
    }

    @SneakyThrows
    @Override
    public ModuleInfo loadModuleInfo(File file) {
        JarFile jarFile = new JarFile(file);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry != null && entry.getName().endsWith(".class")) {
                try {
                    ClassFile classFile = new ClassFile(new DataInputStream(jarFile.getInputStream(entry)));
                    if (!classFile.getSuperclass().equals(moduleClass.getName())) continue;
                    AnnotationsAttribute attribute = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.visibleTag);
                    if (attribute == null) return null;
                    Annotation annotation = Arrays.stream(
                            ((AnnotationsAttribute)
                                    classFile.getAttribute(AnnotationsAttribute.visibleTag))
                                    .getAnnotations())
                            .filter(annotation1 -> annotation1.getTypeName().equals(NewModule.class.getName()))
                            .findFirst()
                            .orElse(null);
                    if (annotation == null) continue;
                    ModuleInfo meta = ModuleInfo.from(annotation);
                    assert meta != null;
                    meta.setMainClass(classFile.getName());
                    meta.setFile(file);
                    return meta;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void load(ModuleClassLoader loader) {
        Class<?> moduleClass = null;
        try {
            moduleClass = loader.loadClass(loader.getInfo().getMainClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object moduleObject = createModule(moduleClass);
        if (moduleObject == null){
            System.err.println("Failed to load module: "+loader.getInfo().getName());
        }else {
            Module module = (Module) moduleObject;
            module.onLoad();
        }
    }

    private Object createModule(Class<?> clazz) {
        Constructor<?> constructor = searchForConstructor(clazz);
        if (constructor == null) {
            System.err.println("No compatible Constructor found in " + clazz.getName());
            return null;
        }else {
            constructor.setAccessible(true);
            try {
                return constructor.newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private Constructor searchForConstructor(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        return null;
    }

    @Override
    public void enable(Module module) {
        module.setEnabled(true);
    }

    @Override
    public void disable(Module module) {
        module.setEnabled(false);
    }

    @Override
    public void unload(ModuleClassLoader loader) {
        try {
            if (loader.getModule() != null) {
                loader.getModule().setEnabled(false);
            }
            loader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
