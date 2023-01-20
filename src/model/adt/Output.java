package model.adt;

import java.util.ArrayList;
import java.util.List;

public class Output<T> implements IOutput<T> {
    private final List<T> list;

    public Output() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public List<T> getContent() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
