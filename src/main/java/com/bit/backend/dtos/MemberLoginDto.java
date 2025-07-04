package com.bit.backend.dtos;

public class MemberLoginDto {

    private long id;
    private long memberId;
    private String code;

    public MemberLoginDto() {
    }

    public MemberLoginDto(long id, long memberId, String code) {
        this.id = id;
        this.memberId = memberId;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
