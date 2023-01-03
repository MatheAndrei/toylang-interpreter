package model.statement;

import exception.InterpreterException;
import model.ProgramState;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        return null;
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
