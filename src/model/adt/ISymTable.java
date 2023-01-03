package model.adt;

import exception.InterpreterException;

import java.util.Map;

public interface ISymTable<K, V> {
    void put(K key, V value);
    void update(K key, V value) throws InterpreterException;
    V lookUp(K key);
    boolean isDefined(K key);
    Map<K, V> getContent();
    ISymTable<K, V> deepCopy();
}
