package model.adt;

import exception.InterpreterException;

import java.util.Map;

public interface ITypeEnv<K, T> {
    void put(K key, T value);
    T lookUp(K key);
    Map<K, T> getContent();
    ITypeEnv<K, T> deepCopy();
}
