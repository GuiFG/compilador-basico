package br.com.isilanguage.ast;

import br.com.isilanguage.datastructures.IsiVariable;


public class CommandLeitura extends AbstractCommand {
    private String id;
    private IsiVariable var;
    
    public CommandLeitura(String id, IsiVariable var) {
        this.id = id;
        this.var = var;
    }

  
    @Override
    public String toString() {
        return "CommandLeitura{" + "id=" + id + '}';
    }

    @Override
    public String generateCodeInCpp() {
        return "cin >> " + id + ";\r"; 
    }
    
    @Override
    public String generateCodeInJava() {
        String leitor = switch (var.getType()) {
            case IsiVariable.NUMBER -> "nextDouble()";
            case IsiVariable.TEXT -> "nextLine()";
            case IsiVariable.BOOL -> "next()";
            default -> "tipo desconhecido" ;
        };
        
        return id + " = scanner." + leitor + ";\r";
    }
    
    @Override
    public CommandType getType() {
        return CommandType.LEITURA;
    }
}
