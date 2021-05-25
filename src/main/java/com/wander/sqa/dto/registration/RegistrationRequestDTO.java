package com.wander.sqa.dto.registration;

public class RegistrationRequestDTO {
    private long teacherId;
    private long subjectGroupId;

    public RegistrationRequestDTO() {
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public long getSubjectGroupId() {
        return subjectGroupId;
    }

    public void setSubjectGroupId(long subjectGroupId) {
        this.subjectGroupId = subjectGroupId;
    }
}
