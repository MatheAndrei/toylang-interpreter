package model.statement;

import exception.InterpreterException;
import model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState prgState) throws InterpreterException;
    IStatement deepCopy();
}
