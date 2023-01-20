package model.adt;

import exception.InterpreterException;

import java.util.Stack;

public interface IExeStack<T> {
    void push(T elem);
    T pop() throws InterpreterException;
    boolean empty();
    Stack<T> getContent();
}
