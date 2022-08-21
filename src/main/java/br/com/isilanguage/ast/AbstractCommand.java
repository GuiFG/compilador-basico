package br.com.isilanguage.ast;


public abstract class AbstractCommand {
    public abstract String generateCodeInCpp();
    public abstract String generateCodeInJava();
    public abstract CommandType getType();
}
