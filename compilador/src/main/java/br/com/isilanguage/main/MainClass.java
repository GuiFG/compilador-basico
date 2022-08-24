package br.com.isilanguage.main;

import br.com.isilanguage.datastructures.Tuple;
import br.com.isilanguage.exceptions.IsiSemanticException;
import br.com.isilanguage.exceptions.SyntaxError;
import br.com.isilanguage.exceptions.SyntaxErrorListener;
import br.com.isilanguage.parser.IsiLangLexer;
import br.com.isilanguage.parser.IsiLangParser;
import br.com.isilanguage.parser.IsiLangParser.ProgContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;



public class MainClass {
     public static void main(String[] args) throws IOException {
        ArrayList<String> fileNames = GetFileNames();
        for (String fileName: fileNames) {
            System.out.println("Compilando o programa: " + fileName);
            var result = run(CharStreams.fromFileName("programs/" + fileName), "output/" + fileName);
            System.out.println(result);
            System.out.println("");
        }
    }
     
     public static ArrayList<String> GetFileNames()
     {
        ArrayList<String> files = new ArrayList<>();
        String currentPath = System.getProperty("user.dir");
        File folder = new File(currentPath + "/programs");
        File[] listOfFiles = folder.listFiles();
         for (File file : listOfFiles) {
             if (file.isFile()) {
                files.add(file.getName());
             } 
         }
         
         return files;
     }
     
     public static Tuple<String, ArrayList<String>> run(CharStream stream, String outputFile) {
        String message;
        ArrayList<String> avisos = new ArrayList<>();
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
                
                return new Tuple(getErrors(errors), null);
            }
            
            avisos = parser.getWarnings();
            System.out.println(avisos);
            message = "Compilation Successful!";
            
            parser.showCommands();
            parser.generateCode(outputFile);
            
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
         
         return new Tuple(message, avisos);
     }
     
     private static String getErrors(List<SyntaxError> errors) {
         StringBuilder str = new StringBuilder();
         for (SyntaxError error: errors) {
             str.append(error.getMessage());
         }
         
         return str.toString();
     }
}
