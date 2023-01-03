package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.adt.IExeStack;
import model.expression.IExpression;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.IValue;

public class IfStatement implements IStatement {
    private final IExpression expr;
    private final IStatement thenStmt;
    private final IStatement elseStmt;

    public IfStatement(IExpression expr, IStatement thenStmt, IStatement elseStmt) {
        this.expr = expr;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        IExeStack<IStatement> exeStack = prgState.getExeStack();
        IHeap<Integer, IValue> heap = prgState.getHeap();

        IValue cond = expr.evaluate(symTable, heap);
        if (!cond.getType().equals(new BoolType()))
            throw new InterpreterException("Condition expression " + expr + " isn't a boolean!");

        BoolValue boolValue = (BoolValue)cond;
        if (boolValue.getValue())
            exeStack.push(thenStmt);
        else
            exeStack.push(elseStmt);

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(expr.deepCopy(), thenStmt.deepCopy(), elseStmt.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("(IF(%s) THEN(%s) ELSE(%s))", expr, thenStmt, elseStmt);
    }
}
