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
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "game_date")
    private Date gameDate;

    @Column(name = "opponent")
    private String opponent;

    @Column(name = "sector")
    private String sector;

    @Column(name = "row")
    private String row;

    @Column(name = "seat")
    private String seat;

    @Column(name = "price")
    private Double price;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}