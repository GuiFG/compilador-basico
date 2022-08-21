package br.com.isilanguage.ast;

import java.util.ArrayList;
import br.com.isilanguage.utils.Util;

public class CommandRepeticao extends AbstractCommand {
    private final String expressao;
    private final ArrayList<AbstractCommand> lstCommands;
    private final String inicio;
    private final String fim;
    private final String passo;
    private final int depth;
    private final int depthJava;
    
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
        this.depthJava = depth + 1;
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
        
        str.append(AppendCommandsCpp(lstCommands));
        str.append("\n")
           .append(Util.getTabs(depth))
           .append("}\r");
        
        return str.toString();
    }
    
    @Override
    public String generateCodeInJava() {
        StringBuilder str = new StringBuilder();
        
        str.append("for (")
            .append(expressao)
            .append(" = ").append(inicio)
            .append("; ").append(expressao)
            .append(" <= ").append(fim)
            .append("; ").append(expressao)
            .append(" += ").append(passo)
            .append(") {\n");
        
        str.append(AppendCommandsJava(lstCommands));
        str.append("\n")
           .append(Util.getTabs(depthJava))
           .append("}\r");
        
        return str.toString();
    }
    
    private String AppendCommandsCpp(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        
        for (AbstractCommand cmd: list) {
            str.append(Util.getTabs(depth + 1));
            str.append(cmd.generateCodeInCpp());
        }
        
        return str.toString();
    }
    
    private String AppendCommandsJava(ArrayList<AbstractCommand> list) {
        StringBuilder str = new StringBuilder();
        
        for (AbstractCommand cmd: list) {
            str.append(Util.getTabs(depthJava + 1));
            str.append(cmd.generateCodeInJava());
        }
        
        return str.toString();
    }

    @Override
    public CommandType getType() {
        return CommandType.REPETICAO;
    }
}
