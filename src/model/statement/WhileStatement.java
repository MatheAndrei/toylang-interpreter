package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.IExeStack;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.expression.IExpression;
import model.type.BoolType;
import model.type.IType;
import model.value.BoolValue;
import model.value.IValue;

public class WhileStatement implements IStatement {
    private final IExpression expr;
    private final IStatement stmt;

    public WhileStatement(IExpression expr, IStatement stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        IExeStack<IStatement> exeStack = prgState.getExeStack();
        IHeap<Integer, IValue> heap = prgState.getHeap();

        IValue cond = expr.evaluate(symTable, heap);
        IType condType = cond.getType();
        if (!condType.equals(new BoolType()))
            throw new InterpreterException("Condition expression " + expr + " isn't a boolean!");

        BoolValue boolValue = (BoolValue)cond;
        if (boolValue.getValue()) {
            exeStack.push(this);
            exeStack.push(stmt);
        }

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expr.deepCopy(), stmt.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("(WHILE (%s) %s)", expr, stmt);
    }
}
