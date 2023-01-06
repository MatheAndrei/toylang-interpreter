package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.IFileTable;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.adt.ITypeEnv;
import model.expression.IExpression;
import model.type.IType;
import model.type.IntType;
import model.type.StringType;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private final IExpression expr;
    private final String id;

    public ReadFile(IExpression expr, String id) {
        this.expr = expr;
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        IHeap<Integer, IValue> heap = prgState.getHeap();
        IFileTable<StringValue, BufferedReader> fileTable = prgState.getFileTable();

        if (!symTable.isDefined(id))
            throw new InterpreterException("Variable " + id + " is not declared!");

        IType idType = symTable.lookUp(id).getType();
        if (!idType.equals(new IntType()))
            throw new InterpreterException("Type of variable " + id + " is not integer!");

        IValue value = expr.evaluate(symTable, heap);
        IType valueType = value.getType();
        if (!valueType.equals(new StringType()))
            throw new InterpreterException("Invalid file name!");

        StringValue fileName = (StringValue)value;
        if (!fileTable.isOpened(fileName))
            throw new InterpreterException("File " + fileName + " is not opened!");

        BufferedReader bufferedReader = fileTable.lookUp(fileName);

        try {
            String line = bufferedReader.readLine();
            int readValue;
            if (line.equals(""))
                readValue = 0;
            else
                readValue = Integer.parseInt(line);
            IntValue newIntValue = new IntValue(readValue);
            symTable.update(id, newIntValue);
        } catch (IOException e) {
            throw new InterpreterException(e.getMessage());
        }

        return null;
    }

    @Override
    public ITypeEnv<String, IType> typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        IType idType = typeEnv.lookUp(id);
        IType exprType = expr.typeCheck(typeEnv);
        if (!idType.equals(new IntType()))
            throw new InterpreterException("Type of variable " + id + " is not integer!");
        if (!exprType.equals(new StringType()))
            throw new InterpreterException("Invalid file name!");
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFile(expr.deepCopy(), id);
    }

    @Override
    public String toString() {
        return String.format("readFile(%s,%s)", expr, id);
    }
}
