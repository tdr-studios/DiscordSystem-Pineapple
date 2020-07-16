package de.dseelp.discordsystem.core.impl.modules;

import de.dseelp.discordsystem.api.modules.Module;
import de.dseelp.discordsystem.api.modules.ModuleInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URLClassLoader;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleData {
    private Module module;
    private ModuleInfo moduleInfo;
    private URLClassLoader classLoader;
}
