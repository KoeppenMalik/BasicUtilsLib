package de.malik.mylibrary.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UtilsLibFilePrinter {

    /**
     * prints all elements of <code>records</code> into <code>file</code>. Uses
     * <code>append</code> to decide if the already existing data will be overwritten or not
     * @param records all the Strings which will be printed into the file
     * @param file the file where the data will be printed into
     * @param append if true, the existing data will not get overwritten, otherwise it will
     * @throws IOException if the given file is a folder or if the file can not be found
     */
    public void print(File file, boolean append, String... records) throws IOException {
        PrintWriter writer = createWriter(file, append);
        for (String record : records) {
            writer.println(record);
        }
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }

    /**
     * prints all elements of <code>records</code> into <code>file</code>. Uses
     * <code>append</code> to decide if the already existing data will be overwritten or not
     * @param records an ArrayList containing all the String that will be printed into the file
     * @param file the file where the data will be printed into
     * @param append if true, the existing data will not get overwritten, otherwise it will
     * @throws IOException if the given file is a folder or if the file can not be found
     */
    public void print(File file, boolean append, ArrayList<String> records) throws IOException {
        PrintWriter writer = createWriter(file, append);
        for (String record : records) {
            writer.println(record);
        }
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }

    /**
     * creates a new instance of the <code>PrintWriter</code> class
     * @param file the file where the printer will print into
     * @param append if true, the existing data will not get overwritten, otherwise it will
     * @return a new instance of <code>PrintWriter</code> class
     * @throws IOException if the given file is a folder or if the file can not be found
     */
    private PrintWriter createWriter(File file, boolean append) throws IOException {
        FileWriter fWriter = new FileWriter(file, append);
        return new PrintWriter(new BufferedWriter(fWriter));
    }
}
