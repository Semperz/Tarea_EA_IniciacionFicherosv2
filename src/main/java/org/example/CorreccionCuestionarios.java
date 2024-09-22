package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;


public class CorreccionCuestionarios {
    public static void main(String[] args) {
        Path filePath = Path.of("res/test.txt");
        try (BufferedReader lector = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            String linea;
            String respuestasCorrectas = "";
            String alumno;
            String examen;
            double notaAlumno = 0d;
            while ((linea = lector.readLine()) != null) {
                int posicionPrimerBS = linea.indexOf(" ");
                if (linea.length() == 20) {
                    respuestasCorrectas = linea;
                }
                if (posicionPrimerBS == -1) {
                    continue;
                }else{
                    alumno = linea.substring(0, posicionPrimerBS);
                    examen = linea.substring(posicionPrimerBS + 1);
                }
                for (int i = 0; i < respuestasCorrectas.length(); i++) {
                    if (respuestasCorrectas.charAt(i) != examen.charAt(i)){
                        notaAlumno -= 0.15;
                    } else if (examen.charAt(i) == ' '){
                        notaAlumno += 0;
                    }else{
                        notaAlumno += 0.5;
                    }
                }
                System.out.println(alumno + ": " + notaAlumno);
                notaAlumno = 0;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}