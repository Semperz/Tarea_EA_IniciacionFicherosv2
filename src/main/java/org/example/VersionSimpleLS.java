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
                    if (Files.isDirectory(fichero)) {
                        sb.append("d");
                    }else{
                        sb.append("-");
                    }
                    if (Files.isReadable(fichero)){
                        sb.append("r");
                    }else{
                        sb.append("-");
                    }
                    if (Files.isWritable(fichero)) {
                        sb.append("w");
                    }else{
                        sb.append("-");
                    }
                    if (Files.isExecutable(fichero)){
                        sb.append("x");
                    }else{
                        sb.append("-");
                    }
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
