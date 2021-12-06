package nl.bd.aoc.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileUtils {
    public static List<String> readFromFile(String filename)
    {
        try {
            String text = IOUtils.toString(FileUtils.class.getClassLoader().getResourceAsStream(filename),
                    "UTF-8");

            return new ArrayList<>(Arrays.asList(text.split("\n")));
        } catch (IOException e) {
            System.err.println("Exception while reading input file: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public static String fileContent(String filename)
    {
        try {
            String text = IOUtils.toString(FileUtils.class.getClassLoader().getResourceAsStream(filename),
                    "UTF-8");

            return text;
        } catch (IOException e) {
            System.err.println("Exception while reading input file: " + e.getMessage());
        }

        return "";
    }

    public static List<Integer> readFromFileAsInteger(String filename)
    {
        return FileUtils.readFromFile(filename).stream().map(Integer::valueOf).toList();
    }
}
