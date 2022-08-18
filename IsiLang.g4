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
    private int _typeVar; 

    private String _exprDecision;
    private Stack<String> stackDecision = new Stack<String>();
    private ArrayList<AbstractCommand> lstTrue;
    private ArrayList<AbstractCommand> lstFalse;
    private int depth = 0;
    private int typeVar1;
    private int typeVar2;
    private String termo1;
    private String termo2;
    
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

    private void checkType(int type, int expected) {
        if (type == -1)
            return;

        if (type != expected)
            throw new IsiSemanticException("Symbol '" + _exprID + "' com tipo incompativel. Valor = " + _exprContent);
    }

    private void checkTypeId(int type, String id) {
        if (type == -1)
            return;

        if (type != getTypeVariable(id))
            throw new IsiSemanticException("Symbol '" + _exprID + "' nao pode ser a relacionado com a variavel '" + id + "'");
    }

    private void checkTypeOperator(int type, String operator) 
    {
        if (type == -1)
            return;

        if (type == IsiVariable.TEXT && !operator.equals("+"))
            throw new IsiSemanticException("Operador '" + operator + "' nao permitido para a variavel '" + _exprID + "' do tipo 'texto'");
    }

    private int getTypeVariable(String id) {
        IsiVariable var = (IsiVariable) symbolTable.get(id);
        return var.getType();
    }

    private void updateComparisonTypeVariables(String id) {
        int type = getTypeVariable(id);
        updateComparisonTypeVariables(type);
    }

    private void updateComparisonTypeVariables(int type) {
        if (typeVar1 == -1)
            typeVar1 = type;    
        else 
            typeVar2 = type;
    }

    private void checkComparisonTypes() {
        if (typeVar1 != typeVar2) {
            String message = 
                String.format(
                    "Comando de comparacao com tipos incompativeis em relacao aos termos '%s' (%s) e '%s' (%s)",
                    termo1, getNameType(typeVar1), termo2, getNameType(typeVar2));
            throw new IsiSemanticException(message);
        }
    }

    private String getNameType(int type) {
        String name = switch (type) {
                case IsiVariable.NUMBER -> "numero";
                case IsiVariable.TEXT -> "texto";
                default -> "tipo desconhecido " + type;
        };
	        
        return name;
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
cmdattrib  : ID { 
                    _exprID = _input.LT(-1).getText(); 
                    checkId(_exprID); 
                    _typeVar = getTypeVariable(_exprID);
                }
             ATTR { _exprContent = ""; }
             expr 
             SC {
                updateSymbolValue(_exprID, _exprContent);
                CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
                stack.peek().add(cmd);
                _typeVar = -1;
                _exprContent = "";
             }
           ;

cmdselecao : 'se' AP { typeVar1 = -1; typeVar2 = -1; }
                  termo  { 
                    String text = _input.LT(-1).getText(); 
                    termo1 = text;
                    _exprDecision = text;
                }
                  OPREL { _exprDecision += _input.LT(-1).getText(); }
                  termo { 
                    text = _input.LT(-1).getText(); 
                    termo2 = text;
                    _exprDecision += text;
                  }
                  FP { checkComparisonTypes(); }
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
                AP { typeVar1 = -1; typeVar2 = -1; }
                termo { 
                    String text = _input.LT(-1).getText(); 
                    termo1 = text;
                    _exprLoop = text;
                }
                OPREL { _exprLoop += _input.LT(-1).getText(); }
                termo { 
                    text = _input.LT(-1).getText(); 
                    termo2 = text;
                    _exprLoop += text;
                }
                FP { checkComparisonTypes(); }
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
                OP { 
                    String content = _input.LT(-1).getText(); 
                    checkTypeOperator(_typeVar, content); 
                    _exprContent += content;
                }
                termo 
            )*
           ;
termo      : ID { String text = _input.LT(-1).getText(); 
                    checkId(text);
                    checkTypeId(_typeVar, text);
                    _exprContent += text; 
                    updateComparisonTypeVariables(text);
             } 
           | NUMBER { 
                _exprContent += _input.LT(-1).getText(); 
                checkType(_typeVar, IsiVariable.NUMBER); 
                updateComparisonTypeVariables(IsiVariable.NUMBER);
            }
           | TEXT { 
                _exprContent += _input.LT(-1).getText(); 
                checkType(_typeVar, IsiVariable.TEXT); 
                updateComparisonTypeVariables(IsiVariable.TEXT);
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

TEXT : ["] ([a-zA-Z0-9.,!?$%#@&*() ])* ["] 
     ;
    
WS : (' ' | '\t' | '\n' | '\r') -> skip;
