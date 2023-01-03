package model.adt;

import exception.InterpreterException;

public interface IExeStack<T> {
    void push(T elem);
    T pop() throws InterpreterException;
    boolean empty();
}
