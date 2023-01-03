package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.IExeStack;

public class CompStatement implements IStatement {
    private final IStatement firstStmt;
    private final IStatement secondStmt;

    public CompStatement(IStatement firstStmt, IStatement secondStmt) {
        this.firstStmt = firstStmt;
        this.secondStmt = secondStmt;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        IExeStack<IStatement> exeStack = prgState.getExeStack();
        exeStack.push(secondStmt);
        exeStack.push(firstStmt);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CompStatement(firstStmt.deepCopy(), secondStmt.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("(%s;%s)", firstStmt, secondStmt);
    }
}
