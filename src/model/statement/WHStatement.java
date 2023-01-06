package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.adt.ITypeEnv;
import model.expression.IExpression;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;

public class WHStatement implements IStatement {
    private final String id;
    private final IExpression expr;

    public WHStatement(String id, IExpression expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        IHeap<Integer, IValue> heap = prgState.getHeap();

        if (!symTable.isDefined(id))
            throw new InterpreterException("Variable " + id + " is not declared!");

        IValue idValue = symTable.lookUp(id);
        IType idType = idValue.getType();
        if (!(idType instanceof RefType))
            throw new InterpreterException("Type of variable " + id + " is not Ref!");

        Integer address = ((RefValue)idValue).getAddress();
        if (!heap.isAllocated(address))
            throw new InterpreterException("Expression" + expr + " is not allocated on the heap!");

        IValue value = expr.evaluate(symTable, heap);
        IType valueType = value.getType();
        if (!valueType.equals(((RefType)idType).getInner()))
            throw new InterpreterException("Type of value referenced by variable " + id + " and expression " + expr + " do not match!");

        heap.update(address, value);

        return null;
    }

    @Override
    public ITypeEnv<String, IType> typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        IType idType = typeEnv.lookUp(id);
        IType exprType = expr.typeCheck(typeEnv);
        if (!idType.equals(new RefType(exprType)))
            throw new InterpreterException("Type of value referenced by variable " + id + " and expression " + expr + " do not match!");
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new WHStatement(id, expr.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("wH(%s,%s)", id, expr);
    }
}
