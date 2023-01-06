package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.*;
import model.expression.IExpression;
import model.type.IType;
import model.type.StringType;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStatement {
    private final IExpression expr;

    public OpenRFile(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        IHeap<Integer, IValue> heap = prgState.getHeap();
        IFileTable<StringValue, BufferedReader> fileTable = prgState.getFileTable();

        IValue value = expr.evaluate(symTable, heap);
        if (!value.getType().equals(new StringType()))
            throw new InterpreterException("Invalid file path!");

        StringValue strValue = (StringValue)value;

        if (fileTable.isOpened(strValue))
            throw new InterpreterException("File is already opened!");

        String str = strValue.getValue();

        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(str));
            fileTable.put(strValue, bufferReader);
        } catch (FileNotFoundException e) {
            throw new InterpreterException(e.getMessage());
        }

        return null;
    }

    @Override
    public ITypeEnv<String, IType> typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        IType type = expr.typeCheck(typeEnv);
        if (!type.equals(new StringType()))
            throw new InterpreterException("Invalid file path!");
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenRFile(expr.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("openRFile(%s)", expr);
    }
}
