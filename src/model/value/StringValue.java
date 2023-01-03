package model.value;

import model.type.IType;
import model.type.StringType;

public class StringValue implements IValue {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringValue)
            return value.equals(((StringValue)obj).getValue());
        return false;
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(value);
    }

    @Override
    public String toString() {
        return String.format("string(\"%s\")", value);
    }
}
