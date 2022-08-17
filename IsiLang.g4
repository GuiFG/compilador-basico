grammar IsiLang; 

@header {
    import br.com.isilanguage.datastructures.IsiSymbol;
    import br.com.isilanguage.datastructures.IsiVariable;
    import br.com.isilanguage.datastructures.IsiSymbolTable;
    import br.com.isilanguage.exceptions.IsiSemanticException;
    import br.com.isilanguage.exceptions.Warning;
    import br.com.isilanguage.ast.IsiProgram;
    import br.com.isilanguage.ast.AbstractCommand;
    import br.com.isilanguage.ast.CommandLeitura;
    import br.com.isilanguage.ast.CommandEscrita;
    import br.com.isilanguage.ast.CommandAtribuicao;
    import br.com.isilanguage.ast.CommandDecisao; 
    import br.com.isilanguage.ast.CommandRepeticao;
    
    import java.util.ArrayList;
    import java.util.Stack;
}

@members {
    private int _tipo;
    private String _varName;
    private String _varValue;
    
    private String _readID;
    private String _writeID;
    private String _exprID;
    private String _exprContent;

    private String _exprDecision;
    private Stack<String> stackDecision = new Stack<String>();
    private ArrayList<AbstractCommand> lstTrue;
    private ArrayList<AbstractCommand> lstFalse;
    private int depth = 0;
    
    private String _exprLoop;
    private Stack<String> stackLoop = new Stack<String>();
    private ArrayList<AbstractCommand> loopCommands;

    private IsiSymbolTable symbolTable = new IsiSymbolTable();
    private IsiSymbol symbol;
    private IsiProgram program = new IsiProgram();
    private ArrayList<AbstractCommand> currentThread = new ArrayList<AbstractCommand>();
    private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();

    private void addSymbol(String name) {
        _varName = name;
        _varValue = null;
        symbol = new IsiVariable(_varName, _tipo, _varValue);
        
        if (!symbolTable.exists(_varName)) {
            symbolTable.add(symbol);
            System.out.println("Simbolo adicionado " + symbol);
        }
        else 
            throw new IsiSemanticException("Symbol '" + _varName + "' already declared");
    }

    private void updateSymbolValue(String id, String value) {
        IsiVariable var = (IsiVariable) symbolTable.get(id);

        var.setValue(value);
    }

    private void checkId(String id) {
        if (!symbolTable.exists(id)) 
            throw new IsiSemanticException("Symbol '" + id + "' not declared");
    }

    public void checkWarnings() {
        for (IsiSymbol symbol : symbolTable.getAll()) {
            IsiVariable var = (IsiVariable) symbol;
            String value = var.getValue();
            if (value == null) {
                IsiSemanticException.showWarning(Warning.UNASSIGNED_VARIABLE, var.getName());
            }
        };
    }

    public void showCommands() {
        for (AbstractCommand c: program.getCommands()) {
            System.out.println(c);
        }
    }

    public void generateCode() {
        program.generateTarget();
    }
}

prog    : 'programa' decl bloco 'fimprog;'
            {   
                program.setVarTable(symbolTable);
                program.setCommands(stack.pop()); 
            }
        ;

decl    : (declaravar)+
        ;

declaravar : tipo ID { addSymbol(_input.LT(-1).getText()); }    
            ( VIR 
              ID { addSymbol(_input.LT(-1).getText()); }
            )* SC
           ;

tipo    : 'numero' { _tipo = IsiVariable.NUMBER; }
        | 'texto'  { _tipo = IsiVariable.TEXT;  }
        ;

bloco   : { currentThread = new ArrayList<AbstractCommand>(); 
            stack.push(currentThread);
        }
        (cmd)+
        ;
cmd     : 
      cmdleitura 
    | cmdescrita 
    | cmdattrib 
    | cmdselecao
    | cmdrepeticao
    ;

cmdleitura : 'leia' AP 
                    ID { _readID = _input.LT(-1).getText(); checkId(_readID); }
                    FP 
                    SC  {
                        IsiVariable var =  (IsiVariable)symbolTable.get(_readID);
                        CommandLeitura cmd = new CommandLeitura(_readID, var);
                        stack.peek().add(cmd);
                    }
           ;
cmdescrita : 'escreva' AP 
                       ID { _writeID = _input.LT(-1).getText(); checkId(_writeID); } 
                       FP 
                       SC {
                            IsiVariable var =  (IsiVariable)symbolTable.get(_writeID);
                            CommandEscrita cmd = new CommandEscrita(_writeID, var);
                            stack.peek().add(cmd);
                       }
           ;
cmdattrib  : ID { _exprID = _input.LT(-1).getText(); checkId(_exprID); }
             ATTR { _exprContent = ""; }
             expr 
             SC {
                updateSymbolValue(_exprID, _exprContent);
                CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
                stack.peek().add(cmd);
             }
           ;

cmdselecao : 'se' AP 
                  ID { _exprDecision = _input.LT(-1).getText(); }
                  OPREL { _exprDecision += _input.LT(-1).getText(); }
                  (ID | NUMBER) { _exprDecision += _input.LT(-1).getText(); }
                  FP 
                  ACH 
                  {
                    depth += 1;
                    currentThread = new ArrayList<AbstractCommand>();
                    stack.push(currentThread);

                    stackDecision.push(_exprDecision);
                  }
                  (cmd)+ 
                  FCH 
                  { 
                    lstTrue = stack.pop();
                  }
            ('senao' 
                ACH 
                { 
                    currentThread = new ArrayList<AbstractCommand>();
                    stack.push(currentThread);
                } 
                (cmd+) 
                FCH
                {
                    lstFalse = stack.pop();
                }
            )? 
            {
                _exprDecision = stackDecision.pop();
                CommandDecisao cmd = new CommandDecisao(_exprDecision, lstTrue, lstFalse, depth);
                stack.peek().add(cmd);
                lstTrue = null;
                lstFalse = null;
                depth -= 1;
            }
           ;

cmdrepeticao : 'enquanto' 
                AP 
                ID { _exprLoop = _input.LT(-1).getText(); }
                OPREL { _exprLoop += _input.LT(-1).getText(); }
                (ID | NUMBER) { _exprLoop += _input.LT(-1).getText(); }
                FP 
                ACH  
                {
                    depth += 1;
                    currentThread = new ArrayList<AbstractCommand>();
                    stack.push(currentThread);

                    stackLoop.push(_exprLoop);
                }
                (cmd)+
                FCH
                {
                    loopCommands = stack.pop();
                    _exprLoop = stackLoop.pop();
                    CommandRepeticao cmd = new CommandRepeticao(_exprLoop, loopCommands, depth);
                    stack.peek().add(cmd);
                    loopCommands = null;
                    depth -= 1;
                }
                ;

expr       : termo ( 
                OP { _exprContent += _input.LT(-1).getText(); }
                termo 
            )*
           ;
termo      : ID { String text = _input.LT(-1).getText(); checkId(text);
                  _exprContent += text; 
             } 
           | NUMBER {
                _exprContent += _input.LT(-1).getText();
           }
           ;

AP  : '('
    ;
FP  : ')'
    ;
SC  : ';'
    ;
OP  : '+' | '-' | '*' | '/'
    ;
ATTR : '='
     ;
ID : [a-z] ([a-z] | [A-Z] | [0-9])*
   ;

VIR : ','
    ;

ACH : '{'
    ;

FCH : '}'
    ;

OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;

NUMBER  : [0-9]+ ('.' [0-9]+)?
        ;
WS : (' ' | '\t' | '\n' | '\r') -> skip;
