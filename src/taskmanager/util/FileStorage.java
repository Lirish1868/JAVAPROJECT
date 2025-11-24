package taskmanager.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import taskmanager.exception.TaskManagerException;

public class FileStorage {
    public static List<String> readLines(String filePath) throws TaskManagerException {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return lines;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new TaskManagerException("Error reading file: " + filePath);
        }
        return lines;
    }

    public static void writeLines(String filePath, List<String> lines) throws TaskManagerException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new TaskManagerException("Error writing file: " + filePath);
        }
    }
    
    public static void appendLine(String filePath, String line) throws TaskManagerException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            throw new TaskManagerException("Error appending to file: " + filePath);
        }
    }
}
