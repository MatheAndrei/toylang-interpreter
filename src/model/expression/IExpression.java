package model.expression;

import exception.InterpreterException;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.adt.ITypeEnv;
import model.type.IType;
import model.value.IValue;

public interface IExpression {
    IValue evaluate(ISymTable<String, IValue> symTable, IHeap<Integer, IValue> heap) throws InterpreterException;
    IType typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException;
    IExpression deepCopy();
}
