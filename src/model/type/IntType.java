package model.type;

import model.value.IValue;
import model.value.IntValue;

public class IntType implements IType {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }

    @Override
    public IType deepCopy() {
        return new IntType();
    }

    @Override
    public String toString() {
        return "int";
    }
}
