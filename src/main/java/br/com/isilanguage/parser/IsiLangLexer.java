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
    
    import java.util.ArrayList;
    import java.util.Stack;

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
		T__9=10, AP=11, FP=12, SC=13, OP=14, ATTR=15, ID=16, VIR=17, ACH=18, FCH=19, 
		OPREL=20, NUMBER=21, TEXT=22, BOOL=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "AP", "FP", "SC", "OP", "ATTR", "ID", "VIR", "ACH", "FCH", "OPREL", 
		"NUMBER", "TEXT", "BOOL", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'logico'", "'leia'", 
		"'escreva'", "'se'", "'senao'", "'enquanto'", "'('", "')'", "';'", null, 
		"'='", null, "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "AP", 
		"FP", "SC", "OP", "ATTR", "ID", "VIR", "ACH", "FCH", "OPREL", "NUMBER", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00c5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\7\21"+
		"\u0085\n\21\f\21\16\21\u0088\13\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0099\n\25\3\26\6\26\u009c"+
		"\n\26\r\26\16\26\u009d\3\26\3\26\6\26\u00a2\n\26\r\26\16\26\u00a3\5\26"+
		"\u00a6\n\26\3\27\3\27\7\27\u00aa\n\27\f\27\16\27\u00ad\13\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\5\30\u00c0\n\30\3\31\3\31\3\31\3\31\2\2\32\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\3\2\n\5\2,-//\61\61\3\2c|\5\2\62;C\\c|\4\2>>@@"+
		"\3\2\62;\3\2$$\n\2\"#%(*,..\60\60\62;A\\c|\5\2\13\f\17\17\"\"\2\u00ce"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\3\63\3\2\2\2\5<\3\2\2\2\7E\3\2\2\2\tL\3\2\2\2\13R\3\2"+
		"\2\2\rY\3\2\2\2\17^\3\2\2\2\21f\3\2\2\2\23i\3\2\2\2\25o\3\2\2\2\27x\3"+
		"\2\2\2\31z\3\2\2\2\33|\3\2\2\2\35~\3\2\2\2\37\u0080\3\2\2\2!\u0082\3\2"+
		"\2\2#\u0089\3\2\2\2%\u008b\3\2\2\2\'\u008d\3\2\2\2)\u0098\3\2\2\2+\u009b"+
		"\3\2\2\2-\u00a7\3\2\2\2/\u00bf\3\2\2\2\61\u00c1\3\2\2\2\63\64\7r\2\2\64"+
		"\65\7t\2\2\65\66\7q\2\2\66\67\7i\2\2\678\7t\2\289\7c\2\29:\7o\2\2:;\7"+
		"c\2\2;\4\3\2\2\2<=\7h\2\2=>\7k\2\2>?\7o\2\2?@\7r\2\2@A\7t\2\2AB\7q\2\2"+
		"BC\7i\2\2CD\7=\2\2D\6\3\2\2\2EF\7p\2\2FG\7w\2\2GH\7o\2\2HI\7g\2\2IJ\7"+
		"t\2\2JK\7q\2\2K\b\3\2\2\2LM\7v\2\2MN\7g\2\2NO\7z\2\2OP\7v\2\2PQ\7q\2\2"+
		"Q\n\3\2\2\2RS\7n\2\2ST\7q\2\2TU\7i\2\2UV\7k\2\2VW\7e\2\2WX\7q\2\2X\f\3"+
		"\2\2\2YZ\7n\2\2Z[\7g\2\2[\\\7k\2\2\\]\7c\2\2]\16\3\2\2\2^_\7g\2\2_`\7"+
		"u\2\2`a\7e\2\2ab\7t\2\2bc\7g\2\2cd\7x\2\2de\7c\2\2e\20\3\2\2\2fg\7u\2"+
		"\2gh\7g\2\2h\22\3\2\2\2ij\7u\2\2jk\7g\2\2kl\7p\2\2lm\7c\2\2mn\7q\2\2n"+
		"\24\3\2\2\2op\7g\2\2pq\7p\2\2qr\7s\2\2rs\7w\2\2st\7c\2\2tu\7p\2\2uv\7"+
		"v\2\2vw\7q\2\2w\26\3\2\2\2xy\7*\2\2y\30\3\2\2\2z{\7+\2\2{\32\3\2\2\2|"+
		"}\7=\2\2}\34\3\2\2\2~\177\t\2\2\2\177\36\3\2\2\2\u0080\u0081\7?\2\2\u0081"+
		" \3\2\2\2\u0082\u0086\t\3\2\2\u0083\u0085\t\4\2\2\u0084\u0083\3\2\2\2"+
		"\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\"\3"+
		"\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a\7.\2\2\u008a$\3\2\2\2\u008b\u008c"+
		"\7}\2\2\u008c&\3\2\2\2\u008d\u008e\7\177\2\2\u008e(\3\2\2\2\u008f\u0099"+
		"\t\5\2\2\u0090\u0091\7@\2\2\u0091\u0099\7?\2\2\u0092\u0093\7>\2\2\u0093"+
		"\u0099\7?\2\2\u0094\u0095\7?\2\2\u0095\u0099\7?\2\2\u0096\u0097\7#\2\2"+
		"\u0097\u0099\7?\2\2\u0098\u008f\3\2\2\2\u0098\u0090\3\2\2\2\u0098\u0092"+
		"\3\2\2\2\u0098\u0094\3\2\2\2\u0098\u0096\3\2\2\2\u0099*\3\2\2\2\u009a"+
		"\u009c\t\6\2\2\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009d\u009e\3\2\2\2\u009e\u00a5\3\2\2\2\u009f\u00a1\7\60\2\2\u00a0"+
		"\u00a2\t\6\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2"+
		"\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u009f\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6,\3\2\2\2\u00a7\u00ab\t\7\2\2\u00a8\u00aa\t\b\2\2"+
		"\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac"+
		"\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\t\7\2\2\u00af"+
		".\3\2\2\2\u00b0\u00b1\7x\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7t\2\2\u00b3"+
		"\u00b4\7f\2\2\u00b4\u00b5\7c\2\2\u00b5\u00b6\7f\2\2\u00b6\u00b7\7g\2\2"+
		"\u00b7\u00b8\7k\2\2\u00b8\u00b9\7t\2\2\u00b9\u00c0\7q\2\2\u00ba\u00bb"+
		"\7h\2\2\u00bb\u00bc\7c\2\2\u00bc\u00bd\7n\2\2\u00bd\u00be\7u\2\2\u00be"+
		"\u00c0\7q\2\2\u00bf\u00b0\3\2\2\2\u00bf\u00ba\3\2\2\2\u00c0\60\3\2\2\2"+
		"\u00c1\u00c2\t\t\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\b\31\2\2\u00c4\62"+
		"\3\2\2\2\13\2\u0084\u0086\u0098\u009d\u00a3\u00a5\u00ab\u00bf\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}