package com.jis.uv.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "reservation_date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date reservationDate;

    @Column(name = "game_date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date gameDate;

    @Column(name = "opponent", nullable = false)
    private String opponent;

    @Column(name = "sector", nullable = false)
    private String sector;

    @Column(name = "_row", nullable = false)
    private String row;

    @Column(name = "seat", nullable = false)
    private String seat;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    public Ticket() {

    }

    public Ticket(Date reservationDate, Date gameDate, String opponent, String sector, String row, String seat,
                  Double price, Member member) {
        this.reservationDate = reservationDate;
        this.gameDate = gameDate;
        this.opponent = opponent;
        this.sector = sector;
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}