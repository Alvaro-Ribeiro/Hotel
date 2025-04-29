package util;

import java.time.LocalDateTime;

public class Util {
 
    public static String dataFormat(LocalDateTime data){
    	String str = String.format("%02d/%02d/%04d - %02d:%02d", data.getDayOfMonth(), data.getMonthValue(), data.getYear(), data.getHour(), data.getMinute());
    	return str;
    }
    
    public static String valueFormat(double valor) {
    	String str = String.format("%02.2f", valor);
    	return str;
    }

    public static boolean validaCPF(String cpf){
        if(cpf.matches("[0-9]*") && cpf.length() == 11){
            return true;
        }       
        return false;
    }

    public static boolean validaTelefone(String telefone){
        if(telefone.matches("[0-9]*") && (telefone.length() == 11 || telefone.length() == 10)){
            return true;
        }       
        return false;
    }

    public static boolean validaNome(String nome){
        if(nome.matches("[A-Z][a-z]*+([ '-][a-zA-Z]+)*")){
            return true;
        }       
        return false;
    }

    public static boolean validaEmail(String email){
        if(email.matches("[^#@$%¨&*=+]*[@][a-zA-Z]*[.][a-zA-Z][a-zA-Z]*")){
            return true;
        }       
        return false;
    }

    public static boolean validaNomeCat(String nome){
        if(nome.matches("[A-Z][a-z0-9]*+([ '-][a-zA-Z0-9]+)*")){
            return true;
        }       
        return false;
    }

}
