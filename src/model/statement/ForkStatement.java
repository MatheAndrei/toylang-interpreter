package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.*;
import model.type.IType;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;

public class ForkStatement implements IStatement {
    private final IStatement stmt;

    public ForkStatement(IStatement stmt) {
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        IExeStack<IStatement> exeStack = new ExeStack<>();
        ISymTable<String, IValue> symTable = prgState.getSymTable().deepCopy();
        IOutput<IValue> output = prgState.getOutput();
        IFileTable<StringValue, BufferedReader> fileTable = prgState.getFileTable();
        IHeap<Integer, IValue> heap = prgState.getHeap();
        IStatement program = stmt.deepCopy();

        ProgramState thread = new ProgramState(
                exeStack,
                symTable,
                output,
                fileTable,
                heap,
                program
        );

        return thread;
    }

    @Override
    public ITypeEnv<String, IType> typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        stmt.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(stmt.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("fork(%s)", stmt);
    }
}
