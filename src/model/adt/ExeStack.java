package model.adt;

import exception.InterpreterException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ExeStack<T> implements IExeStack<T> {
    private final Stack<T> stack;

    public ExeStack() {
        this.stack = new Stack<>();
    }

    @Override
    public void push(T elem) {
        stack.push(elem);
    }

    @Override
    public T pop() throws InterpreterException {
        if (stack.empty())
            throw new InterpreterException("Empty execution stack!");
        return stack.pop();
    }

    @Override
    public boolean empty() {
        return stack.empty();
    }

    @Override
    public Stack<T> getContent() {
        return stack;
    }

    @Override
    public String toString() {
        if (stack.empty())
            return "[]";

        List<T> list = new ArrayList<>(stack);
        Collections.reverse(list);

        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < list.size() - 1; ++i) {
            T elem = list.get(i);
            str.append(elem);
            str.append(" | ");
        }
        str.append(list.get(list.size() - 1));
        str.append("]");

        return str.toString();
    }
}
