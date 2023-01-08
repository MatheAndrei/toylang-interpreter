package model.statement;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.adt.ITypeEnv;
import model.expression.IExpression;
import model.type.IType;
import model.value.IValue;

public class AssignStatement implements IStatement {
    private final String id;
    private final IExpression expr;

    public AssignStatement(String id, IExpression expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws InterpreterException {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        IHeap<Integer, IValue> heap = prgState.getHeap();
        if (!symTable.isDefined(id))
            throw new InterpreterException("Variable " + id + " wasn't declared earlier!");

        IValue value = expr.evaluate(symTable, heap);
        IType type = symTable.lookUp(id).getType();
        if (!value.getType().equals(type))
            throw new InterpreterException("Type of variable " + id + " and type of assigned expression + " + expr + " don't match!");

        symTable.update(id, value);

        return null;
    }

    @Override
    public ITypeEnv<String, IType> typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        IType idType = typeEnv.lookUp(id);
        IType exprType = expr.typeCheck(typeEnv);
        if (!idType.equals(exprType))
            throw new InterpreterException("Type of variable " + id + " and type of assigned expression " + expr + " don't match!");
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(id, expr.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("%s=%s", id, expr);
    }
}
