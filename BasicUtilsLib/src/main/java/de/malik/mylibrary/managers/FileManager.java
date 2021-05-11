package de.malik.mylibrary.managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.malik.mylibrary.general.UtilsLib;
import de.malik.mylibrary.utils.UtilsLibFilePrinter;
import de.malik.mylibrary.utils.UtilsLibFileReader;

public class FileManager {

    /**
     * an Array containing all Strings that are not allowed in file or folder names
     */
    public static final String[] RESERVED_CHARS = new String[] {
            "|", "\\\\", "?", "'", "*", "<", "\\", "\"", ":", ">", "+", "[", "]", "/"
    };

    /**
     * one single String containing all Strings that are not allowed in file or folder names
     */
    public static final String RESERVED_CHARS_STRING = "|\\\\?*<\\\":>+[]/'";

    /**
     * a HashMap containing all created files. They can be accessed by using the whole file name
     */
    public static final Map<String, File> CREATED_FILES = new HashMap<>();

    /**
     * a HashMap containing all created folders. They can be accessed by using the whole folder name
     */
    public static final Map<String, File> CREATED_FOLDERS = new HashMap<>();

    /**
     * creates a new file named <code>fileName</code> in <code>folder</code>. The created file will
     * be added to the HashMap <code>CREATED_FILES</code>
     * @param fileName the name which the new created file will have
     * @param folder the folder in which the file will be created
     * @return the file which just have been created
     * @throws IOException if an I/O error occurred
     */
    public static File createFile(@NonNull String fileName, @NonNull File folder) throws IOException {
        if (UtilsLib.containsString(fileName, RESERVED_CHARS) || UtilsLib.containsString(folder.getName(), RESERVED_CHARS)) {
            throw new IllegalArgumentException("Ether the fileName or the folder name can not contain any of the chars " + RESERVED_CHARS_STRING);
        }
        File file = new File(folder, fileName);
        file.createNewFile();
        CREATED_FILES.put(fileName, file);
        return file;
    }

    /**
     * creates a folder named <code>folderName</code> at the path <code>folderPath</code>. The
     * created folder will be added to the HashMap <code>CREATED_FOLDERS</code>
     * @param folderName the name which the new created folder will have
     * @param folderPath the path at which the folder will be created
     * @return the folder which just have been created
     */
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

    /**
     * creates a new instance of the UtilsLibFileReader class and returns it
     * @return a new instance of UtilsLibFileReader class
     */
    public static UtilsLibFileReader getReader() {
        return new UtilsLibFileReader();
    }

    /**
     * creates a new instance of the UtilsLibFilePrinter class and returns it
     * @return a new instance of UtilsLibFilePrinter class
     */
    public static UtilsLibFilePrinter getPrinter() {
        return new UtilsLibFilePrinter();
    }
}
