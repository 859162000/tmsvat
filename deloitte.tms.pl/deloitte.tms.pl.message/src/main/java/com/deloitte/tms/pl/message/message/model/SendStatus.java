package com.deloitte.tms.pl.message.message.model;

public enum SendStatus {
    NORMAL(0), EXCEPTION(1);

    private int nCode;

    private SendStatus(int _nCode) {
        this.nCode = _nCode;
    }

    @Override
    public String toString() {
        return String.valueOf(this.nCode);
    }
}
