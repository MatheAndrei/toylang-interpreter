package model.adt;

import exception.InterpreterException;

import java.util.Map;

public interface IHeap<K, V> {
    K allocate(V value) throws InterpreterException;
    void update(K key, V value) throws InterpreterException;
    void deallocate(K key) throws InterpreterException;
    V lookUp(K key);
    boolean isAllocated(K key);
    Map<K, V> getContent();
    void setContent(Map<K, V> map);
}
