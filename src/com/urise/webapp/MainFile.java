package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        list("D:\\JAVA\\basejava");
    }

    static void list(String filePath) {

        File file = new File(filePath);

        String[] fileList = file.list();
        int i;
        for (i = 0; i < Objects.requireNonNull(fileList).length; i++) {
            File f1 = new File(filePath +
                    File.separator + fileList[i]);

            if (f1.isFile())
                System.out.println(filePath +
                        File.separator + fileList[i]);
            else {
                list(filePath +
                        File.separator + fileList[i]);
            }
        }
    }
}
