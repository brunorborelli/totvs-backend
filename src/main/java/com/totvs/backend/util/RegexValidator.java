package com.totvs.backend.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsável por validações utilizando expressões regulares.
 */
public class RegexValidator {

    public static final String TELEFONE_REGEX = "^(55\\d{12}|\\d{8})$";
    public static final Pattern TELEFONE_PATTERN = Pattern.compile(TELEFONE_REGEX);
    public static final String TAMANHO_DEZ = "^.{1,10}$";

    /**
     * Valida se o telefone é válido.
     *
     * @param telefone o telefone a ser validado
     * @return true se o telefone for válido, false caso contrário
     */
    public static boolean isTelefoneValido(String telefone) {
        if (telefone == null) {
            return false;
        }
        Matcher matcher = TELEFONE_PATTERN.matcher(telefone);
        return matcher.matches();
    }

    /**
     * Valida se o texto possui um tamanho máximo de X caracteres.
     *
     * @param texto o texto a ser validado
     * @param regex a expressao regular a ser validada
     * @return true se o texto tiver no máximo X caracteres, false caso contrário
     */
    public static boolean validaTamanhoTexto(String texto, String regex) {
        if (texto == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
}
