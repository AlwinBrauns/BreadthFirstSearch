package com.company;

public class Mark<T> {
    private T element;
    private boolean isMarked;

    Mark(T element, boolean isMarked)
    {
        this.element = element;
        this.isMarked = isMarked;
    }

    public T getElement() {
        return element;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public boolean isMarked() {
        return isMarked;
    }
}
