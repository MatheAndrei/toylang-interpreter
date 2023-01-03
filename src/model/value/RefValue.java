package model.value;

import model.type.IType;
import model.type.RefType;

public class RefValue implements IValue {
    private final int address;
    private final IType type;

    public RefValue(int address, IType type) {
        this.address = address;
        this.type = type;
    }

    @Override
    public IType getType() {
        return new RefType(type);
    }

    public int getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RefValue)
            return address == ((RefValue)obj).address && type.equals(((RefValue)obj).type);
        return false;
    }

    @Override
    public IValue deepCopy() {
        return new RefValue(address, type.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("Ref(%s, %s)", address, type);
    }
}
