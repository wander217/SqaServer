package com.wander.sqa.dto.user;

import com.wander.sqa.dto.base.BaseDTO;

public class TeacherStatDTO extends BaseDTO {
    private String fullname;
    private long forgot;
    private long remember;

    public TeacherStatDTO() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getForgot() {
        return forgot;
    }

    public void setForgot(long forgot) {
        this.forgot = forgot;
    }

    public long getRemember() {
        return remember;
    }

    public void setRemember(long remember) {
        this.remember = remember;
    }
}
