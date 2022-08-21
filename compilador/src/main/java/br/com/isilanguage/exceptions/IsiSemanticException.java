package br.com.isilanguage.exceptions;



public class IsiSemanticException extends RuntimeException {
    public IsiSemanticException(String msg) {
        super(msg);
    }
    
    public static void showWarning(Warning warning, String name) {
        System.out.println(getWarning(warning, name));
    }
    
    public static String getWarning(Warning warning, String name) {
        String message;
        if (warning == Warning.UNASSIGNED_VARIABLE)
            message = "WARNING: Variavel '" + name + "' declarada e não utilizada";
        else 
            message = "WARNING: " + name;
        
        return message;
    }
}