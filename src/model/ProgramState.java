package model;

import exception.InterpreterException;
import model.adt.*;
import model.statement.IStatement;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    private final int id;
    private IExeStack<IStatement> exeStack;
    private ISymTable<String, IValue> symTable;
    private IOutput<IValue> output;
    private IFileTable<StringValue, BufferedReader> fileTable;
    private IHeap<Integer, IValue> heap;
    private IStatement originalProgram;
    static private int numProgramStates = 0;

    public ProgramState(
            IExeStack<IStatement> exeStack,
            ISymTable<String, IValue> symTable,
            IOutput<IValue> output,
            IFileTable<StringValue, BufferedReader> fileTable,
            IHeap<Integer, IValue> heap,
            IStatement program
    ) {
        this.id = generateNewId();
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = program.deepCopy();
        this.exeStack.push(program);
    }

    public int getId() {
        return id;
    }

    public IExeStack<IStatement> getExeStack() {
        return exeStack;
    }

    public ISymTable<String, IValue> getSymTable() {
        return symTable;
    }

    public IOutput<IValue> getOutput() {
        return output;
    }

    public IFileTable<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IHeap<Integer, IValue> getHeap() {
        return heap;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setExeStack(IExeStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(ISymTable<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public void setOutput(IOutput<IValue> output) {
        this.output = output;
    }

    public void setFileTable(IFileTable<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setHeap(IHeap<Integer, IValue> heap) {
        this.heap = heap;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public boolean isCompleted() {
        return exeStack.empty();
    }

    static synchronized private int generateNewId() {
        return numProgramStates++;
    }

    public ProgramState oneStep() throws InterpreterException {
        if (exeStack.empty())
            throw new InterpreterException("Program state execution stack is empty!");
        IStatement currentStmt = exeStack.pop();
        return currentStmt.execute(this);
    }

    @Override
    public String toString() {
        return String.format(
                "Id: %s\nExeStack: %s\nSymTable: %s\nHeap: %s\nOutput: %s\nFileTable: %s",
                id, exeStack, symTable, heap, output, fileTable
        );
    }
}
