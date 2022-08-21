package br.com.isilanguage.ast;

import java.util.ArrayList;
import br.com.isilanguage.utils.Util;

public class CommandEnquanto extends AbstractCommand {
    private final String condition;
    private final ArrayList<AbstractCommand> lstCommands;
    private int depth;
    private int depthJava;
    
    public CommandEnquanto(
            String condition, 
            ArrayList<AbstractCommand> lstCommands,
            int depth
    ) {
        this.condition = condition;
        this.lstCommands = lstCommands;
        this.depth = depth;
        this.depthJava = depth + 1;
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
        
        str.append(AppendCommandsCpp(lstCommands));
        str.append("\n")
           .append(Util.getTabs(depth))
           .append("}\r");
        
        return str.toString();
    }
    
    @Override
    public String generateCodeInJava() {
        StringBuilder str = new StringBuilder();
        
        str.append("while (").append(condition)
           .append(") {")
           .append("\n");
        
        str.append(AppendCommandsJava(lstCommands));
        str.append("\n")
           .append(Util.getTabs(depthJava))
           .append("}\r");
        
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
             str.append(Util.getTabs(depthJava + 1))
                .append(cmd.generateCodeInJava());
        }
        
        return str.toString();
    }

    @Override
    public CommandType getType() {
        return CommandType.ENQUANTO;
    }
}
