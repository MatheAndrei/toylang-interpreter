package model.value;

import model.type.IType;
import model.type.IntType;

public class IntValue implements IValue {
    private final int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntValue)
            return value == ((IntValue)obj).getValue();
        return false;
    }

    @Override
    public IValue deepCopy() {
        return new IntValue(value);
    }

    @Override
    public String toString() {
        return String.format("int(%s)", value);
    }
}
