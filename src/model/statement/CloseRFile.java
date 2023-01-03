package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.IFileTable;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.expression.IExpression;
import model.type.IType;
import model.type.StringType;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    private final IExpression expr;

    public CloseRFile(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        IHeap<Integer, IValue> heap = prgState.getHeap();
        IFileTable<StringValue, BufferedReader> fileTable = prgState.getFileTable();

        IValue value = expr.evaluate(symTable, heap);
        IType type = value.getType();
        if (!type.equals(new StringType()))
            throw new InterpreterException("Invalid file name!");

        StringValue fileName = (StringValue)value;
        if (!fileTable.isOpened(fileName))
            throw new InterpreterException("File " + fileName + " is not opened!");

        BufferedReader bufferedReader = fileTable.lookUp(fileName);

        try {
            bufferedReader.close();
            fileTable.remove(fileName);
        } catch (IOException e) {
            throw new InterpreterException(e.getMessage());
        }

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseRFile(expr.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("closeRFile(%s)", expr);
    }
}
