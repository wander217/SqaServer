package com.wander.sqa.dto.user;

import com.wander.sqa.dto.base.BaseDTO;

public class SubjectTeacherStatDTO extends BaseDTO {
    private String code;
    private String fullname;

    public SubjectTeacherStatDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
