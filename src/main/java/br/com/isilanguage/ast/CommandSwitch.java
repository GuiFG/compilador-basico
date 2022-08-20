package br.com.isilanguage.ast;

import br.com.isilanguage.datastructures.IsiSymbol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import br.com.isilanguage.utils.Util;

public class CommandSwitch extends AbstractCommand {
    private final String expression;
    private final HashMap<String, ArrayList<AbstractCommand>> cases;
    private int depth = 2;
    
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
    public String generateCodeInC() {
        StringBuilder str = new StringBuilder();
        
        str.append("switch (").append(expression).append(") {\r");
        
        String blocoCases = getBlocoCases();
        str.append(blocoCases);
        str.append(Util.getTabs(depth-1)).append("}");
        
        /*       
        switch(expression) {
            case x:
              // code block
              break;
            case y:
              // code block
              break;
            default:
              // code block
        }
        */
        
        return str.toString();
    }
    
    private String getBlocoCases() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, ArrayList<AbstractCommand>> set :
             cases.entrySet()) {
            str.append(Util.getTabs(depth));
            
            String key = set.getKey();
            if (!key.equals("outrocaso"))
                str.append("case ").append(key);
            else 
                str.append("default");
            str.append(":\r");
               
            str.append(AppendCommands(set.getValue()));
        }
        
        return str.toString();
    }
    
    private String AppendCommands(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        for (AbstractCommand cmd: list) {
             str.append(Util.getTabs(depth + 1))
                .append(cmd.generateCodeInC());
        }
        
        return str.toString();
    }

    @Override
    public CommandType getType() {
        return CommandType.SWITCH;
    }
}
