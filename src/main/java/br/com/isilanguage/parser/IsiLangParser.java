// Generated from IsiLang.g4 by ANTLR 4.7.1
package br.com.isilanguage.parser;

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
    import br.com.isilanguage.ast.CommandSwitch;
    import br.com.isilanguage.ast.CommandBreak;
    import br.com.isilanguage.ast.CommandContinue;
    import br.com.isilanguage.ast.CommandType;
    
    import java.util.ArrayList;
    import java.util.Stack;
    import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, AP=16, FP=17, 
		SC=18, DP=19, OP=20, ATTR=21, ID=22, VIR=23, ACH=24, FCH=25, OPREL=26, 
		NUMBER=27, TEXT=28, BOOL=29, WS=30;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_cmdswitch = 11, RULE_cmdBreak = 12, 
		RULE_cmdContinue = 13, RULE_expr = 14, RULE_termo = 15;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdrepeticao", "cmdswitch", "cmdBreak", "cmdContinue", 
		"expr", "termo"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'logico'", "'leia'", 
		"'escreva'", "'se'", "'senao'", "'enquanto'", "'escolha'", "'caso'", "'outrocaso'", 
		"'parar'", "'continuar'", "'('", "')'", "';'", "':'", null, "'='", null, 
		"','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "AP", "FP", "SC", "DP", "OP", "ATTR", "ID", "VIR", 
		"ACH", "FCH", "OPREL", "NUMBER", "TEXT", "BOOL", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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
	    private Boolean _breakOk = false;
	    private Boolean _continueOk = false;

	    private String caseExpression;
	    private int countCase = 0;
	    private Stack<String> stackCaseTerms = new Stack<String>();

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

	    private void checkBreak()
	    {   
	        if (!_breakOk)
	            throw new IsiSemanticException("Comando 'parar' deve ser usado dentro de um estrutura de repeticao ou de escolha");
	    }

	    private void checkContinue()
	    {   
	        if (!_continueOk)
	            throw new IsiSemanticException("Comando 'continue' deve ser usado dentro de um estrutura de repeticao");
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
	                case IsiVariable.BOOL -> "logico";
	                default -> "tipo desconhecido " + type;
	        };
		        
	        return name;
	    }

	    private HashMap<String, ArrayList<AbstractCommand>> getCasesCommands(
	        Stack<ArrayList<AbstractCommand>> stack, 
	        Stack<String> stackCaseTerms,
	        int countCase
	    )
	    {
	        HashMap<String, ArrayList<AbstractCommand>> cases = new HashMap<String, ArrayList<AbstractCommand>>();
	        for (int i = 0; i < countCase; i++) {
	            ArrayList<AbstractCommand> commands = stack.pop();
	            String term = stackCaseTerms.pop();
	            cases.put(term, commands);
	        }

	        return cases;
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

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(T__0);
			setState(33);
			decl();
			setState(34);
			bloco();
			setState(35);
			match(T__1);
			   
			                program.setVarTable(symbolTable);
			                program.setCommands(stack.pop()); 
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				declaravar();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			tipo();
			setState(44);
			match(ID);
			 addSymbol(_input.LT(-1).getText()); 
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(46);
				match(VIR);
				setState(47);
				match(ID);
				 addSymbol(_input.LT(-1).getText()); 
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(T__2);
				 _tipo = IsiVariable.NUMBER; 
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(T__3);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(60);
				match(T__4);
				 _tipo = IsiVariable.BOOL; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 currentThread = new ArrayList<AbstractCommand>(); 
			            stack.push(currentThread);
			        
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				cmd();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdrepeticaoContext cmdrepeticao() {
			return getRuleContext(CmdrepeticaoContext.class,0);
		}
		public CmdswitchContext cmdswitch() {
			return getRuleContext(CmdswitchContext.class,0);
		}
		public CmdBreakContext cmdBreak() {
			return getRuleContext(CmdBreakContext.class,0);
		}
		public CmdContinueContext cmdContinue() {
			return getRuleContext(CmdContinueContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				cmdleitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				cmdattrib();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				cmdselecao();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
				cmdrepeticao();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(75);
				cmdswitch();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 7);
				{
				setState(76);
				cmdBreak();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 8);
				{
				setState(77);
				cmdContinue();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__5);
			setState(81);
			match(AP);
			setState(82);
			match(ID);
			 _readID = _input.LT(-1).getText(); checkId(_readID); 
			setState(84);
			match(FP);
			setState(85);
			match(SC);

			                        IsiVariable var =  (IsiVariable)symbolTable.get(_readID);
			                        CommandLeitura cmd = new CommandLeitura(_readID, var);
			                        stack.peek().add(cmd);
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__6);
			setState(89);
			match(AP);
			setState(90);
			match(ID);
			 _writeID = _input.LT(-1).getText(); checkId(_writeID); 
			setState(92);
			match(FP);
			setState(93);
			match(SC);

			                            IsiVariable var =  (IsiVariable)symbolTable.get(_writeID);
			                            CommandEscrita cmd = new CommandEscrita(_writeID, var);
			                            stack.peek().add(cmd);
			                       
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(ID);
			 
			                    _exprID = _input.LT(-1).getText(); 
			                    checkId(_exprID); 
			                    _typeVar = getTypeVariable(_exprID);
			                
			setState(98);
			match(ATTR);
			 _exprContent = ""; 
			setState(100);
			expr();
			setState(101);
			match(SC);

			                updateSymbolValue(_exprID, _exprContent);
			                CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			                stack.peek().add(cmd);
			                _typeVar = -1;
			                _exprContent = "";
			             
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__7);
			setState(105);
			match(AP);
			 typeVar1 = -1; typeVar2 = -1; 
			setState(107);
			termo();
			 
			                    String text = _input.LT(-1).getText(); 
			                    termo1 = text;
			                    _exprDecision = text;
			                
			setState(109);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(111);
			termo();
			 
			                    text = _input.LT(-1).getText(); 
			                    termo2 = text;
			                    _exprDecision += text;
			                  
			setState(113);
			match(FP);
			 checkComparisonTypes(); 
			setState(115);
			match(ACH);

			                    depth += 1;
			                    currentThread = new ArrayList<AbstractCommand>();
			                    stack.push(currentThread);

			                    stackDecision.push(_exprDecision);
			                  
			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(117);
				cmd();
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << ID))) != 0) );
			setState(122);
			match(FCH);
			 
			                    lstTrue = stack.pop();
			                  
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(124);
				match(T__8);
				setState(125);
				match(ACH);
				 
				                    currentThread = new ArrayList<AbstractCommand>();
				                    stack.push(currentThread);
				                
				{
				setState(128); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(127);
					cmd();
					}
					}
					setState(130); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << ID))) != 0) );
				}
				setState(132);
				match(FCH);

				                    lstFalse = stack.pop();
				                
				}
			}


			                _exprDecision = stackDecision.pop();
			                CommandDecisao cmd = new CommandDecisao(_exprDecision, lstTrue, lstFalse, depth);
			                stack.peek().add(cmd);
			                lstTrue = null;
			                lstFalse = null;
			                depth -= 1;
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdrepeticaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdrepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdrepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdrepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdrepeticao(this);
		}
	}

	public final CmdrepeticaoContext cmdrepeticao() throws RecognitionException {
		CmdrepeticaoContext _localctx = new CmdrepeticaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdrepeticao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(T__9);
			setState(140);
			match(AP);
			 typeVar1 = -1; typeVar2 = -1; _breakOk = true; _continueOk = true; 
			setState(142);
			termo();
			 
			                    String text = _input.LT(-1).getText(); 
			                    termo1 = text;
			                    _exprLoop = text;
			                
			setState(144);
			match(OPREL);
			 _exprLoop += _input.LT(-1).getText(); 
			setState(146);
			termo();
			 
			                    text = _input.LT(-1).getText(); 
			                    termo2 = text;
			                    _exprLoop += text;
			                
			setState(148);
			match(FP);
			 checkComparisonTypes(); 
			setState(150);
			match(ACH);

			                    depth += 1;
			                    currentThread = new ArrayList<AbstractCommand>();
			                    stack.push(currentThread);

			                    stackLoop.push(_exprLoop);
			                
			setState(153); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(152);
				cmd();
				}
				}
				setState(155); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << ID))) != 0) );
			setState(157);
			match(FCH);

			                    loopCommands = stack.pop();
			                    _exprLoop = stackLoop.pop();
			                    CommandRepeticao cmd = new CommandRepeticao(_exprLoop, loopCommands, depth);
			                    stack.peek().add(cmd);
			                    loopCommands = null;
			                    depth -= 1;
			                    _breakOk = false;
			                    _continueOk = false;
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdswitchContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public List<TerminalNode> DP() { return getTokens(IsiLangParser.DP); }
		public TerminalNode DP(int i) {
			return getToken(IsiLangParser.DP, i);
		}
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<CmdleituraContext> cmdleitura() {
			return getRuleContexts(CmdleituraContext.class);
		}
		public CmdleituraContext cmdleitura(int i) {
			return getRuleContext(CmdleituraContext.class,i);
		}
		public List<CmdescritaContext> cmdescrita() {
			return getRuleContexts(CmdescritaContext.class);
		}
		public CmdescritaContext cmdescrita(int i) {
			return getRuleContext(CmdescritaContext.class,i);
		}
		public List<CmdattribContext> cmdattrib() {
			return getRuleContexts(CmdattribContext.class);
		}
		public CmdattribContext cmdattrib(int i) {
			return getRuleContext(CmdattribContext.class,i);
		}
		public List<CmdselecaoContext> cmdselecao() {
			return getRuleContexts(CmdselecaoContext.class);
		}
		public CmdselecaoContext cmdselecao(int i) {
			return getRuleContext(CmdselecaoContext.class,i);
		}
		public List<CmdrepeticaoContext> cmdrepeticao() {
			return getRuleContexts(CmdrepeticaoContext.class);
		}
		public CmdrepeticaoContext cmdrepeticao(int i) {
			return getRuleContext(CmdrepeticaoContext.class,i);
		}
		public List<CmdswitchContext> cmdswitch() {
			return getRuleContexts(CmdswitchContext.class);
		}
		public CmdswitchContext cmdswitch(int i) {
			return getRuleContext(CmdswitchContext.class,i);
		}
		public List<CmdBreakContext> cmdBreak() {
			return getRuleContexts(CmdBreakContext.class);
		}
		public CmdBreakContext cmdBreak(int i) {
			return getRuleContext(CmdBreakContext.class,i);
		}
		public CmdswitchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdswitch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdswitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdswitch(this);
		}
	}

	public final CmdswitchContext cmdswitch() throws RecognitionException {
		CmdswitchContext _localctx = new CmdswitchContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdswitch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(T__10);
			setState(161);
			match(AP);
			setState(162);
			match(ID);
			 
			                caseExpression = _input.LT(-1).getText(); 
			                checkId(caseExpression); 
			                _typeVar = getTypeVariable(caseExpression);
			                _breakOk = true;
			            
			setState(164);
			match(FP);
			setState(165);
			match(ACH);
			setState(184); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(166);
				match(T__11);
				setState(167);
				termo();
				 
				                    _exprContent = ""; 
				                    stackCaseTerms.push(_input.LT(-1).getText()); 
				                
				setState(169);
				match(DP);
				 
				                    currentThread = new ArrayList<AbstractCommand>();
				                    stack.push(currentThread);
				                    countCase += 1;
				                
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(177);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__5:
						{
						setState(171);
						cmdleitura();
						}
						break;
					case T__6:
						{
						setState(172);
						cmdescrita();
						}
						break;
					case ID:
						{
						setState(173);
						cmdattrib();
						}
						break;
					case T__7:
						{
						setState(174);
						cmdselecao();
						}
						break;
					case T__9:
						{
						setState(175);
						cmdrepeticao();
						}
						break;
					case T__10:
						{
						setState(176);
						cmdswitch();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(179); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(181);
					cmdBreak();
					}
				}

				}
				}
				setState(186); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__11 );
			setState(188);
			match(T__12);
			 stackCaseTerms.push("outrocaso"); 
			setState(190);
			match(DP);

			                currentThread = new ArrayList<AbstractCommand>();
			                stack.push(currentThread);
			                countCase += 1;
			            
			setState(193); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(192);
				cmd();
				}
				}
				setState(195); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << ID))) != 0) );
			setState(197);
			match(FCH);

			                HashMap<String, ArrayList<AbstractCommand>> cases = getCasesCommands(stack, stackCaseTerms, countCase);
			                CommandSwitch cmd = new CommandSwitch(caseExpression, cases);
			                stack.peek().add(cmd);
			                _typeVar = -1;
			                _breakOk = false;
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdBreakContext extends ParserRuleContext {
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdBreakContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdBreak; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdBreak(this);
		}
	}

	public final CmdBreakContext cmdBreak() throws RecognitionException {
		CmdBreakContext _localctx = new CmdBreakContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmdBreak);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(T__13);
			setState(201);
			match(SC);
			 checkBreak(); CommandBreak cmdBreak = new CommandBreak(); stack.peek().add(cmdBreak); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContinueContext extends ParserRuleContext {
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdContinueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdContinue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdContinue(this);
		}
	}

	public final CmdContinueContext cmdContinue() throws RecognitionException {
		CmdContinueContext _localctx = new CmdContinueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_cmdContinue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(T__14);
			setState(205);
			match(SC);
			 checkContinue(); CommandContinue cmdContinue = new CommandContinue(); stack.peek().add(cmdContinue); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			termo();
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(209);
				match(OP);
				 
				                    String content = _input.LT(-1).getText(); 
				                    checkTypeOperator(_typeVar, content); 
				                    _exprContent += content;
				                
				setState(211);
				termo();
				}
				}
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(IsiLangParser.TEXT, 0); }
		public TerminalNode BOOL() { return getToken(IsiLangParser.BOOL, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_termo);
		try {
			setState(225);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				match(ID);
				 String text = _input.LT(-1).getText(); 
				                    checkId(text);
				                    checkTypeId(_typeVar, text);
				                    _exprContent += text; 
				                    updateComparisonTypeVariables(text);
				             
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				match(NUMBER);
				 
				                _exprContent += _input.LT(-1).getText(); 
				                checkType(_typeVar, IsiVariable.NUMBER); 
				                updateComparisonTypeVariables(IsiVariable.NUMBER);
				            
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(221);
				match(TEXT);
				 
				                _exprContent += _input.LT(-1).getText(); 
				                checkType(_typeVar, IsiVariable.TEXT); 
				                updateComparisonTypeVariables(IsiVariable.TEXT);
				            
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(223);
				match(BOOL);

				                _exprContent += _input.LT(-1).getText();
				                checkType(_typeVar, IsiVariable.BOOL);
				                updateComparisonTypeVariables(IsiVariable.BOOL);
				           
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00e6\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\6\3*\n\3\r\3\16\3+\3\4\3\4\3\4\3\4\3\4\3\4\7\4\64\n"+
		"\4\f\4\16\4\67\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5A\n\5\3\6\3\6\6"+
		"\6E\n\6\r\6\16\6F\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7Q\n\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\6\13y\n\13\r\13\16\13z\3\13\3\13\3\13\3\13\3\13\3\13\6"+
		"\13\u0083\n\13\r\13\16\13\u0084\3\13\3\13\3\13\5\13\u008a\n\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u009c"+
		"\n\f\r\f\16\f\u009d\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00b4\n\r\r\r\16\r\u00b5\3\r\5\r\u00b9"+
		"\n\r\6\r\u00bb\n\r\r\r\16\r\u00bc\3\r\3\r\3\r\3\r\3\r\6\r\u00c4\n\r\r"+
		"\r\16\r\u00c5\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\7\20\u00d7\n\20\f\20\16\20\u00da\13\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\5\21\u00e4\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \2\2\2\u00f2\2\"\3\2\2\2\4)\3\2\2\2\6-\3\2\2\2\b"+
		"@\3\2\2\2\nB\3\2\2\2\fP\3\2\2\2\16R\3\2\2\2\20Z\3\2\2\2\22b\3\2\2\2\24"+
		"j\3\2\2\2\26\u008d\3\2\2\2\30\u00a2\3\2\2\2\32\u00ca\3\2\2\2\34\u00ce"+
		"\3\2\2\2\36\u00d2\3\2\2\2 \u00e3\3\2\2\2\"#\7\3\2\2#$\5\4\3\2$%\5\n\6"+
		"\2%&\7\4\2\2&\'\b\2\1\2\'\3\3\2\2\2(*\5\6\4\2)(\3\2\2\2*+\3\2\2\2+)\3"+
		"\2\2\2+,\3\2\2\2,\5\3\2\2\2-.\5\b\5\2./\7\30\2\2/\65\b\4\1\2\60\61\7\31"+
		"\2\2\61\62\7\30\2\2\62\64\b\4\1\2\63\60\3\2\2\2\64\67\3\2\2\2\65\63\3"+
		"\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\289\7\24\2\29\7\3\2\2\2:"+
		";\7\5\2\2;A\b\5\1\2<=\7\6\2\2=A\b\5\1\2>?\7\7\2\2?A\b\5\1\2@:\3\2\2\2"+
		"@<\3\2\2\2@>\3\2\2\2A\t\3\2\2\2BD\b\6\1\2CE\5\f\7\2DC\3\2\2\2EF\3\2\2"+
		"\2FD\3\2\2\2FG\3\2\2\2G\13\3\2\2\2HQ\5\16\b\2IQ\5\20\t\2JQ\5\22\n\2KQ"+
		"\5\24\13\2LQ\5\26\f\2MQ\5\30\r\2NQ\5\32\16\2OQ\5\34\17\2PH\3\2\2\2PI\3"+
		"\2\2\2PJ\3\2\2\2PK\3\2\2\2PL\3\2\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\r"+
		"\3\2\2\2RS\7\b\2\2ST\7\22\2\2TU\7\30\2\2UV\b\b\1\2VW\7\23\2\2WX\7\24\2"+
		"\2XY\b\b\1\2Y\17\3\2\2\2Z[\7\t\2\2[\\\7\22\2\2\\]\7\30\2\2]^\b\t\1\2^"+
		"_\7\23\2\2_`\7\24\2\2`a\b\t\1\2a\21\3\2\2\2bc\7\30\2\2cd\b\n\1\2de\7\27"+
		"\2\2ef\b\n\1\2fg\5\36\20\2gh\7\24\2\2hi\b\n\1\2i\23\3\2\2\2jk\7\n\2\2"+
		"kl\7\22\2\2lm\b\13\1\2mn\5 \21\2no\b\13\1\2op\7\34\2\2pq\b\13\1\2qr\5"+
		" \21\2rs\b\13\1\2st\7\23\2\2tu\b\13\1\2uv\7\32\2\2vx\b\13\1\2wy\5\f\7"+
		"\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\7\33\2\2}\u0089"+
		"\b\13\1\2~\177\7\13\2\2\177\u0080\7\32\2\2\u0080\u0082\b\13\1\2\u0081"+
		"\u0083\5\f\7\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2"+
		"\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\33\2\2\u0087"+
		"\u0088\b\13\1\2\u0088\u008a\3\2\2\2\u0089~\3\2\2\2\u0089\u008a\3\2\2\2"+
		"\u008a\u008b\3\2\2\2\u008b\u008c\b\13\1\2\u008c\25\3\2\2\2\u008d\u008e"+
		"\7\f\2\2\u008e\u008f\7\22\2\2\u008f\u0090\b\f\1\2\u0090\u0091\5 \21\2"+
		"\u0091\u0092\b\f\1\2\u0092\u0093\7\34\2\2\u0093\u0094\b\f\1\2\u0094\u0095"+
		"\5 \21\2\u0095\u0096\b\f\1\2\u0096\u0097\7\23\2\2\u0097\u0098\b\f\1\2"+
		"\u0098\u0099\7\32\2\2\u0099\u009b\b\f\1\2\u009a\u009c\5\f\7\2\u009b\u009a"+
		"\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u00a0\7\33\2\2\u00a0\u00a1\b\f\1\2\u00a1\27\3\2\2"+
		"\2\u00a2\u00a3\7\r\2\2\u00a3\u00a4\7\22\2\2\u00a4\u00a5\7\30\2\2\u00a5"+
		"\u00a6\b\r\1\2\u00a6\u00a7\7\23\2\2\u00a7\u00ba\7\32\2\2\u00a8\u00a9\7"+
		"\16\2\2\u00a9\u00aa\5 \21\2\u00aa\u00ab\b\r\1\2\u00ab\u00ac\7\25\2\2\u00ac"+
		"\u00b3\b\r\1\2\u00ad\u00b4\5\16\b\2\u00ae\u00b4\5\20\t\2\u00af\u00b4\5"+
		"\22\n\2\u00b0\u00b4\5\24\13\2\u00b1\u00b4\5\26\f\2\u00b2\u00b4\5\30\r"+
		"\2\u00b3\u00ad\3\2\2\2\u00b3\u00ae\3\2\2\2\u00b3\u00af\3\2\2\2\u00b3\u00b0"+
		"\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b9\5\32"+
		"\16\2\u00b8\u00b7\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba"+
		"\u00a8\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\7\17\2\2\u00bf\u00c0\b\r\1\2\u00c0"+
		"\u00c1\7\25\2\2\u00c1\u00c3\b\r\1\2\u00c2\u00c4\5\f\7\2\u00c3\u00c2\3"+
		"\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c8\7\33\2\2\u00c8\u00c9\b\r\1\2\u00c9\31\3\2\2"+
		"\2\u00ca\u00cb\7\20\2\2\u00cb\u00cc\7\24\2\2\u00cc\u00cd\b\16\1\2\u00cd"+
		"\33\3\2\2\2\u00ce\u00cf\7\21\2\2\u00cf\u00d0\7\24\2\2\u00d0\u00d1\b\17"+
		"\1\2\u00d1\35\3\2\2\2\u00d2\u00d8\5 \21\2\u00d3\u00d4\7\26\2\2\u00d4\u00d5"+
		"\b\20\1\2\u00d5\u00d7\5 \21\2\u00d6\u00d3\3\2\2\2\u00d7\u00da\3\2\2\2"+
		"\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\37\3\2\2\2\u00da\u00d8"+
		"\3\2\2\2\u00db\u00dc\7\30\2\2\u00dc\u00e4\b\21\1\2\u00dd\u00de\7\35\2"+
		"\2\u00de\u00e4\b\21\1\2\u00df\u00e0\7\36\2\2\u00e0\u00e4\b\21\1\2\u00e1"+
		"\u00e2\7\37\2\2\u00e2\u00e4\b\21\1\2\u00e3\u00db\3\2\2\2\u00e3\u00dd\3"+
		"\2\2\2\u00e3\u00df\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4!\3\2\2\2\22+\65@"+
		"FPz\u0084\u0089\u009d\u00b3\u00b5\u00b8\u00bc\u00c5\u00d8\u00e3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}