package de.dseelp.discordsystem.version;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;
import de.dseelp.discordsystem.utils.GsonUtils;
import de.dseelp.discordsystem.version.module.DownloadableModule;
import de.dseelp.discordsystem.version.module.DownloadableModuleVersion;
import de.dseelp.discordsystem.version.repository.ModuleRepository;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

public class Test {
    public static void main(String[] args) throws Exception {
        //DownloadableModule test = new DownloadableModule();
        //test.addVersion("0.1", "0.2-ALPHA", "1.0", System.currentTimeMillis(), "");
        //System.out.println(GsonUtils.getPretty().toJson(test));
        ModuleRepository repository = ModuleRepository.findRepository(new WebHelper("http://localhost/repos/modules/"));
        for (DownloadableModule module : repository.getModules()) {
            System.out.println("Name: "+module.getName());
            System.out.println("Versions:");
            for (DownloadableModuleVersion version : module.getVersions()) {
                System.out.println(version.getVersion().toString());
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("");
            }
        }
    }
}
