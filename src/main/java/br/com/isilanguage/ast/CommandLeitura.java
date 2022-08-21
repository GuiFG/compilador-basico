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
    public CommandType getType() {
        return CommandType.LEITURA;
    }
}
