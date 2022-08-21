package br.com.isilanguage.ast;


public class CommandContinue extends AbstractCommand {

    @Override
    public String generateCodeInCpp() {
        return "continue;\r";
    }
    
    @Override
    public String generateCodeInJava() {
        return "continue;\r";
    }

    @Override
    public CommandType getType() {
        return CommandType.CONTINUE;
    }
    
    @Override
    public String toString() {
        return "CommandContinue{expressao=continuar}";
    }
}
