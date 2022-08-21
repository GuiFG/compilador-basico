package br.com.isilanguage.ast;


public class CommandBreak extends AbstractCommand {

    @Override
    public String generateCodeInCpp() {
        return "break;\r";
    }
    
    @Override
    public String generateCodeInJava() {
        return "break;\r";
    }

    @Override
    public CommandType getType() {
        return CommandType.BREAK;
    }
    
    @Override
    public String toString() {
        return "CommandBreak{expressao=parar}";
    }
}
