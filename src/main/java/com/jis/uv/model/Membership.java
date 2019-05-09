package com.jis.uv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "membership")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "member_from")
    private Date memberFrom;

    @Column(name = "member_to")
    private Date memberTo;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    public Membership() {

    }

    public Membership(Date memberFrom, Date memberTo, Double price, Member member) {
        this.memberFrom = memberFrom;
        this.memberTo = memberTo;
        this.price = price;
        this.member = member;
    }

    public Date getMemberFrom() {
        return memberFrom;
    }

    public void setMemberFrom(Date memberFrom) {
        this.memberFrom = memberFrom;
    }

    public Date getMemberTo() {
        return memberTo;
    }

    public void setMemberTo(Date memberTo) {
        this.memberTo = memberTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}