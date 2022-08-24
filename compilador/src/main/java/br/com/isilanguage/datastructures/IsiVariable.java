package br.com.isilanguage.datastructures;


public class IsiVariable extends IsiSymbol {
    public static final int NUMBER = 0;
    public static final int TEXT = 1;
    public static final int BOOL = 2;
    
    private int type;
    private String value;
    private Boolean inline;
    private Boolean last;
    
    public IsiVariable(String name, int type, String value, Boolean inline) {
        super(name);
        this.type = type;
        this.value = value;
        this.inline = inline;
        this.last = false;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public Boolean getLast() {
        return this.last;
    }
    
    public void setLast(Boolean value) {
        this.last = value;
    }
    
    @Override
    public String toString() {
        return "IsiVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
    }

    @Override
    public String generateCodeInCpp() {
        String str;
        if (!this.inline)
        {
            str = switch (type) {
                case IsiVariable.NUMBER -> "double " + super.name;
                case IsiVariable.TEXT -> "string " + super.name;
                case IsiVariable.BOOL -> "bool " + super.name;
                default -> "tipo desconhecido " + type;
            };
        }
        else {
            str = ", " + super.name;
        }
        
        str = this.last ?  str + ";\r" : str;
        return str;
    }
    
    @Override
    public String generateCodeInJava() {
        String str;
        if (!this.inline)
        {
            str = switch (type) {
                case IsiVariable.NUMBER -> "double " + super.name;
                case IsiVariable.TEXT -> "String " + super.name;
                case IsiVariable.BOOL -> "boolean " + super.name;
                default -> "tipo desconhecido " + type;
            };
        }
        else {
            str = ", " + super.name;
        }
        
        str = this.last ?  str + ";\r" : str;
        return str;
    }
}
