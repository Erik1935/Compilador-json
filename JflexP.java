/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jflexp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author erikj
 */
public class JflexP {

    public static void main(String[] args) throws Exception {
       Tokens pReservadas = Tokens.ERROR;
         try{
           generar();
        }catch(Exception e){
           throw new Exception("Error al generar Archivo Lexer");
       }
    }
//        public static void generarLexer() throws Exception{
//        // Ruta del archivo donde se encuentra el archivo lex.flex
//        String ruta = "C:\\Users\\erikj\\Documents\\NetBeansProjects\\jflexP\\src\\jflexp\\Lexer.flex\\";
//        try {  
//            File archivo = new File(ruta);
//            JFlex.Main.generate(archivo);
//        }catch(Exception e){
//            throw new Exception("Error al crear  Archivo Lexer.java ");
//        }
//    }
    public static void generar() throws IOException, Exception{
String rutaProy = "C:\\Users\\erikj\\Documents\\NetBeansProjects\\jflexP";
String[] ruta1 = {rutaProy+"\\src\\jflexp\\Lexer.flex"};
String[] ruta2 = {rutaProy+"\\src\\jflexp\\LexerCup.flex"};
String[] rutaS = {"-parser", "Sintax", rutaProy+"\\src\\jflexp\\Sintax.cup"};
JFlex.Main.generate(ruta1);
JFlex.Main.generate(ruta2);
java_cup.Main.main(rutaS);
Path rutaSym = Paths.get(rutaProy+"\\src\\jflexp\\sym.java");
if (Files.exists(rutaSym)) {
Files.delete(rutaSym);
}
Files.move(
Paths.get(rutaProy+"\\sym.java"),
Paths.get(rutaProy+"\\src\\jflexp\\sym.java")
);
Path rutaSin = Paths.get(rutaProy+"\\src\\jflexp\\Sintax.java");
if (Files.exists(rutaSin)) {
Files.delete(rutaSin);
}
Files.move(
Paths.get(rutaProy+"\\Sintax.java"),
Paths.get(rutaProy+"\\src\\jflexp\\Sintax.java")
);
}
}
