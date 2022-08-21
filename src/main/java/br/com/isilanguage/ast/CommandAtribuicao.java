package br.com.isilanguage.ast;


public class CommandAtribuicao extends AbstractCommand {
    private String id;
    private String expr; 
    
    public CommandAtribuicao(String id, String expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "CommandAtribuicao{" + "id=" + id + ", expr=" + expr + '}';
    }

    @Override
    public String generateCodeInCpp() {
        return id + " = " + expr + ";";
    }

    @Override
    public CommandType getType() {
        return CommandType.ATTR;
    }
}
