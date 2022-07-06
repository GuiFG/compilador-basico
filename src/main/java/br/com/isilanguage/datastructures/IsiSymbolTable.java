package br.com.isilanguage.datastructures;

import java.util.ArrayList;
import java.util.HashMap;

public class IsiSymbolTable {
    private final HashMap<String, IsiSymbol> map;
    
    public IsiSymbolTable() {
        map = new HashMap<>();
    }
    
    public void add(IsiSymbol symbol) {
        map.put(symbol.getName(), symbol);
    }
    
    public boolean exists(String symbolName) {
        return map.get(symbolName) != null;
    }
    
    public IsiSymbol get(String symbolName) {
        return map.get(symbolName);
    }
    
    public ArrayList<IsiSymbol> getAll() {
        ArrayList<IsiSymbol> list = new ArrayList<>();
        for (IsiSymbol symbol : map.values()) {
            list.add(symbol);
        }
        
        return list;
    }
}
