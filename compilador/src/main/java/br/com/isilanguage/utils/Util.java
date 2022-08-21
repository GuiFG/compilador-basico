package br.com.isilanguage.utils;

import br.com.isilanguage.ast.AbstractCommand;
import br.com.isilanguage.ast.CommandType;
import java.util.ArrayList;

public final class Util {
    
    private Util() {
    }
    public static String getTabs(int total) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < total; i++)
            str.append("\t");
        
        return str.toString();
    }
    
    public static boolean existCommand(ArrayList<AbstractCommand> commands, CommandType cmdType)
    {
        for (AbstractCommand command : commands) {
            if (command.getType() == cmdType)
                return true;
        }
        
        return false;
    }
}
