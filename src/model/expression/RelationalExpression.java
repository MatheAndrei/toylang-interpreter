package model.expression;

import exception.InterpreterException;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.operators.RelationalOperator;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;

public class RelationalExpression implements IExpression {
    private final IExpression expr1;
    private final IExpression expr2;
    private final RelationalOperator op;

    public RelationalExpression(IExpression expr1, IExpression expr2, RelationalOperator op) {
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

        BoolValue result;
        switch (op) {
            case EQ -> result = new BoolValue(int1 == int2);
            case NE -> result = new BoolValue(int1 != int2);
            case GT -> result = new BoolValue(int1 > int2);
            case LT -> result = new BoolValue(int1 < int2);
            case GE -> result = new BoolValue(int1 >= int2);
            case LE -> result = new BoolValue(int1 <= int2);
            default -> throw new InterpreterException("Invalid relational operator!");
        }

        return result;
    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(expr1.deepCopy(), expr2.deepCopy(), op);
    }

    @Override
    public String toString() {
        String op_char;
        switch (op) {
            case EQ -> op_char = "==";
            case NE -> op_char = "!=";
            case GT -> op_char = ">";
            case LT -> op_char = "<";
            case GE -> op_char = ">=";
            case LE -> op_char = "<=";
            default -> op_char = "?";
        }
        return String.format("%s%s%s", expr1, op_char, expr2);
    }
}
