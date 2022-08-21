package br.com.isilanguage.ast;


public class CommandBreak extends AbstractCommand {

    @Override
    public String generateCodeInC() {
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
