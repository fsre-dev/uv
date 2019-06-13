package com.jis.uv.model;

import com.jis.uv.model.enums.ActionEnum;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "membership_audit")
public class MembershipAudit extends Membership {
    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private ActionEnum action;

    @Column(name = "updated_at")
    @Basic
    private Date updatedAt;

    public MembershipAudit(Date memberFrom, Date memberTo, Double price, Member member, ActionEnum action) {
        super(memberFrom, memberTo, price, member);
        this.action = action;
        updatedAt = new Date(new java.util.Date().getTime());
    }

    public ActionEnum getAction() {
        return action;
    }

    public void setAction(ActionEnum action) {
        this.action = action;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}