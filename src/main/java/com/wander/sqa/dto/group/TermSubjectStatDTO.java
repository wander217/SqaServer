package com.wander.sqa.dto.group;

import com.wander.sqa.dto.base.BaseDTO;

public class TermSubjectStatDTO extends BaseDTO {
    private String termSubjectName;
    private long remember;
    private long forgot;

    public TermSubjectStatDTO() {
    }

    public String getTermSubjectName() {
        return termSubjectName;
    }

    public void setTermSubjectName(String termSubjectName) {
        this.termSubjectName = termSubjectName;
    }

    public long getRemember() {
        return remember;
    }

    public void setRemember(long remember) {
        this.remember = remember;
    }

    public long getForgot() {
        return forgot;
    }

    public void setForgot(long forgot) {
        this.forgot = forgot;
    }
}
