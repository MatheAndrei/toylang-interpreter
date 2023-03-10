package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.ITypeEnv;
import model.type.IType;

public interface IStatement {
    ProgramState execute(ProgramState prgState) throws InterpreterException;
    ITypeEnv<String, IType> typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException;
    IStatement deepCopy();
}
