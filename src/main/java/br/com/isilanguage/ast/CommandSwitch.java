package br.com.isilanguage.ast;

import br.com.isilanguage.datastructures.IsiSymbol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import br.com.isilanguage.utils.Util;

public class CommandSwitch extends AbstractCommand {
    private final String expression;
    private final HashMap<String, ArrayList<AbstractCommand>> cases;
    private final int depth = 2;
    
    public CommandSwitch(
        String expression, 
        HashMap<String, ArrayList<AbstractCommand>> cases
    ) {
        this.expression = expression;
        this.cases = cases;
    }
    
    @Override
    public String toString() {
        return "CommandSwitch{" + "expressao=" + expression + ", lstCommands=" + cases + '}';
    }

    @Override
    public String generateCodeInCpp() {
        StringBuilder str = new StringBuilder();
        
        str.append("switch (").append(expression).append(") {\r");
        
        String blocoCases = getBlocoCases("cpp");
        str.append(blocoCases);
        str.append(Util.getTabs(depth-1)).append("}\r");
        
        return str.toString();
    }
    
    @Override
    public String generateCodeInJava() {
        StringBuilder str = new StringBuilder();
        
        str.append("switch (").append(expression).append(") {\r");
        
        String blocoCases = getBlocoCases("java");
        str.append(blocoCases);
        str.append(Util.getTabs(depth)).append("}\r");
        
        return str.toString();
    }
    
    private String getBlocoCases(String type) {
        int profundidade = depth;
        if (type.equals("java"))
            profundidade += 1;
        
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, ArrayList<AbstractCommand>> set :
             cases.entrySet()) {
            
            str.append(Util.getTabs(profundidade));
            
            String key = set.getKey();
            if (!key.equals("outrocaso"))
                str.append("case ").append(key);
            else 
                str.append("default");
            str.append(":\r");
               
            if (type.equals("cpp"))
                str.append(AppendCommandsCpp(set.getValue()));
            else if (type.equals("java"))
                str.append(AppendCommandsJava(set.getValue()));
        }
        
        return str.toString();
    }
    
    private String AppendCommandsCpp(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        for (AbstractCommand cmd: list) {
             str.append(Util.getTabs(depth + 1))
                .append(cmd.generateCodeInCpp());
        }
        
        return str.toString();
    }
    
    private String AppendCommandsJava(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        for (AbstractCommand cmd: list) {
             str.append(Util.getTabs(depth + 2))
                .append(cmd.generateCodeInJava());
        }
        
        return str.toString();
    }

    @Override
    public CommandType getType() {
        return CommandType.SWITCH;
    }
}
