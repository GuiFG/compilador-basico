package br.com.isilanguage.utils;

public final class Util {
    
    private Util() {
    }
    public static String getTabs(int total) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < total; i++)
            str.append("\t");
        
        return str.toString();
    }
}
