package de.dseelp.discordsystem.core.spring.components;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.modules.ModuleClassLoader;
import de.dseelp.discordsystem.api.modules.ModuleInfo;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import de.dseelp.discordsystem.version.WebHelper;
import de.dseelp.discordsystem.version.module.DownloadableModule;
import de.dseelp.discordsystem.version.module.DownloadableModuleVersion;
import de.dseelp.discordsystem.version.module.ModuleType;
import de.dseelp.discordsystem.version.repository.ModuleRepository;
import de.dseelp.discordsystem.version.repository.ModuleRepositoryType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Component("ModuleDownloadService")
public class ModuleDownloadService {
    private List<ModuleRepository> repositories;

    private LogSystem logSystem;

    public ModuleDownloadService() {
        LoggerRegistry.register("updater", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "Updater"));
        logSystem = LoggerRegistry.get("updater");
    }

    public Collection<DownloadableModule> getModules() {
        List<DownloadableModule> modules = new ArrayList<>();
        for (ModuleRepository repository : repositories) {
            modules.addAll(repository.getModules());
        }
        return Collections.unmodifiableCollection(modules);
    }

    public Collection<ModuleRepository> getRepositories() {
        return Collections.unmodifiableCollection(repositories);
    }
    public DownloadableModule[] findModules(String moduleName) {
        moduleName = moduleName.toLowerCase();
        List<DownloadableModule> modules = new ArrayList<>();
        for (ModuleRepository repository : repositories) {
            for (DownloadableModule module : repository.getModules()) {
                if (module.getName().toLowerCase().equals(moduleName)) {
                    logSystem.debug("Found module "+moduleName+" in "+repository.getName());
                    modules.add(module);
                }
            }
        }
        return modules.toArray(new DownloadableModule[modules.size()]);
    }

    private boolean isNeeded(ModuleRepository repository, DownloadableModule module) {
        if (repository.getType() == ModuleRepositoryType.NEEDED) {
            if (module.getType() == ModuleType.UNDEFINED || module.getType() == ModuleType.NEEDED) {
                return true;
            }
            return false;
        }
        return module.getType() == ModuleType.NEEDED;
    }

    private boolean isOptional(ModuleRepository repository, DownloadableModule module) {
        return !isNeeded(repository, module);
    }

    @PostConstruct
    public void loadRepositories() {
        logSystem.write("Loading Module Repositories from Config...");
        if (repositories != null) repositories.clear();
        else repositories = new ArrayList<>();
        String[] urls = BotConfig.getModuleRepositoryUrls();
        for (String url : urls) {
            try {
                ModuleRepository repository = ModuleRepository.findRepository(new WebHelper(url));
                repositories.add(repository);
                logSystem.write("Loaded repository "+repository.getName()+" from "+url+"!");
            } catch (Exception e) {
                e.printStackTrace(logSystem.getError());
            }
        }
        if (repositories.size() == 1)
            logSystem.write("Loaded "+repositories.size()+" Repository!");
        else
            logSystem.write("Loaded "+repositories.size()+" Repositories!");
    }

    public DownloadableModule findModule(String[] authors, DownloadableModule[] modules) {
        for (DownloadableModule module : modules) {
            if (Arrays.equals(module.getAuthors(), authors)) {
                return module;
            }else {
                for (String author : authors) {
                    author = author.toLowerCase();
                    for (String moduleAuthor : module.getAuthors()) {
                        if (moduleAuthor.toLowerCase().equals(author)) {
                            return module;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void checkModules(ModuleService.CustomModuleManager customModuleManager) {
        final DefaultArtifactVersion coreVersion = new DefaultArtifactVersion(Discord.getVersion());
        for (ModuleClassLoader classLoader : customModuleManager.getClassLoaders()) {
            DownloadableModule module = findModule(classLoader.getInfo().getAuthors(), findModules(classLoader.getInfo().getName()));
            if (module == null) {
                continue;
            }
            module.setInstalledVersion(new DefaultArtifactVersion(classLoader.getInfo().getVersion()));
            module.setCoreVersion(coreVersion);
            if (!module.isUptoDate()) {
                logSystem.warning("Module "+classLoader.getInfo().getName()+" is not up to date!");
                DownloadableModuleVersion latestSupported = module.getLatestSupported();
                logSystem.write("Found new version v"+latestSupported.toString()+" from module "+module.getName());
                if (!BotConfig.isAutoUpdates()) {
                    logSystem.warning("Please enable auto updates for updating modules. Or update manually!");
                    logSystem.write("You can download the new Version here: "+latestSupported.getUrl());
                    continue;
                }
                ModuleInfo info = classLoader.getInfo();
                customModuleManager.unload(info.getFile());
                File newFile = null;
                try {
                    System.gc();
                    FileUtils.forceDelete(info.getFile());
                    URL url = new URL(latestSupported.getUrl());
                    newFile = info.getFile();
                    FileUtils.copyURLToFile(url, newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cachedFiles.add(newFile);
            }
        }
    }

    private List<File> cachedFiles = new ArrayList<>();

    public void loadCached(ModuleService.CustomModuleManager customModuleManager) {
        for (File cachedFile : cachedFiles) {
            customModuleManager.load(cachedFile);
            customModuleManager.finalLoad(customModuleManager.getModuleClassLoader(cachedFile));
        }
    }

    public void download(ModuleService.CustomModuleManager customModuleManager) {
        for (ModuleRepository repository : repositories) {
            for (DownloadableModule module : repository.getModules()) {
                if (isNeeded(repository, module)) {
                    ModuleClassLoader classLoader = customModuleManager.getModuleClassLoader(module.getName());
                    if (classLoader == null) {
                        if (!downloadModule(module, customModuleManager)) {
                            logSystem.error("A File with the name "+FilenameUtils.getName(module.getLatestSupported().getUrl())+" already exists!");
                        }
                    }else {
                        if (!Arrays.equals(module.getAuthors(), classLoader.getInfo().getAuthors())) {
                            if (!downloadModule(module, customModuleManager)) {
                                logSystem.error("A File with the name "+FilenameUtils.getName(module.getLatestSupported().getUrl())+" already exists!");
                            }
                        }else {
                            for (String author : classLoader.getInfo().getAuthors()) {
                                author = author.toLowerCase();
                                boolean find = false;
                                for (String moduleAuthor : module.getAuthors()) {
                                    if (moduleAuthor.toLowerCase().equals(author)) {
                                        find = true;
                                    }
                                }
                                if (!find) {
                                    if (!downloadModule(module, customModuleManager)) {
                                        logSystem.error("A File with the name "+FilenameUtils.getName(module.getLatestSupported().getUrl())+" already exists!");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean downloadModule(DownloadableModule module, ModuleService.CustomModuleManager customModuleManager) {
        return downloadModule(module, customModuleManager, false);
    }

    public boolean downloadModule(DownloadableModule module, ModuleService.CustomModuleManager customModuleManager, boolean override) {
        try {
            URL url = new URL(module.getLatestSupported().getUrl());
            File newFile = new File("modules", FilenameUtils.getName(url.getFile()));
            if (newFile.exists()) {
                if (!override) {
                    return false;
                }
            }
            FileUtils.copyURLToFile(url, newFile);
            cachedFiles.add(newFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
