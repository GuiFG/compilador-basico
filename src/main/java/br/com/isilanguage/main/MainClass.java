package br.com.isilanguage.main;

import br.com.isilanguage.exceptions.IsiSemanticException;
import br.com.isilanguage.parser.IsiLangLexer;
import br.com.isilanguage.parser.IsiLangParser;
import java.io.IOException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;


public class MainClass {
     public static void main(String[] args) {
        try {
            IsiLangLexer lexer;
            IsiLangParser parser;
            
            lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            
            parser = new IsiLangParser(tokenStream);
            
            parser.prog();
            
            System.out.println("Compilation Successful");
            
            parser.showCommands();
            
            parser.generateCode();
            
        } catch (IsiSemanticException ex) { 
            System.err.println("Semantic ERROR: " + ex.getMessage());
        } catch (IOException | RecognitionException ex) {
            System.err.println("ERROR " + ex.getMessage());
        } catch (RuntimeException ex) {
            System.err.println("ERROR Runtime " + ex.getMessage());
        }
            
    }
}
