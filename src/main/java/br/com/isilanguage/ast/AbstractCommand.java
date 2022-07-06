package br.com.isilanguage.ast;


public abstract class AbstractCommand {
    public abstract String generateCodeInC();
    public abstract CommandType getType();
}
