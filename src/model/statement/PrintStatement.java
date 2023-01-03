package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.adt.IOutput;
import model.expression.IExpression;
import model.value.IValue;

public class PrintStatement implements IStatement {
    private final IExpression expr;

    public PrintStatement(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        IHeap<Integer, IValue> heap = prgState.getHeap();
        IOutput<IValue> output = prgState.getOutput();
        IValue val = expr.evaluate(symTable, heap);
        output.add(val);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expr.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("print(%s)", expr);
    }
}
