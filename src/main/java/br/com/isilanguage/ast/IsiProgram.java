package br.com.isilanguage.ast;

import br.com.isilanguage.datastructures.IsiSymbol;
import br.com.isilanguage.datastructures.IsiSymbolTable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IsiProgram {
    private IsiSymbolTable varTable;
    private ArrayList<AbstractCommand> commands;
    private String programName;
    
    public void generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("#include <stdio.h>\n");
        str.append("int main(){\n");
        for (IsiSymbol symbol: varTable.getAll())
        {
            str.append("\t").append(symbol.generateCodeInC()).append("\n");
        }
        
        for (AbstractCommand command: commands)
        {
            if (command.getType() != CommandType.DECISAO)
                str.append("\t").append(command.generateCodeInC()).append("\n");
            else 
                str.append(command.generateCodeInC()).append("\n");
        }
        str.append("}");
        
        try 
        {
            WriteCodeInFile(str.toString());
        }
        catch (IOException ex)
        {
            System.out.println("ERROR: Write Code In File. " + ex.getMessage());
        }
        
    }
    
    private static void WriteCodeInFile(String code) throws IOException
    {
        try (FileWriter fr = new FileWriter(new File("main.c"))) {
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
