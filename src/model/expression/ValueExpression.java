package model.expression;

import exception.InterpreterException;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.value.IValue;

public class ValueExpression implements IExpression {
    private final IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(ISymTable<String, IValue> symTable, IHeap<Integer, IValue> heap) throws InterpreterException {
        return value;
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(value.deepCopy());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
