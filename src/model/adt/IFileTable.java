package model.adt;

import exception.InterpreterException;

import java.util.Map;

public interface IFileTable<K, V> {
    void put(K key, V value);
    void update(K key, V value) throws InterpreterException;
    void remove(K key) throws InterpreterException;
    V lookUp(K key);
    boolean isOpened(K key);
    Map<K, V> getContent();
}
