package de.malik.mylibrary.managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.malik.mylibrary.general.UtilsLib;
import de.malik.mylibrary.utils.UtilsLibFileParser;
import de.malik.mylibrary.utils.UtilsLibFilePrinter;
import de.malik.mylibrary.utils.UtilsLibFileReader;

public class FileManager {

    public static final String[] RESERVED_CHARS = new String[] {
            "|", "\\\\", "?", "'", "*", "<", "\\", "\"", ":", ">", "+", "[", "]", "/"
    };
    public static final String RESERVED_CHARS_STRING = "|\\\\?*<\\\":>+[]/'";

    public static final Map<String, File> CREATED_FILES = new HashMap<>();
    public static final Map<String, File> CREATED_FOLDERS = new HashMap<>();

    public static File createFile(@NonNull String fileName, @NonNull File folder) throws IOException {
        if (UtilsLib.containsString(fileName, RESERVED_CHARS) || UtilsLib.containsString(folder.getName(), RESERVED_CHARS)) {
            throw new IllegalArgumentException("Ether the fileName or the folder name can not contain any of the chars " + RESERVED_CHARS_STRING);
        }
        File file = new File(folder, fileName);
        file.createNewFile();
        CREATED_FILES.put(fileName, file);
        return file;
    }

    public static File createFolder(@NonNull String folderName, @Nullable String folderPath) {
        if (UtilsLib.containsString(folderName, RESERVED_CHARS)) {
            throw new IllegalArgumentException("The folderName can not contain any of the chars " + RESERVED_CHARS_STRING);
        }
        File folder = new File(folderPath, folderName);
        if (!folder.mkdir()) {
            folder.mkdir();
        }
        CREATED_FOLDERS.put(folderName, folder);
        return folder;
    }

    public static UtilsLibFileReader getReader() {
        return new UtilsLibFileReader();
    }

    public static UtilsLibFilePrinter getPrinter() {
        return new UtilsLibFilePrinter();
    }

    public static UtilsLibFileParser getParser() {
        return new UtilsLibFileParser();
    }
}
