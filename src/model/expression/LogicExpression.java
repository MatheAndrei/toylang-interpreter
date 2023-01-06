package model.expression;

import exception.InterpreterException;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.adt.ITypeEnv;
import model.operators.LogicOperator;
import model.type.BoolType;
import model.type.IType;
import model.value.BoolValue;
import model.value.IValue;

public class LogicExpression implements IExpression {
    private final IExpression expr1;
    private final IExpression expr2;
    private final LogicOperator op;

    public LogicExpression(IExpression expr1, IExpression expr2, LogicOperator op) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.op = op;
    }

    @Override
    public IValue evaluate(ISymTable<String, IValue> symTable, IHeap<Integer, IValue> heap) throws InterpreterException {
        IValue val1;
        IValue val2;
        val1 = expr1.evaluate(symTable, heap);
        if (!val1.getType().equals(new BoolType()))
            throw new InterpreterException("First operand " + expr1 + " isn't a boolean!");
        val2 = expr2.evaluate(symTable, heap);
        if (!val2.getType().equals(new BoolType()))
            throw new InterpreterException("Second operand " + expr2 + " isn't a boolean!");

        BoolValue boolVal1 = (BoolValue)val1;
        BoolValue boolVal2 = (BoolValue)val2;
        boolean bool1 = boolVal1.getValue();
        boolean bool2 = boolVal2.getValue();

        BoolValue result;
        switch (op) {
            case AND -> result = new BoolValue(bool1 && bool2);
            case OR -> result = new BoolValue(bool1 || bool2);
            default -> throw new InterpreterException("Invalid logical operator!");
        }

        return result;
    }

    @Override
    public IType typeCheck(ITypeEnv<String, IType> typeEnv) throws InterpreterException {
        IType type1 = expr1.typeCheck(typeEnv);
        IType type2 = expr2.typeCheck(typeEnv);
        if (!type1.equals(new BoolType()))
            throw new InterpreterException("First operand " + expr1 + " isn't a boolean!");
        if (!type2.equals(new BoolType()))
            throw new InterpreterException("Second operand " + expr2 + " isn't a boolean!");
        return new BoolType();
    }

    @Override
    public IExpression deepCopy() {
        return new LogicExpression(expr1.deepCopy(), expr2.deepCopy(), op);
    }

    @Override
    public String toString() {
        String op_char;
        switch (op) {
            case AND -> op_char = "&&";
            case OR -> op_char = "||";
            default -> op_char = "?";
        }
        return String.format("%s%s%s", expr1, op_char, expr2);
    }
}
