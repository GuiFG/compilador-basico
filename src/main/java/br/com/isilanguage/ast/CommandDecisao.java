package br.com.isilanguage.ast;

import java.util.ArrayList;
import br.com.isilanguage.utils.Util;

public class CommandDecisao extends AbstractCommand {
    private final String condition;
    private final ArrayList<AbstractCommand> lstTrue;
    private final ArrayList<AbstractCommand> lstFalse;
    private int depth;
    private int depthJava;
    
    public CommandDecisao(
            String condition, 
            ArrayList<AbstractCommand> lstTrue, 
            ArrayList<AbstractCommand> lstFalse,
            int depth
    ) {
        this.condition = condition;
        this.lstTrue = lstTrue;
        this.lstFalse = lstFalse;
        this.depth = depth;
        this.depthJava = depth + 1;
    }
    
    @Override
    public String toString() {
        return "CommandDecisao{" + "condition=" + condition + ", lstTrue=" + lstTrue + ", lstFalse=" + lstFalse + '}';
    }

    @Override
    public String generateCodeInCpp() {
        StringBuilder str = new StringBuilder();
        str.append("if (").append(condition)
           .append(") {\n");
        
        str.append(AppendCommandsCpp(lstTrue));
        str.append("\n").append(Util.getTabs(depth))
            .append("}");
        
        if (lstFalse != null && !lstFalse.isEmpty()) {
            str.append(" else {\n");
            str.append(AppendCommandsCpp(lstFalse));
            str.append("\n")
               .append(Util.getTabs(depth))
               .append("}");
        }
        str.append("\r");
        
        
        return str.toString();
    }
    
    @Override
    public String generateCodeInJava() {
        StringBuilder str = new StringBuilder();
        str.append("if (").append(condition)
           .append(") {\n");
        
        str.append(AppendCommandsJava(lstTrue));
        str.append("\n").append(Util.getTabs(depthJava))
            .append("}");
        
        if (lstFalse != null && !lstFalse.isEmpty()) {
            str.append(" else {\n");
            str.append(AppendCommandsJava(lstFalse));
            str.append("\n")
               .append(Util.getTabs(depthJava))
               .append("}");
        }
        str.append("\r");
        
        
        return str.toString();
    }
    
    private String AppendCommandsCpp(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        for (AbstractCommand cmd: list) {
            str.append(Util.getTabs(depth+1)).append(cmd.generateCodeInCpp());
        }
        
        return str.toString();
    }
    
    private String AppendCommandsJava(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        for (AbstractCommand cmd: list) {
            str.append(Util.getTabs(depthJava+1)).append(cmd.generateCodeInJava());
        }
        
        return str.toString();
    }

    @Override
    public CommandType getType() {
        return CommandType.DECISAO;
    }
}
