package br.com.isilanguage.ast;

import java.util.ArrayList;
import br.com.isilanguage.utils.Util;

public class CommandRepeticao extends AbstractCommand {
    private final String condition;
    private final ArrayList<AbstractCommand> lstCommands;
    private int depth;
    
    public CommandRepeticao(
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
        return "CommandRepeticao{" + "condition=" + condition + ", lstCommands=" + lstCommands;
    }

    @Override
    public String generateCodeInC() {
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
        str.append(Util.getTabs(depth + 1));
        for (AbstractCommand cmd: list) {
             str.append(cmd.generateCodeInC());
        }
        
        return str.toString();
    }

    @Override
    public CommandType getType() {
        return CommandType.REPETICAO;
    }
}
