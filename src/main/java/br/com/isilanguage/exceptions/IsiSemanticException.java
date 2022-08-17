package br.com.isilanguage.exceptions;



public class IsiSemanticException extends RuntimeException {
    public IsiSemanticException(String msg) {
        super(msg);
    }
    
    public static void showWarning(Warning warning, String name) {
        if (warning == Warning.UNASSIGNED_VARIABLE)
            System.out.println("WARNING: Variavel '" + name + "' declarada e não utilizada");
        else 
            System.out.println("WARNING: " + name);
    }
}