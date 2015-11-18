/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.Color;
import java.awt.TextField;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author TUNTS
 */
public class Validation {

    private static Border borderRed = BorderFactory.createLineBorder(Color.red);
    private static Border borderGreen = BorderFactory.createLineBorder(Color.green);

    /**
     *
     * @author Wellington de lima alves
     */
    public static boolean validarCpf(String cpf) {
        if (cpf == null) {
            return false;
        } else {
            String cpfGerado = "";
            cpf = removerCaracteres(cpf);
            if (verificarSeTamanhoInvalido(cpf)) {
                return false;
            }
            cpfGerado = cpf.substring(0, 9);
            cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));
            cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));

            if (!cpfGerado.equals(cpf)) {
                return false;
            }
        }
        return true;
    }

    private static String removerCaracteres(String cpf) {
        cpf = cpf.replace("-", "");
        return cpf.replace(".", "");
    }

    private static boolean verificarSeTamanhoInvalido(String cpf) {
        if (cpf.length() != 11) {
            return true;
        }
        return false;
    }

    private static String calculoComCpf(String cpf) {
        int digGerado = 0;
        int mult = cpf.length() + 1;
        char[] charCpf = cpf.toCharArray();
        for (int i = 0; i < cpf.length(); i++) {
            digGerado += (charCpf[i] - 48) * mult--;
        }
        if (digGerado % 11 < 2) {
            digGerado = 0;
        } else {
            digGerado = 11 - digGerado % 11;
        }
        return String.valueOf(digGerado);
    }

    public static boolean validateEmptyField(JTextField field) {
        if (field.getText().trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean validateNumberField(JTextField field) {

        if (field.getText().trim().matches("[0-9.-]+")) {
            field.setBorder(borderGreen);
            return true;
        } else {
            field.setBorder(borderRed);
            return false;
        }
    }

    public static boolean validateTextField(JTextField field) {
        if (field.getText().trim().matches("[a-zA-z ]+")) {
            field.setBorder(borderGreen);
            return true;
        } else {
            field.setBorder(borderRed);
            return false;
        }

    }

    public static void maskCPF(JFormattedTextField field) throws ParseException {
        MaskFormatter formatter = new MaskFormatter("###.###.###-##");
        formatter.setValidCharacters("0123456789");
        formatter.install(field);

    }
    
    public static void maskPhone(JFormattedTextField field) throws ParseException {
        MaskFormatter formatter = new MaskFormatter("(##) #########");
        formatter.setValidCharacters("0123456789");
        formatter.install(field);

    }
    
    public static boolean setBorderRed(JPasswordField field) {
            field.setBorder(borderRed);
            return false;
    }

    public static boolean setBorderGreen(JPasswordField field) {
            field.setBorder(borderGreen);
            return true;
    }

    public static boolean checkPassword(JPasswordField tfEmpPassword, JPasswordField tfEmpPasswordConfirm) {
        if (new String(tfEmpPassword.getPassword()).equals(new String(tfEmpPasswordConfirm.getPassword()))) {
            Validation.setBorderGreen(tfEmpPassword);
            Validation.setBorderGreen(tfEmpPasswordConfirm);
            return true;
        } else {
            Validation.setBorderRed(tfEmpPassword);
            Validation.setBorderRed(tfEmpPasswordConfirm);
            return false;
        }  
    }

}
