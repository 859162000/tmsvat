package com.deloitte.tms.pl.message.message.model;

public enum SendType {
    EMAIL(1), SYSTEM(2);

    private int nCode;

    private SendType(int _nCode) {
        this.nCode = _nCode;
    }

    @Override
    public String toString() {
        return String.valueOf(this.nCode);
    }
}
