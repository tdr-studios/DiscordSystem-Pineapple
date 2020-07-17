package de.dseelp.discordsystem.core.impl.modules;

import de.dseelp.discordsystem.api.modules.Module;
import de.dseelp.discordsystem.api.modules.ModuleClassLoader;
import de.dseelp.discordsystem.api.modules.ModuleInfo;
import de.dseelp.discordsystem.api.modules.ModuleLoader;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.jar.JarFile;

public class SimpleModuleLoader implements ModuleLoader {

    public SimpleModuleLoader enableDebug() {
        test = true;
        return this;
    }

    public SimpleModuleLoader disableDebug() {
        test = false;
        return this;
    }

    private boolean test = false;

    private List<SimpleData> dataList = new ArrayList<>();

    private void debug(String s) {
        if (test) System.out.println(s);
    }

    @SneakyThrows
    public void load(ModuleInfo info) {
        debug("Loading File: "+info.getFile().getName());
        if (info.getFile().getName().endsWith(".jar")) {
            debug("Compatible File: "+info.getFile().getName());
            ModuleInfo meta = getModuleInfo(new JarFile(info.getFile()));
            //meta.setFile(file);
            //debug("Readed moduleInfo: "+file.getName()+" ModuleInfo: "+meta);
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{new URL("jar:" + info.getFile().toURI().toURL() + "!/")}
            );
            debug("Classloader for File "+info.getFile().getName()+" initialized!");
            SimpleData data = new SimpleData();
            data.setModuleInfo(meta);
            data.setClassLoader(classLoader);
            Class<?> moduleClass = classLoader.loadClass(meta.getMainClass());
            //classLoader.
            debug("Loaded Main!");
            Module module = null;
            for (Constructor<?> constructor : moduleClass.getConstructors()) {
                debug("Finded Constructor: "+constructor);
                if (constructor.getParameterCount() == 0) {
                    debug("Using Constructor "+constructor);
                    constructor.setAccessible(true);
                    Object object = constructor.newInstance();
                    debug("Created instance from Constructor!");
                    if (object instanceof Module) {
                        debug("Super Matches");
                        module = (Module) object;
                        break;
                    }
                }
            }
            assert module != null;
            debug("Asserted != null");
            data.setModule(module);
            debug("Data setted module");
            debug("Configured module!");
            debug("Loaded Module!");
            module.onLoad();
            dataList.add(data);
        }
    }

    private ModuleInfo getModuleInfo(JarFile jarFile) {
        return null;
    }

    @Override
    public ModuleInfo loadModuleInfo(File file) {
        return null;
    }

    @Override
    public void load(ModuleClassLoader info) {

    }

    public void enable(Module module) {
        System.out.println("Enabling Module!");
        module.onEnable();
    }

    public void disable(Module module) {
        module.onDisable();
    }

    @Override
    public void unload(ModuleClassLoader info) {

    }

    public void unload(File file) {
        SimpleData systemData = null;
        for (SimpleData data : dataList) {
            Module module = data.getModule();
            if (module.getFile().equals(file)) {
                systemData = data;
            }
        }
        assert systemData != null;
        try {
            systemData.getClassLoader().close();
            dataList.remove(systemData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Module> getModules() {
        List<Module> modules = new ArrayList<>();
        dataList.forEach(data -> modules.add(data.getModule()));
        return modules;
    }
}
