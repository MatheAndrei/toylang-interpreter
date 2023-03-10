package model.expression;

import exception.InterpreterException;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.adt.ITypeEnv;
import model.type.IType;
import model.value.IValue;

public class VarExpression implements IExpression {
    private final String id;

    public VarExpression(String id) {
        this.id = id;
    }

    @Override
    public IValue evaluate(ISymTable<String, IValue> symTable, IHeap<Integer, IValue> heap) throws InterpreterException {
        return symTable.lookUp(id);
    }

    @Override
    public IType typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        return typeEnv.lookUp(id);
    }

    @Override
    public IExpression deepCopy() {
        return new VarExpression(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
