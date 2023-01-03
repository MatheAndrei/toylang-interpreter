package model.type;

import model.value.IValue;
import model.value.RefValue;

public class RefType implements IType {
    private final IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RefType)
            return inner.equals(((RefType)obj).getInner());
        return false;
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public IType deepCopy() {
        return new RefType(inner.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("Ref(%s)", inner.toString());
    }
}
