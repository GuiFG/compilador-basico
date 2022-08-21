package br.com.isilanguage.ast;

import java.util.ArrayList;
import br.com.isilanguage.utils.Util;

public class CommandDecisao extends AbstractCommand {
    private final String condition;
    private final ArrayList<AbstractCommand> lstTrue;
    private final ArrayList<AbstractCommand> lstFalse;
    private int depth;
    
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
    }
    
    @Override
    public String toString() {
        return "CommandDecisao{" + "condition=" + condition + ", lstTrue=" + lstTrue + ", lstFalse=" + lstFalse + '}';
    }

    @Override
    public String generateCodeInC() {
        StringBuilder str = new StringBuilder();
        str.append("if (").append(condition)
           .append(") {\n");
        
        str.append(AppendCommands(lstTrue));
        str.append("\n").append(Util.getTabs(depth))
            .append("}");
        
        if (lstFalse != null && !lstFalse.isEmpty()) {
            str.append(" else {\n");
            str.append(AppendCommands(lstFalse));
            str.append("\n")
               .append(Util.getTabs(depth))
               .append("}");
        }
        str.append("\r");
        
        
        return str.toString();
    }
    
    private String AppendCommands(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        for (AbstractCommand cmd: list) {
            str.append(Util.getTabs(depth+1)).append(cmd.generateCodeInC());
        }
        
        return str.toString();
    }

    @Override
    public CommandType getType() {
        return CommandType.DECISAO;
    }
}
