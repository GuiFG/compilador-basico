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
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, SC=16, DP=17, OP=18, 
		ATTR=19, ID=20, VIR=21, ACH=22, FCH=23, OPREL=24, NUMBER=25, TEXT=26, 
		BOOL=27, WS=28;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "AP", "FP", "SC", "DP", "OP", "ATTR", 
		"ID", "VIR", "ACH", "FCH", "OPREL", "NUMBER", "TEXT", "BOOL", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'logico'", "'leia'", 
		"'escreva'", "'se'", "'senao'", "'enquanto'", "'escolha'", "'caso'", "'outrocaso'", 
		"'('", "')'", "';'", "':'", null, "'='", null, "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "AP", "FP", "SC", "DP", "OP", "ATTR", "ID", "VIR", "ACH", 
		"FCH", "OPREL", "NUMBER", "TEXT", "BOOL", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\36\u00e6\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\7\25\u00a6\n\25\f\25\16\25\u00a9\13\25\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00ba"+
		"\n\31\3\32\6\32\u00bd\n\32\r\32\16\32\u00be\3\32\3\32\6\32\u00c3\n\32"+
		"\r\32\16\32\u00c4\5\32\u00c7\n\32\3\33\3\33\7\33\u00cb\n\33\f\33\16\33"+
		"\u00ce\13\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\5\34\u00e1\n\34\3\35\3\35\3\35\3\35\2\2\36"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36\3"+
		"\2\n\5\2,-//\61\61\3\2c|\5\2\62;C\\c|\4\2>>@@\3\2\62;\3\2$$\n\2\"#%(*"+
		",..\60\60\62;A\\c|\5\2\13\f\17\17\"\"\2\u00ef\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5D\3\2\2\2\7M\3\2"+
		"\2\2\tT\3\2\2\2\13Z\3\2\2\2\ra\3\2\2\2\17f\3\2\2\2\21n\3\2\2\2\23q\3\2"+
		"\2\2\25w\3\2\2\2\27\u0080\3\2\2\2\31\u0088\3\2\2\2\33\u008d\3\2\2\2\35"+
		"\u0097\3\2\2\2\37\u0099\3\2\2\2!\u009b\3\2\2\2#\u009d\3\2\2\2%\u009f\3"+
		"\2\2\2\'\u00a1\3\2\2\2)\u00a3\3\2\2\2+\u00aa\3\2\2\2-\u00ac\3\2\2\2/\u00ae"+
		"\3\2\2\2\61\u00b9\3\2\2\2\63\u00bc\3\2\2\2\65\u00c8\3\2\2\2\67\u00e0\3"+
		"\2\2\29\u00e2\3\2\2\2;<\7r\2\2<=\7t\2\2=>\7q\2\2>?\7i\2\2?@\7t\2\2@A\7"+
		"c\2\2AB\7o\2\2BC\7c\2\2C\4\3\2\2\2DE\7h\2\2EF\7k\2\2FG\7o\2\2GH\7r\2\2"+
		"HI\7t\2\2IJ\7q\2\2JK\7i\2\2KL\7=\2\2L\6\3\2\2\2MN\7p\2\2NO\7w\2\2OP\7"+
		"o\2\2PQ\7g\2\2QR\7t\2\2RS\7q\2\2S\b\3\2\2\2TU\7v\2\2UV\7g\2\2VW\7z\2\2"+
		"WX\7v\2\2XY\7q\2\2Y\n\3\2\2\2Z[\7n\2\2[\\\7q\2\2\\]\7i\2\2]^\7k\2\2^_"+
		"\7e\2\2_`\7q\2\2`\f\3\2\2\2ab\7n\2\2bc\7g\2\2cd\7k\2\2de\7c\2\2e\16\3"+
		"\2\2\2fg\7g\2\2gh\7u\2\2hi\7e\2\2ij\7t\2\2jk\7g\2\2kl\7x\2\2lm\7c\2\2"+
		"m\20\3\2\2\2no\7u\2\2op\7g\2\2p\22\3\2\2\2qr\7u\2\2rs\7g\2\2st\7p\2\2"+
		"tu\7c\2\2uv\7q\2\2v\24\3\2\2\2wx\7g\2\2xy\7p\2\2yz\7s\2\2z{\7w\2\2{|\7"+
		"c\2\2|}\7p\2\2}~\7v\2\2~\177\7q\2\2\177\26\3\2\2\2\u0080\u0081\7g\2\2"+
		"\u0081\u0082\7u\2\2\u0082\u0083\7e\2\2\u0083\u0084\7q\2\2\u0084\u0085"+
		"\7n\2\2\u0085\u0086\7j\2\2\u0086\u0087\7c\2\2\u0087\30\3\2\2\2\u0088\u0089"+
		"\7e\2\2\u0089\u008a\7c\2\2\u008a\u008b\7u\2\2\u008b\u008c\7q\2\2\u008c"+
		"\32\3\2\2\2\u008d\u008e\7q\2\2\u008e\u008f\7w\2\2\u008f\u0090\7v\2\2\u0090"+
		"\u0091\7t\2\2\u0091\u0092\7q\2\2\u0092\u0093\7e\2\2\u0093\u0094\7c\2\2"+
		"\u0094\u0095\7u\2\2\u0095\u0096\7q\2\2\u0096\34\3\2\2\2\u0097\u0098\7"+
		"*\2\2\u0098\36\3\2\2\2\u0099\u009a\7+\2\2\u009a \3\2\2\2\u009b\u009c\7"+
		"=\2\2\u009c\"\3\2\2\2\u009d\u009e\7<\2\2\u009e$\3\2\2\2\u009f\u00a0\t"+
		"\2\2\2\u00a0&\3\2\2\2\u00a1\u00a2\7?\2\2\u00a2(\3\2\2\2\u00a3\u00a7\t"+
		"\3\2\2\u00a4\u00a6\t\4\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8*\3\2\2\2\u00a9\u00a7\3\2\2\2"+
		"\u00aa\u00ab\7.\2\2\u00ab,\3\2\2\2\u00ac\u00ad\7}\2\2\u00ad.\3\2\2\2\u00ae"+
		"\u00af\7\177\2\2\u00af\60\3\2\2\2\u00b0\u00ba\t\5\2\2\u00b1\u00b2\7@\2"+
		"\2\u00b2\u00ba\7?\2\2\u00b3\u00b4\7>\2\2\u00b4\u00ba\7?\2\2\u00b5\u00b6"+
		"\7?\2\2\u00b6\u00ba\7?\2\2\u00b7\u00b8\7#\2\2\u00b8\u00ba\7?\2\2\u00b9"+
		"\u00b0\3\2\2\2\u00b9\u00b1\3\2\2\2\u00b9\u00b3\3\2\2\2\u00b9\u00b5\3\2"+
		"\2\2\u00b9\u00b7\3\2\2\2\u00ba\62\3\2\2\2\u00bb\u00bd\t\6\2\2\u00bc\u00bb"+
		"\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c6\3\2\2\2\u00c0\u00c2\7\60\2\2\u00c1\u00c3\t\6\2\2\u00c2\u00c1\3"+
		"\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5"+
		"\u00c7\3\2\2\2\u00c6\u00c0\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\64\3\2\2"+
		"\2\u00c8\u00cc\t\7\2\2\u00c9\u00cb\t\b\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce"+
		"\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce"+
		"\u00cc\3\2\2\2\u00cf\u00d0\t\7\2\2\u00d0\66\3\2\2\2\u00d1\u00d2\7x\2\2"+
		"\u00d2\u00d3\7g\2\2\u00d3\u00d4\7t\2\2\u00d4\u00d5\7f\2\2\u00d5\u00d6"+
		"\7c\2\2\u00d6\u00d7\7f\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9\7k\2\2\u00d9"+
		"\u00da\7t\2\2\u00da\u00e1\7q\2\2\u00db\u00dc\7h\2\2\u00dc\u00dd\7c\2\2"+
		"\u00dd\u00de\7n\2\2\u00de\u00df\7u\2\2\u00df\u00e1\7q\2\2\u00e0\u00d1"+
		"\3\2\2\2\u00e0\u00db\3\2\2\2\u00e18\3\2\2\2\u00e2\u00e3\t\t\2\2\u00e3"+
		"\u00e4\3\2\2\2\u00e4\u00e5\b\35\2\2\u00e5:\3\2\2\2\13\2\u00a5\u00a7\u00b9"+
		"\u00be\u00c4\u00c6\u00cc\u00e0\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}