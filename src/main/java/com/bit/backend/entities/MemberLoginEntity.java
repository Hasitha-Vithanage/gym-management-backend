package com.bit.backend.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "member-login")
public class MemberLoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "memberId")
    private long memberId;

    @Column(name = "code")
    private String code;

    public MemberLoginEntity() {
    }

    public MemberLoginEntity(long id, long memberId, String code) {
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
