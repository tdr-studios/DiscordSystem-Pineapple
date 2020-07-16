package de.dseelp.discordsystem.api.modules;

import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ModuleInfo {
    ModuleInfo(String name, String[] authors, String version, String description, String[] dependencies, String[] loadBefore) {
        this.name = name;
        this.authors = authors;
        this.version = version;
        this.description = description;
        this.dependencies = dependencies;
        this.loadBefore = loadBefore;
    }
    private final String name;
    private final String description;
    private final String version;
    private final String[] authors;

    private final String[] dependencies;
    private final String[] loadBefore;

    private String mainClass;

    private File file;

    public static ModuleInfo from(NewModule annotation) {
        return new ModuleInfo(annotation.name(), annotation.authors(), annotation.version(), annotation.description(), annotation.dependencies(), annotation.loadBefore());
    }

    public static ModuleInfo from(Annotation annotation) {
        if (annotation == null) return null;
        String name = ((StringMemberValue) annotation.getMemberValue("name")).getValue();
        String version = ((StringMemberValue) annotation.getMemberValue("version")).getValue();
        StringMemberValue descriptionValue = ((StringMemberValue) annotation.getMemberValue("description"));
        String description;
        if (descriptionValue != null )description = ((StringMemberValue) annotation.getMemberValue("description")).getValue();
        else description = "";
        ArrayMemberValue authorValue = (ArrayMemberValue) annotation.getMemberValue("authors");
        ArrayMemberValue dependencyValue = (ArrayMemberValue) annotation.getMemberValue("dependencies");
        ArrayMemberValue loadBeforeValue = (ArrayMemberValue) annotation.getMemberValue("loadBefore");
        String[] authors = toStringArray(authorValue);
        String[] dependencies = toStringArray(authorValue);
        String[] loadBefore = toStringArray(authorValue);
        return new ModuleInfo(name, authors, version, description, dependencies, loadBefore);
    }

    private static String[] toStringArray(ArrayMemberValue values) {
        if (values == null) return new String[]{};
        List<String> strings = new ArrayList<>();
        for (MemberValue value : values.getValue()) {
            if (value instanceof StringMemberValue) {
                strings.add(((StringMemberValue) value).getValue());
            }
        }
        return strings.toArray(new String[strings.size()]);
    }



    public void setFile(File file) {
        if (this.file == null) this.file = file;
    }

    public void setMainClass(String mainClass) {
        if (this.mainClass == null) this.mainClass = mainClass;
    }
}
