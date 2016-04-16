package com.deloitte.tms.pl.message.message.model;

public enum RecipientType {
	//接受对象
    ALL(1), ROLE(2), OWNER(3), PROJECT_WITHOUT_PIC(4), PROJECT(5), PROJECT_WITHOUT_PIC_MIC(6);

    private int nCode;

    private RecipientType(int _nCode) {
        this.nCode = _nCode;
    }

    @Override
    public String toString() {
        return String.valueOf(this.nCode);
    }
}
