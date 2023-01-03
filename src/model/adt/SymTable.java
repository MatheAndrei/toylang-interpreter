package model.adt;

import exception.InterpreterException;

import java.util.*;

public class SymTable<K, V> implements ISymTable<K, V> {
    private final Map<K, V> map;

    public SymTable() {
        this.map = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public void update(K key, V value) throws InterpreterException {
        if (!map.containsKey(key))
            throw new InterpreterException("Variable " + key + " not defined!");
        map.put(key, value);
    }

    @Override
    public V lookUp(K key) {
        return map.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        return map.containsKey(key);
    }

    @Override
    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public ISymTable<K, V> deepCopy() {
        Map<K, V> mapCopy = new HashMap<>(map);
        ISymTable<K, V> symTable = new SymTable<>();
        symTable.getContent().putAll(mapCopy);
        return symTable;
    }

    @Override
    public String toString() {
        if (map.isEmpty())
            return "{}";

        List<K> keys = new ArrayList<>(map.keySet());

        StringBuilder str = new StringBuilder();
        str.append("{\n");
        for (int i = 0; i < keys.size() - 1; ++i) {
            K key = keys.get(i);
            V value = map.get(key);
            str.append(String.format("\t%s -> %s,\n", key, value));
        }
        K key = keys.get(keys.size() - 1);
        V value = map.get(key);
        str.append(String.format("\t%s -> %s,\n", key, value));
        str.append("}");

        return str.toString();
    }
}
