package model.adt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeEnv<K, T> implements ITypeEnv<K, T> {
    private final Map<K, T> map;

    public TypeEnv() {
        this.map = new HashMap<>();
    }

    @Override
    public void put(K key, T value) {
        map.put(key, value);
    }

    @Override
    public T lookUp(K key) {
        return map.get(key);
    }

    @Override
    public Map<K, T> getContent() {
        return map;
    }

    @Override
    public ITypeEnv<K, T> deepCopy() {
        Map<K, T> mapCopy = new HashMap<>(map);
        ITypeEnv<K, T> typeEnv = new TypeEnv<>();
        typeEnv.getContent().putAll(mapCopy);
        return typeEnv;
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
            T type = map.get(key);
            str.append(String.format("\t%s -> %s,\n", key, type));
        }
        K key = keys.get(keys.size() - 1);
        T type = map.get(key);
        str.append(String.format("\t%s -> %s,\n", key, type));
        str.append("}");

        return str.toString();
    }
}
