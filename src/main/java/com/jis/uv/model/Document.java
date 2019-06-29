package com.jis.uv.model;

import com.jis.uv.model.enums.EventEnum;

import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "event", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EventEnum event;

    @Column(name = "description")
    private String description;

    @Column(name = "event_date", nullable = false)
    private Date eventDate;

    @Column(name = "price_per_member", nullable = false)
    private Double pricePerMember;

    @Column(name = "totalCost", nullable = false)
    private Double totalCost;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToMany
    private Set<Member> members;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventEnum getReason() {
        return event;
    }

    public void setReason(EventEnum event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public EventEnum getEvent() {
        return event;
    }

    public void setEvent(EventEnum event) {
        this.event = event;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Double getPricePerMember() {
        return pricePerMember;
    }

    public void setPricePerMember(Double pricePerMember) {
        this.pricePerMember = pricePerMember;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
