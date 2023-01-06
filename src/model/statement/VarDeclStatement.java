package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.ISymTable;
import model.adt.ITypeEnv;
import model.type.*;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;

public class VarDeclStatement implements IStatement {
    private final IType type;
    private final String id;

    public VarDeclStatement(IType type, String id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        if (symTable.isDefined(id))
            throw new InterpreterException("Variable " + id + " is already declared!");

        IValue value;
        if (type.equals(new IntType()))
            value = new IntType().defaultValue();
        else if (type.equals(new BoolType()))
            value = new BoolType().defaultValue();
        else if (type.equals(new StringType()))
            value = new StringType().defaultValue();
        else if (type instanceof RefType)
            value = new RefType(((RefType)type).getInner()).defaultValue();
        else
            throw new InterpreterException("Invalid variable type!");

        symTable.put(id, value);

        return null;
    }

    @Override
    public ITypeEnv<String, IType> typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        typeEnv.put(id, type);
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclStatement(type.deepCopy(), id);
    }

    @Override
    public String toString() {
        return String.format("%s %s", type, id);
    }
}
