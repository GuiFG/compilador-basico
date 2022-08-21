package br.com.isilanguage.ast;

import java.util.ArrayList;
import br.com.isilanguage.utils.Util;

public class CommandEnquanto extends AbstractCommand {
    private final String condition;
    private final ArrayList<AbstractCommand> lstCommands;
    private int depth;
    
    public CommandEnquanto(
            String condition, 
            ArrayList<AbstractCommand> lstCommands,
            int depth
    ) {
        this.condition = condition;
        this.lstCommands = lstCommands;
        this.depth = depth;
    }
    
    @Override
    public String toString() {
        return "CommandEnquanto{" + "condition=" + condition + ", lstCommands=" + lstCommands + '}';
    }

    @Override
    public String generateCodeInCpp() {
        StringBuilder str = new StringBuilder();
        
        str.append("while (").append(condition)
           .append(") {")
           .append("\n");
        
        str.append(AppendCommands(lstCommands));
        str.append("\n")
           .append(Util.getTabs(depth))
           .append("}\r");
        
        return str.toString();
    }
    
    private String AppendCommands(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        for (AbstractCommand cmd: list) {
             str.append(Util.getTabs(depth + 1))
                .append(cmd.generateCodeInCpp());
        }
        
        return str.toString();
    }

    @Override
    public CommandType getType() {
        return CommandType.ENQUANTO;
    }
}
