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
    import br.com.isilanguage.ast.CommandEnquanto;
    import br.com.isilanguage.ast.CommandRepeticao;
    import br.com.isilanguage.ast.CommandSwitch;
    import br.com.isilanguage.ast.CommandBreak;
    import br.com.isilanguage.ast.CommandContinue;
    import br.com.isilanguage.ast.CommandType;
    
    import java.util.ArrayList;
    import java.util.Stack;
    import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, AP=22, FP=23, SC=24, DP=25, OP=26, 
		ATTR=27, ID=28, VIR=29, ACH=30, FCH=31, OPREL=32, NUMBER=33, TEXT=34, 
		BOOL=35, WS=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "AP", "FP", "SC", "DP", "OP", "ATTR", 
		"ID", "VIR", "ACH", "FCH", "OPREL", "NUMBER", "TEXT", "BOOL", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'logico'", "'leia'", 
		"'escreva'", "'se'", "'senao'", "'enquanto'", "'para'", "'de'", "'ate'", 
		"'passo'", "'faca'", "'fimpara'", "'escolha'", "'caso'", "'outrocaso'", 
		"'parar'", "'continuar'", "'('", "')'", "';'", "':'", null, "'='", null, 
		"','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "AP", "FP", 
		"SC", "DP", "OP", "ATTR", "ID", "VIR", "ACH", "FCH", "OPREL", "NUMBER", 
		"TEXT", "BOOL", "WS"
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

	    private String forStart;
	    private String forEnd;
	    private String forStep;

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

	    public ArrayList<String> getWarnings() {
	        ArrayList<String> warnings = new ArrayList<String>();
	        for (IsiSymbol symbol : symbolTable.getAll()) {
	            IsiVariable var = (IsiVariable) symbol;
	            String value = var.getValue();
	            if (value == null) {
	                String warn = IsiSemanticException.getWarning(Warning.UNASSIGNED_VARIABLE, var.getName());
	                warnings.add(warn);
	            } 
	        };

	        return warnings;
	    }

	    public void showCommands() {
	        for (AbstractCommand c: program.getCommands()) {
	            System.out.println(c);
	        }
	    }

	    public void generateCode(String fileName) {
	        program.generateTarget(fileName);
	    }


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u0125\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\34\3\34\3\35\3\35\7\35\u00e5\n\35\f\35\16\35\u00e8\13\35\3"+
		"\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u00f9\n!\3\"\6"+
		"\"\u00fc\n\"\r\"\16\"\u00fd\3\"\3\"\6\"\u0102\n\"\r\"\16\"\u0103\5\"\u0106"+
		"\n\"\3#\3#\7#\u010a\n#\f#\16#\u010d\13#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$"+
		"\3$\3$\3$\3$\3$\3$\3$\5$\u0120\n$\3%\3%\3%\3%\2\2&\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&\3\2"+
		"\n\5\2,-//\61\61\3\2c|\5\2\62;C\\c|\4\2>>@@\3\2\62;\3\2$$\n\2\"#%(*,."+
		".\60\60\62;A\\c|\5\2\13\f\17\17\"\"\2\u012e\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2"+
		"\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3K\3\2\2\2\5"+
		"T\3\2\2\2\7]\3\2\2\2\td\3\2\2\2\13j\3\2\2\2\rq\3\2\2\2\17v\3\2\2\2\21"+
		"~\3\2\2\2\23\u0081\3\2\2\2\25\u0087\3\2\2\2\27\u0090\3\2\2\2\31\u0095"+
		"\3\2\2\2\33\u0098\3\2\2\2\35\u009c\3\2\2\2\37\u00a2\3\2\2\2!\u00a7\3\2"+
		"\2\2#\u00af\3\2\2\2%\u00b7\3\2\2\2\'\u00bc\3\2\2\2)\u00c6\3\2\2\2+\u00cc"+
		"\3\2\2\2-\u00d6\3\2\2\2/\u00d8\3\2\2\2\61\u00da\3\2\2\2\63\u00dc\3\2\2"+
		"\2\65\u00de\3\2\2\2\67\u00e0\3\2\2\29\u00e2\3\2\2\2;\u00e9\3\2\2\2=\u00eb"+
		"\3\2\2\2?\u00ed\3\2\2\2A\u00f8\3\2\2\2C\u00fb\3\2\2\2E\u0107\3\2\2\2G"+
		"\u011f\3\2\2\2I\u0121\3\2\2\2KL\7r\2\2LM\7t\2\2MN\7q\2\2NO\7i\2\2OP\7"+
		"t\2\2PQ\7c\2\2QR\7o\2\2RS\7c\2\2S\4\3\2\2\2TU\7h\2\2UV\7k\2\2VW\7o\2\2"+
		"WX\7r\2\2XY\7t\2\2YZ\7q\2\2Z[\7i\2\2[\\\7=\2\2\\\6\3\2\2\2]^\7p\2\2^_"+
		"\7w\2\2_`\7o\2\2`a\7g\2\2ab\7t\2\2bc\7q\2\2c\b\3\2\2\2de\7v\2\2ef\7g\2"+
		"\2fg\7z\2\2gh\7v\2\2hi\7q\2\2i\n\3\2\2\2jk\7n\2\2kl\7q\2\2lm\7i\2\2mn"+
		"\7k\2\2no\7e\2\2op\7q\2\2p\f\3\2\2\2qr\7n\2\2rs\7g\2\2st\7k\2\2tu\7c\2"+
		"\2u\16\3\2\2\2vw\7g\2\2wx\7u\2\2xy\7e\2\2yz\7t\2\2z{\7g\2\2{|\7x\2\2|"+
		"}\7c\2\2}\20\3\2\2\2~\177\7u\2\2\177\u0080\7g\2\2\u0080\22\3\2\2\2\u0081"+
		"\u0082\7u\2\2\u0082\u0083\7g\2\2\u0083\u0084\7p\2\2\u0084\u0085\7c\2\2"+
		"\u0085\u0086\7q\2\2\u0086\24\3\2\2\2\u0087\u0088\7g\2\2\u0088\u0089\7"+
		"p\2\2\u0089\u008a\7s\2\2\u008a\u008b\7w\2\2\u008b\u008c\7c\2\2\u008c\u008d"+
		"\7p\2\2\u008d\u008e\7v\2\2\u008e\u008f\7q\2\2\u008f\26\3\2\2\2\u0090\u0091"+
		"\7r\2\2\u0091\u0092\7c\2\2\u0092\u0093\7t\2\2\u0093\u0094\7c\2\2\u0094"+
		"\30\3\2\2\2\u0095\u0096\7f\2\2\u0096\u0097\7g\2\2\u0097\32\3\2\2\2\u0098"+
		"\u0099\7c\2\2\u0099\u009a\7v\2\2\u009a\u009b\7g\2\2\u009b\34\3\2\2\2\u009c"+
		"\u009d\7r\2\2\u009d\u009e\7c\2\2\u009e\u009f\7u\2\2\u009f\u00a0\7u\2\2"+
		"\u00a0\u00a1\7q\2\2\u00a1\36\3\2\2\2\u00a2\u00a3\7h\2\2\u00a3\u00a4\7"+
		"c\2\2\u00a4\u00a5\7e\2\2\u00a5\u00a6\7c\2\2\u00a6 \3\2\2\2\u00a7\u00a8"+
		"\7h\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa\7o\2\2\u00aa\u00ab\7r\2\2\u00ab"+
		"\u00ac\7c\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7c\2\2\u00ae\"\3\2\2\2\u00af"+
		"\u00b0\7g\2\2\u00b0\u00b1\7u\2\2\u00b1\u00b2\7e\2\2\u00b2\u00b3\7q\2\2"+
		"\u00b3\u00b4\7n\2\2\u00b4\u00b5\7j\2\2\u00b5\u00b6\7c\2\2\u00b6$\3\2\2"+
		"\2\u00b7\u00b8\7e\2\2\u00b8\u00b9\7c\2\2\u00b9\u00ba\7u\2\2\u00ba\u00bb"+
		"\7q\2\2\u00bb&\3\2\2\2\u00bc\u00bd\7q\2\2\u00bd\u00be\7w\2\2\u00be\u00bf"+
		"\7v\2\2\u00bf\u00c0\7t\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2\7e\2\2\u00c2"+
		"\u00c3\7c\2\2\u00c3\u00c4\7u\2\2\u00c4\u00c5\7q\2\2\u00c5(\3\2\2\2\u00c6"+
		"\u00c7\7r\2\2\u00c7\u00c8\7c\2\2\u00c8\u00c9\7t\2\2\u00c9\u00ca\7c\2\2"+
		"\u00ca\u00cb\7t\2\2\u00cb*\3\2\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce\7q\2"+
		"\2\u00ce\u00cf\7p\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1\7k\2\2\u00d1\u00d2"+
		"\7p\2\2\u00d2\u00d3\7w\2\2\u00d3\u00d4\7c\2\2\u00d4\u00d5\7t\2\2\u00d5"+
		",\3\2\2\2\u00d6\u00d7\7*\2\2\u00d7.\3\2\2\2\u00d8\u00d9\7+\2\2\u00d9\60"+
		"\3\2\2\2\u00da\u00db\7=\2\2\u00db\62\3\2\2\2\u00dc\u00dd\7<\2\2\u00dd"+
		"\64\3\2\2\2\u00de\u00df\t\2\2\2\u00df\66\3\2\2\2\u00e0\u00e1\7?\2\2\u00e1"+
		"8\3\2\2\2\u00e2\u00e6\t\3\2\2\u00e3\u00e5\t\4\2\2\u00e4\u00e3\3\2\2\2"+
		"\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7:\3"+
		"\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ea\7.\2\2\u00ea<\3\2\2\2\u00eb\u00ec"+
		"\7}\2\2\u00ec>\3\2\2\2\u00ed\u00ee\7\177\2\2\u00ee@\3\2\2\2\u00ef\u00f9"+
		"\t\5\2\2\u00f0\u00f1\7@\2\2\u00f1\u00f9\7?\2\2\u00f2\u00f3\7>\2\2\u00f3"+
		"\u00f9\7?\2\2\u00f4\u00f5\7?\2\2\u00f5\u00f9\7?\2\2\u00f6\u00f7\7#\2\2"+
		"\u00f7\u00f9\7?\2\2\u00f8\u00ef\3\2\2\2\u00f8\u00f0\3\2\2\2\u00f8\u00f2"+
		"\3\2\2\2\u00f8\u00f4\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9B\3\2\2\2\u00fa"+
		"\u00fc\t\6\2\2\u00fb\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fb\3\2"+
		"\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0105\3\2\2\2\u00ff\u0101\7\60\2\2\u0100"+
		"\u0102\t\6\2\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2"+
		"\2\2\u0103\u0104\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u00ff\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106D\3\2\2\2\u0107\u010b\t\7\2\2\u0108\u010a\t\b\2\2"+
		"\u0109\u0108\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c"+
		"\3\2\2\2\u010c\u010e\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u010f\t\7\2\2\u010f"+
		"F\3\2\2\2\u0110\u0111\7x\2\2\u0111\u0112\7g\2\2\u0112\u0113\7t\2\2\u0113"+
		"\u0114\7f\2\2\u0114\u0115\7c\2\2\u0115\u0116\7f\2\2\u0116\u0117\7g\2\2"+
		"\u0117\u0118\7k\2\2\u0118\u0119\7t\2\2\u0119\u0120\7q\2\2\u011a\u011b"+
		"\7h\2\2\u011b\u011c\7c\2\2\u011c\u011d\7n\2\2\u011d\u011e\7u\2\2\u011e"+
		"\u0120\7q\2\2\u011f\u0110\3\2\2\2\u011f\u011a\3\2\2\2\u0120H\3\2\2\2\u0121"+
		"\u0122\t\t\2\2\u0122\u0123\3\2\2\2\u0123\u0124\b%\2\2\u0124J\3\2\2\2\13"+
		"\2\u00e4\u00e6\u00f8\u00fd\u0103\u0105\u010b\u011f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}