package org.example;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class VersionSimpleLS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        Path dir = Path.of(nombre);
        StringBuilder sb = new StringBuilder();
        System.out.println("Ficheros del directorio " + dir);
        if (Files.isDirectory(dir)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path fichero : stream) {
                    sb.append(Files.isDirectory(fichero) ? "d" : "-");
                    sb.append(Files.isReadable(fichero) ? "r" : "-");
                    sb.append(Files.isWritable(fichero) ? "w" : "-");
                    sb.append(Files.isExecutable(fichero) ? "x" : "-");
                    if (sb.charAt(0) == 'd'){
                        sb.append(" directorio");
                    }else{
                        sb.append(" archivo");
                    }
                    System.out.println(sb);
                    sb.delete(0, sb.length());
                    }
            } catch (IOException | DirectoryIteratorException ex) {
                System.err.println(ex);
            }

        }
    }
}
