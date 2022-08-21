package br.com.isilanguage.main;

import br.com.isilanguage.exceptions.IsiSemanticException;
import br.com.isilanguage.exceptions.SyntaxError;
import br.com.isilanguage.exceptions.SyntaxErrorListener;
import br.com.isilanguage.parser.IsiLangLexer;
import br.com.isilanguage.parser.IsiLangParser;
import br.com.isilanguage.parser.IsiLangParser.ProgContext;
import java.io.IOException;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;


public class MainClass {
     public static void main(String[] args) throws IOException {
        System.out.println(run(CharStreams.fromFileName("input.isi")));
    }
     
     public static String run(CharStream stream) {
         String message;
         try {
            IsiLangLexer lexer;
            IsiLangParser parser;
            
            lexer = new IsiLangLexer(stream);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            
            parser = new IsiLangParser(tokenStream);
            SyntaxErrorListener listener = new SyntaxErrorListener();
            parser.addErrorListener(listener);
            
            ProgContext result = parser.prog();
            
            if (result.exception != null) {
                List<SyntaxError> errors = listener.getSyntaxErrors();
                
                return getErrors(errors);
            }
            
            parser.checkWarnings();
            
            message = "Compilation Successful!";
            
            parser.showCommands();
            
            parser.generateCode();
            
            System.out.println("End!");
        } catch (IsiSemanticException ex) { 
            message = "Semantic ERROR: " + ex.getMessage();
            System.err.println(message);
        } catch (RecognitionException ex) {
            message = "ERROR " + ex.getMessage();
            System.err.println(message);
        } catch (RuntimeException ex) {
            message = "ERROR Runtime " + ex.getMessage();
            System.err.println(message);
        }  
         
         return message;
     }
     
     private static String getErrors(List<SyntaxError> errors) {
         StringBuilder str = new StringBuilder();
         for (SyntaxError error: errors) {
             str.append(error.getMessage());
         }
         
         return str.toString();
     }
}
