package br.com.mulacar.util;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class UtilString {
    
    public static String removerAcentos(String x) {
        x = Normalizer.normalize(x, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        x = pattern.matcher(x).replaceAll("");
        x = x.replaceAll("[^a-zA-Z0-9 ]", "");
//        x = x.replaceAll("[^\\p{ASCII}]", "");
//        x = x.replaceAll("\\b\\w{2}\\b", "");
        x = x.replaceAll("  ", " ");
        return x;
    }
    
    public static String removerCaracteresNaoAlfaNumericos(String texto) {
        if (texto == null) {
                return "";
        } else {
                return texto.replaceAll("[^A-Za-zÀ-ú0-9 ]", "");
        }
    }   
    
    public static String removerNumeros(String string){
       string = string.replaceAll("[^A-Za-zÀ-ú-' ']+", "");
       return string;
    }   
//    2345aaa3245aaa
    public static String removerLetras(String string) {
        string = string.replaceAll("[^0-9 ]", "");
        return string;
    }    
    
    public static boolean ehEmailValido(String email) {
//        /^[a-z0-9.]+@[a-z0-9]+\.[a-z]+\.([a-z]+)?$/i
                
        boolean ehEmailIdValido = false;
        
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            
            Matcher matcher = pattern.matcher(email);
            
            if (matcher.matches()) {
                ehEmailIdValido = true;
            }
        }
        return ehEmailIdValido;                
    }

    public static String mascararCasasDecimais(String string) {
        StringBuilder sb = new StringBuilder();
        
        if (!string.equals("") && string.length() > 1 ) {
            String aux = string;
            
            sb.append(string.substring(string.length() -1, string.length() -3));
            sb.append(",", 0, 0);
            sb.append(aux, 0, aux.length() - 3);
            
            return sb.toString();
        } else {
            return string;
        }
        
    }

}
