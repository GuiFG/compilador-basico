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
    public String generateCodeInCpp() {
        return "cout << " + id + ";\r";
    }
    
    @Override
    public String generateCodeInJava() {
        return "System.out.println(" + id + ");\r";
    }
    
    @Override
    public CommandType getType() {
        return CommandType.ESCRITA;
    }
    
}
