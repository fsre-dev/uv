package com.jis.uv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "membership")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "member_from", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date memberFrom;

    @Column(name = "member_to", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date memberTo;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToOne(mappedBy = "membership")
    @JsonIgnore
    private Member member;

    public Membership() {

    }

    public Membership(Date memberFrom, Date memberTo, Double price) {
        this.memberFrom = memberFrom;
        this.memberTo = memberTo;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}