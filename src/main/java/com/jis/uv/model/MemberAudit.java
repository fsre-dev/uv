package com.jis.uv.model;

import com.jis.uv.model.enums.ActionEnum;
import com.jis.uv.model.enums.Gender;
import com.jis.uv.model.enums.MemberTypeEnum;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class MemberAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rev_id")
    private Long revId;

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private ActionEnum action;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "member_type")
    @Enumerated(EnumType.STRING)
    private MemberTypeEnum memberType;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "zip")
    private String zip;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cell_number")
    private String cellNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "identity_card")
    private String identityCard;

    @Column(name = "oib")
    private String oib;

    @Column(name = "membership_id")
    private Long membershipId;

    @Column(name = "action_by")
    private String actionBy;

    @Column(name = "isdeleted")
    private Boolean isDeleted;

    public MemberAudit() {
    }

    public MemberAudit(Member member) {
        this.memberId = member.getId();
        this.membershipId = member.getMembership().getId();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.gender = member.getGender();
        this.memberType = member.getMemberType();
        this.address = member.getAddress();
        this.email = member.getEmail();
        this.zip = member.getZip();
        this.city = member.getCity();
        this.state = member.getState();
        this.birthDate = member.getBirthDate();
        this.cardNumber = member.getCardNumber();
        this.passportNumber = member.getPassportNumber();
        this.cellNumber = member.getCellNumber();
        this.phoneNumber = member.getPhoneNumber();
        this.oib = member.getOib();
        this.identityCard = member.getIdentityCard();
        this.isDeleted = member.getDeleted();
        this.actionBy = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public Long getRevId() {
        return revId;
    }

    public void setRevId(Long revId) {
        this.revId = revId;
    }

    public ActionEnum getAction() {
        return action;
    }

    public void setAction(ActionEnum action) {
        this.action = action;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public MemberTypeEnum getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberTypeEnum memberType) {
        this.memberType = memberType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy;
    }

    @Override
    public String toString() {
        return "MemberAudit{" +
            "revId=" + revId +
            ", action=" + action +
            ", memberId=" + memberId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", cardNumber='" + cardNumber + '\'' +
            ", memberType=" + memberType +
            ", gender=" + gender +
            ", address='" + address + '\'' +
            ", zip='" + zip + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", cellNumber='" + cellNumber + '\'' +
            ", email='" + email + '\'' +
            ", birthDate=" + birthDate +
            ", passportNumber='" + passportNumber + '\'' +
            ", identityCard='" + identityCard + '\'' +
            ", oib='" + oib + '\'' +
            ", membershipId=" + membershipId +
            ", isDeleted=" + isDeleted +
            '}';
    }
}
