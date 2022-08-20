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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, AP=15, FP=16, SC=17, 
		DP=18, OP=19, ATTR=20, ID=21, VIR=22, ACH=23, FCH=24, OPREL=25, NUMBER=26, 
		TEXT=27, BOOL=28, WS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "AP", "FP", "SC", "DP", "OP", 
		"ATTR", "ID", "VIR", "ACH", "FCH", "OPREL", "NUMBER", "TEXT", "BOOL", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'logico'", "'leia'", 
		"'escreva'", "'se'", "'senao'", "'enquanto'", "'escolha'", "'caso'", "'parar'", 
		"'outrocaso'", "'('", "')'", "';'", "':'", null, "'='", null, "','", "'{'", 
		"'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "AP", "FP", "SC", "DP", "OP", "ATTR", "ID", "VIR", "ACH", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u00ee\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\7\26\u00ae\n\26"+
		"\f\26\16\26\u00b1\13\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\5\32\u00c2\n\32\3\33\6\33\u00c5\n\33\r\33"+
		"\16\33\u00c6\3\33\3\33\6\33\u00cb\n\33\r\33\16\33\u00cc\5\33\u00cf\n\33"+
		"\3\34\3\34\7\34\u00d3\n\34\f\34\16\34\u00d6\13\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35"+
		"\u00e9\n\35\3\36\3\36\3\36\3\36\2\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37\3\2\n\5\2,-//\61\61\3\2c|\5\2\62"+
		";C\\c|\4\2>>@@\3\2\62;\3\2$$\n\2\"#%(*,..\60\60\62;A\\c|\5\2\13\f\17\17"+
		"\"\"\2\u00f7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5F\3\2\2\2\7O\3\2\2\2\tV\3\2\2\2\13\\\3\2"+
		"\2\2\rc\3\2\2\2\17h\3\2\2\2\21p\3\2\2\2\23s\3\2\2\2\25y\3\2\2\2\27\u0082"+
		"\3\2\2\2\31\u008a\3\2\2\2\33\u008f\3\2\2\2\35\u0095\3\2\2\2\37\u009f\3"+
		"\2\2\2!\u00a1\3\2\2\2#\u00a3\3\2\2\2%\u00a5\3\2\2\2\'\u00a7\3\2\2\2)\u00a9"+
		"\3\2\2\2+\u00ab\3\2\2\2-\u00b2\3\2\2\2/\u00b4\3\2\2\2\61\u00b6\3\2\2\2"+
		"\63\u00c1\3\2\2\2\65\u00c4\3\2\2\2\67\u00d0\3\2\2\29\u00e8\3\2\2\2;\u00ea"+
		"\3\2\2\2=>\7r\2\2>?\7t\2\2?@\7q\2\2@A\7i\2\2AB\7t\2\2BC\7c\2\2CD\7o\2"+
		"\2DE\7c\2\2E\4\3\2\2\2FG\7h\2\2GH\7k\2\2HI\7o\2\2IJ\7r\2\2JK\7t\2\2KL"+
		"\7q\2\2LM\7i\2\2MN\7=\2\2N\6\3\2\2\2OP\7p\2\2PQ\7w\2\2QR\7o\2\2RS\7g\2"+
		"\2ST\7t\2\2TU\7q\2\2U\b\3\2\2\2VW\7v\2\2WX\7g\2\2XY\7z\2\2YZ\7v\2\2Z["+
		"\7q\2\2[\n\3\2\2\2\\]\7n\2\2]^\7q\2\2^_\7i\2\2_`\7k\2\2`a\7e\2\2ab\7q"+
		"\2\2b\f\3\2\2\2cd\7n\2\2de\7g\2\2ef\7k\2\2fg\7c\2\2g\16\3\2\2\2hi\7g\2"+
		"\2ij\7u\2\2jk\7e\2\2kl\7t\2\2lm\7g\2\2mn\7x\2\2no\7c\2\2o\20\3\2\2\2p"+
		"q\7u\2\2qr\7g\2\2r\22\3\2\2\2st\7u\2\2tu\7g\2\2uv\7p\2\2vw\7c\2\2wx\7"+
		"q\2\2x\24\3\2\2\2yz\7g\2\2z{\7p\2\2{|\7s\2\2|}\7w\2\2}~\7c\2\2~\177\7"+
		"p\2\2\177\u0080\7v\2\2\u0080\u0081\7q\2\2\u0081\26\3\2\2\2\u0082\u0083"+
		"\7g\2\2\u0083\u0084\7u\2\2\u0084\u0085\7e\2\2\u0085\u0086\7q\2\2\u0086"+
		"\u0087\7n\2\2\u0087\u0088\7j\2\2\u0088\u0089\7c\2\2\u0089\30\3\2\2\2\u008a"+
		"\u008b\7e\2\2\u008b\u008c\7c\2\2\u008c\u008d\7u\2\2\u008d\u008e\7q\2\2"+
		"\u008e\32\3\2\2\2\u008f\u0090\7r\2\2\u0090\u0091\7c\2\2\u0091\u0092\7"+
		"t\2\2\u0092\u0093\7c\2\2\u0093\u0094\7t\2\2\u0094\34\3\2\2\2\u0095\u0096"+
		"\7q\2\2\u0096\u0097\7w\2\2\u0097\u0098\7v\2\2\u0098\u0099\7t\2\2\u0099"+
		"\u009a\7q\2\2\u009a\u009b\7e\2\2\u009b\u009c\7c\2\2\u009c\u009d\7u\2\2"+
		"\u009d\u009e\7q\2\2\u009e\36\3\2\2\2\u009f\u00a0\7*\2\2\u00a0 \3\2\2\2"+
		"\u00a1\u00a2\7+\2\2\u00a2\"\3\2\2\2\u00a3\u00a4\7=\2\2\u00a4$\3\2\2\2"+
		"\u00a5\u00a6\7<\2\2\u00a6&\3\2\2\2\u00a7\u00a8\t\2\2\2\u00a8(\3\2\2\2"+
		"\u00a9\u00aa\7?\2\2\u00aa*\3\2\2\2\u00ab\u00af\t\3\2\2\u00ac\u00ae\t\4"+
		"\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0,\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\7.\2\2\u00b3"+
		".\3\2\2\2\u00b4\u00b5\7}\2\2\u00b5\60\3\2\2\2\u00b6\u00b7\7\177\2\2\u00b7"+
		"\62\3\2\2\2\u00b8\u00c2\t\5\2\2\u00b9\u00ba\7@\2\2\u00ba\u00c2\7?\2\2"+
		"\u00bb\u00bc\7>\2\2\u00bc\u00c2\7?\2\2\u00bd\u00be\7?\2\2\u00be\u00c2"+
		"\7?\2\2\u00bf\u00c0\7#\2\2\u00c0\u00c2\7?\2\2\u00c1\u00b8\3\2\2\2\u00c1"+
		"\u00b9\3\2\2\2\u00c1\u00bb\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c2\64\3\2\2\2\u00c3\u00c5\t\6\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6"+
		"\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00ce\3\2\2\2\u00c8"+
		"\u00ca\7\60\2\2\u00c9\u00cb\t\6\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00cc\3"+
		"\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce"+
		"\u00c8\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\66\3\2\2\2\u00d0\u00d4\t\7\2"+
		"\2\u00d1\u00d3\t\b\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2"+
		"\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7"+
		"\u00d8\t\7\2\2\u00d88\3\2\2\2\u00d9\u00da\7x\2\2\u00da\u00db\7g\2\2\u00db"+
		"\u00dc\7t\2\2\u00dc\u00dd\7f\2\2\u00dd\u00de\7c\2\2\u00de\u00df\7f\2\2"+
		"\u00df\u00e0\7g\2\2\u00e0\u00e1\7k\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e9"+
		"\7q\2\2\u00e3\u00e4\7h\2\2\u00e4\u00e5\7c\2\2\u00e5\u00e6\7n\2\2\u00e6"+
		"\u00e7\7u\2\2\u00e7\u00e9\7q\2\2\u00e8\u00d9\3\2\2\2\u00e8\u00e3\3\2\2"+
		"\2\u00e9:\3\2\2\2\u00ea\u00eb\t\t\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed"+
		"\b\36\2\2\u00ed<\3\2\2\2\13\2\u00ad\u00af\u00c1\u00c6\u00cc\u00ce\u00d4"+
		"\u00e8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}