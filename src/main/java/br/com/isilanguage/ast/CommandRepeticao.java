package br.com.isilanguage.ast;

import java.util.ArrayList;
import br.com.isilanguage.utils.Util;

public class CommandRepeticao extends AbstractCommand {
    private final String expressao;
    private final ArrayList<AbstractCommand> lstCommands;
    private final String inicio;
    private final String fim;
    private final String passo;
    private int depth;
    
    public CommandRepeticao(
            String expressao, 
            ArrayList<AbstractCommand> lstCommands,
            String inicio,
            String fim,
            String passo,
            int depth
    ) {
        this.expressao = expressao;
        this.lstCommands = lstCommands;
        this.inicio = inicio;
        this.fim = fim;
        this.passo = passo;
        this.depth = depth;
    }
    
    @Override
    public String toString() {
        return "CommandRepeticao{" + "expressao=" + expressao + ", lstCommands=" + lstCommands + 
                "inicio=" + inicio + ", fim=" + fim + ", passo=" + passo + '}';
    }

    @Override
    public String generateCodeInCpp() {
        StringBuilder str = new StringBuilder();
        
        str.append("for (int ")
            .append(expressao)
            .append(" = ").append(inicio)
            .append("; ").append(expressao)
            .append(" <= ").append(fim)
            .append("; ").append(expressao)
            .append(" += ").append(passo)
            .append(") {\n");
        
        str.append(AppendCommands(lstCommands));
        str.append("\n")
           .append(Util.getTabs(depth))
           .append("}\r");
        
        return str.toString();
    }
    
    private String AppendCommands(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        
        for (AbstractCommand cmd: list) {
            str.append(Util.getTabs(depth + 1));
            str.append(cmd.generateCodeInCpp());
        }
        
        return str.toString();
    }

    @Override
    public CommandType getType() {
        return CommandType.REPETICAO;
    }
}
