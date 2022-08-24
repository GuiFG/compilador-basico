package br.com.isilanguage.ast;

import br.com.isilanguage.datastructures.IsiSymbol;
import br.com.isilanguage.datastructures.IsiSymbolTable;
import br.com.isilanguage.datastructures.IsiVariable;
import br.com.isilanguage.utils.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IsiProgram {
    private IsiSymbolTable varTable;
    private ArrayList<AbstractCommand> commands;
    private String programName;
    
    public void generateTarget(String fileName) {
        
        String codeCpp = generateCodeInCpp();
        String codeJava = generateCodeInJava();
        try 
        {
            WriteCodeInFile(codeCpp, fileName, "cpp");
            WriteCodeInFile(codeJava, fileName, "java");
        }
        catch (IOException ex)
        {
            System.out.println("ERROR: Write Code In File. " + ex.getMessage());
        }
    }
    
    private String generateCodeInCpp()
    {
        StringBuilder str = new StringBuilder();
        str.append("#include <iostream>\n")
           .append("using namespace std;\n");
        str.append("int main(){\n");
        str.append("\t");
        
        int count = 0;
        int total = varTable.getAll().size();
        for (IsiSymbol symbol: varTable.getAll())
        {
            str.append(symbol.generateCodeInCpp());
            IsiVariable var = (IsiVariable) symbol;
            if (var.getLast() && (count + 1) != total)
                str.append("\t");
            
            count += 1;
        }
        
        for (AbstractCommand command: commands)
        {
            str.append("\t").append(command.generateCodeInCpp()).append("\n");    
        }
        str.append("}");
        
        return str.toString();
    }
    
    private String generateCodeInJava()
    {
        StringBuilder str = new StringBuilder();
        
        boolean existsLeitura = Util.existCommand(commands, CommandType.LEITURA);
        if (existsLeitura)
            str.append("import java.util.Scanner;\n");
        
        str.append("public class Program {\n")
            .append("\tpublic static void main(String[] args) {\n");
        
        
        String tabs = Util.getTabs(2);
        if (existsLeitura)
            str.append(tabs).append("Scanner scanner = new Scanner(System.in);\n");
        
        str.append(tabs);
        int count = 0;
        int total = varTable.getAll().size();
        for (IsiSymbol symbol: varTable.getAll())
        {
            str.append(symbol.generateCodeInJava());
            IsiVariable var = (IsiVariable) symbol;
            if (var.getLast() && (count + 1) != total)
                str.append(tabs);
            
            count += 1;
        }
        
        for (AbstractCommand command: commands)
        {
            str.append(tabs).append(command.generateCodeInJava()).append("\n");    
        }
        
        str.append("\t}\r}");
        
        return str.toString();
    }
    
    private static void WriteCodeInFile(String code, String fileName, String extension) throws IOException
    {
        try (FileWriter fr = new FileWriter(new File(fileName + "." + extension))) {
                fr.write(code);
            }
    }

    public IsiSymbolTable getVarTable() {
        return varTable;
    }

    public void setVarTable(IsiSymbolTable varTable) {
        this.varTable = varTable;
    }

    public ArrayList<AbstractCommand> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<AbstractCommand> commands) {
        this.commands = commands;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
