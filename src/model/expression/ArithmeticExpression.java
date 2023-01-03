package model.expression;

import exception.InterpreterException;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.operators.ArithmeticOperator;
import model.type.IntType;
import model.value.IValue;
import model.value.IntValue;

public class ArithmeticExpression implements IExpression {
    private final IExpression expr1;
    private final IExpression expr2;
    private final ArithmeticOperator op;

    public ArithmeticExpression(IExpression expr1, IExpression expr2, ArithmeticOperator op) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.op = op;
    }

    @Override
    public IValue evaluate(ISymTable<String, IValue> symTable, IHeap<Integer, IValue> heap) throws InterpreterException {
        IValue val1;
        IValue val2;
        val1 = expr1.evaluate(symTable, heap);
        if (!val1.getType().equals(new IntType()))
            throw new InterpreterException("First operand " + expr1 + " isn't an integer!");
        val2 = expr2.evaluate(symTable, heap);
        if (!val2.getType().equals(new IntType()))
            throw new InterpreterException("Second operand " + expr2 + " isn't an integer!");

        IntValue intVal1 = (IntValue)val1;
        IntValue intVal2 = (IntValue)val2;
        int int1 = intVal1.getValue();
        int int2 = intVal2.getValue();

        IntValue result;
        switch (op) {
            case ADD -> result = new IntValue(int1 + int2);
            case SUB -> result = new IntValue(int1 - int2);
            case MUL -> result = new IntValue(int1 * int2);
            case DIV -> {
                if (int2 == 0)
                    throw new InterpreterException("Division by zero!");
                result = new IntValue(int1 / int2);
            }
            default -> throw new InterpreterException("Invalid arithmetic operator!");
        }

        return result;
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(expr1.deepCopy(), expr2.deepCopy(), op);
    }

    @Override
    public String toString() {
        char op_char;
        switch (op) {
            case ADD -> op_char = '+';
            case SUB -> op_char = '-';
            case MUL -> op_char = '*';
            case DIV -> op_char = '/';
            default -> op_char = '?';
        }
        return String.format("%s%s%s", expr1, op_char, expr2);
    }
}
