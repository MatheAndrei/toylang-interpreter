package model.adt;

import exception.InterpreterException;
import model.value.IValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Heap implements IHeap<Integer, IValue> {
    private Map<Integer, IValue> map;
    private Integer freeAddress;

    public Heap() {
        this.map = new HashMap<>();
        freeAddress = 1;
    }

    private Integer getFreeAddress() {
        while (map.containsKey(freeAddress))
            freeAddress++;
        return freeAddress;
    }

    @Override
    public Integer allocate(IValue value) throws InterpreterException {
        Integer address = getFreeAddress();
        if (address == 0)
            throw new InterpreterException("Heap is full!");
        map.put(address, value);
        return address;
    }

    @Override
    public void update(Integer key, IValue value) throws InterpreterException {
        if (!map.containsKey(key))
            throw new InterpreterException("Variable " + key + " isn't allocated on heap!");
        map.put(key, value);
    }

    @Override
    public void deallocate(Integer key) throws InterpreterException {
        if (!map.containsKey(key))
            throw new InterpreterException("Variable " + key + " isn't allocated on heap!");
        map.remove(key);
    }

    @Override
    public IValue lookUp(Integer key) {
        return map.get(key);
    }

    @Override
    public boolean isAllocated(Integer key) {
        return map.containsKey(key);
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<Integer, IValue> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        if (map.isEmpty())
            return "{}";

        List<Integer> keys = new ArrayList<>(map.keySet());

        StringBuilder str = new StringBuilder();
        str.append("{\n");
        for (int i = 0; i < keys.size() - 1; ++i) {
            Integer key = keys.get(i);
            IValue value = map.get(key);
            str.append(String.format("\t%s -> %s,\n", key, value));
        }
        Integer key = keys.get(keys.size() - 1);
        IValue value = map.get(key);
        str.append(String.format("\t%s -> %s,\n", key, value));
        str.append("}");

        return str.toString();
    }
}
