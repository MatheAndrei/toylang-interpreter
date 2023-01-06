package model.expression;

import exception.InterpreterException;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.adt.ITypeEnv;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;

public class RHExpression implements IExpression {
    private final IExpression expr;

    public RHExpression(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public IValue evaluate(ISymTable<String, IValue> symTable, IHeap<Integer, IValue> heap) throws InterpreterException {
        IValue value = expr.evaluate(symTable, heap);
        IType valueType = value.getType();
        if (!(valueType instanceof RefType))
            throw new InterpreterException("Expression " + expr + " does not evaluate to a Ref value!");

        int address = ((RefValue)value).getAddress();
        if (!heap.isAllocated(address))
            throw new InterpreterException("Expression" + expr + " is not allocated on the heap!");

        IValue result = heap.lookUp(address);

        return result;
    }

    @Override
    public IType typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        IType type = expr.typeCheck(typeEnv);
        if (!(type instanceof RefType))
            throw new InterpreterException("Expression " + expr + " does not evaluate to a Ref value!");
        RefType refType = (RefType)type;
        return refType.getInner();
    }

    @Override
    public IExpression deepCopy() {
        return new RHExpression(expr.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("rH(%s)", expr);
    }
}
