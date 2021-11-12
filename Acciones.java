/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jflexp;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import javax.swing.JTextArea;
import jflexp.Tokens;
//import static jflexp.Tokens.ERROR;
//import static jflexp.Tokens.Identificador;
//import static jflexp.Tokens.Numero;
//import static jflexp.Tokens.Reservadas;

/**
 *
 * @author erikj
 */
public class Acciones {

    private JTextArea area, estado;
    private JTextArea resultado;
    private JTextArea ruta;
    private JTextArea expresion;
    private FileOutputStream salida;
    private FileInputStream entrada;

    public Acciones(JTextArea area, JTextArea resultado, JTextArea ruta, 
            JTextArea expresion, JTextArea estado) {
        this.area = area;
        this.expresion = expresion;
        this.resultado = resultado;
        this.ruta = ruta;
         this.estado = estado;
    }

    public void cargarContenido() throws FileNotFoundException, IOException {
        //Leemos la direccion y lo imprimimos
        String cadena = "";
        entrada = new FileInputStream(ruta.getText());
        int asci;
        while ((asci = entrada.read()) != -1) {
            char caracter = (char) asci;
            cadena += caracter;
        }
        area.setText(cadena);
    }

    public void guardarCont() throws FileNotFoundException {
        salida = new FileOutputStream(ruta.getText());
        byte[] bytext = area.getText().getBytes();
        try {
            salida.write(bytext);
        } catch (IOException ex) {

        }
    }

    public void evaluar() {
        String regex = expresion.getText();
        String texto = area.getText();
        String contenidoEvaluado = "";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(texto);
        while (matcher.find()) {
            contenidoEvaluado += "Full match: " + matcher.group(0) + "\n";
            for (int i = 1; i <= matcher.groupCount(); i++) {
                contenidoEvaluado += "Group " + i + ": " + matcher.group(i) + "\n";
            }
        }
        resultado.setText(contenidoEvaluado);
        estado.setText("");
        estado.setText("Evaluacion realizada correctamente");
    }

}
