package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.ITypeEnv;
import model.type.IType;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        return null;
    }

    @Override
    public ITypeEnv<String, IType> typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }

    @Override
    public String toString() {
        return "NOP";
    }
}
