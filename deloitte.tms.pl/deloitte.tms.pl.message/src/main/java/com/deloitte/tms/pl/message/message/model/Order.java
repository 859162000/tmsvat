package com.deloitte.tms.pl.message.message.model;

public enum Order {
    LOW(1), NORMAL(10), HIGH(20);

    private int nCode;

    private Order(int _nCode) {
        this.nCode = _nCode;
    }

    @Override
    public String toString() {
        return String.valueOf(this.nCode);
    }
}
