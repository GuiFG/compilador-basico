package br.com.isilanguage.ast;

import br.com.isilanguage.datastructures.IsiVariable;

public class CommandEscrita extends AbstractCommand {
    private String id;
    private IsiVariable var;
    
    public CommandEscrita(String id, IsiVariable var) {
        this.id = id;
        this.var = var;
    }
    
    @Override
    public String toString() {
        return "CommandEscrita{" + "id=" + id + '}';
    }

    @Override
    public String generateCodeInC() {
        String format = var.getType() == IsiVariable.NUMBER ? "%lf" : "%s";
        
        return "printf(\"" + format + "\", " + id + ");";
    }
    @Override
    public CommandType getType() {
        return CommandType.ESCRITA;
    }
    
}
