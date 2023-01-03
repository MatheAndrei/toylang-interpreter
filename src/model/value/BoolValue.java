package model.value;

import model.type.BoolType;
import model.type.IType;

public class BoolValue implements IValue {
    private final boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoolValue)
            return value == ((BoolValue)obj).getValue();
        return false;
    }

    @Override
    public IValue deepCopy() {
        return new BoolValue(value);
    }

    @Override
    public String toString() {
        return String.format("bool(%s)", value);
    }
}
