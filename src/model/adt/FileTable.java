package model.adt;

import exception.InterpreterException;

import java.util.HashMap;
import java.util.Map;

public class FileTable<K, V> implements IFileTable<K, V> {
    private final Map<K, V> map;

    public FileTable() {
        this.map = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public void update(K key, V value) throws InterpreterException {
        if (!map.containsKey(key))
            throw new InterpreterException("File descriptor " + key + " doesn't exist!");
        map.put(key, value);
    }

    @Override
    public void remove(K key) throws InterpreterException {
        if (!map.containsKey(key))
            throw new InterpreterException("File descriptor " + key + " doesn't exist!");
        map.remove(key);
    }

    @Override
    public V lookUp(K key) {
        return map.get(key);
    }

    @Override
    public boolean isOpened(K key) {
        return map.containsKey(key);
    }

    @Override
    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public String toString() {
        return map.keySet().toString();
    }
}
