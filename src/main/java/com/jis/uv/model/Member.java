package com.jis.uv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jis.uv.model.enums.Gender;
import com.jis.uv.model.enums.MemberTypeEnum;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "card_number", unique = true, nullable = false)
    private String cardNumber;

    @Column(name = "member_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberTypeEnum memberType;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "zip", nullable = false)
    private String zip;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cell_number")
    private String cellNumber;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "passport_number", unique = true, nullable = false)
    private String passportNumber;

    @Column(name = "identity_card", unique = true)
    private String identityCard;

    @Column(name = "oib", unique = true)
    private String oib;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToMany(mappedBy = "members", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Document> documents;

    public Member() {
    }

    public Member(String firstName, String lastName, String cardNumber, MemberTypeEnum memberType, Gender gender,
                  String address, String zip, String city, String state, String phoneNumber, String cellNumber, String email,
                  Date birthDate, String passportNumber, String oib, String identityCard, Boolean isDeleted, Membership membership) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.memberType = memberType;
        this.gender = gender;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.cellNumber = cellNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.passportNumber = passportNumber;
        this.oib = oib;
        this.identityCard = identityCard;
        this.isDeleted = isDeleted;
        this.membership = membership;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Member)) {
            return false;
        }
        Member member = (Member) o;
        return
            Objects.equals(firstName, member.firstName) &&
                Objects.equals(lastName, member.lastName) &&
                Objects.equals(cardNumber, member.cardNumber) &&
                memberType == member.memberType &&
                gender == member.gender &&
                Objects.equals(address, member.address) &&
                Objects.equals(zip, member.zip) &&
                Objects.equals(city, member.city) &&
                Objects.equals(state, member.state) &&
                Objects.equals(phoneNumber, member.phoneNumber) &&
                Objects.equals(cellNumber, member.cellNumber) &&
                Objects.equals(email, member.email) &&
                Objects.equals(birthDate.toLocalDate(), member.birthDate.toLocalDate()) &&
                Objects.equals(passportNumber, member.passportNumber) &&
                Objects.equals(identityCard, member.identityCard) &&
                Objects.equals(oib, member.oib) &&
                Objects.equals(isDeleted, member.isDeleted);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, cardNumber, memberType, gender, address, zip, city, state, phoneNumber, cellNumber, email, birthDate.toLocalDate(), passportNumber, identityCard, oib, isDeleted);
    }

    @Override
    public String toString() {
        return "Member{" +
            "id=" + id +
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
            ", membership=" + membership +
            ", isDeleted=" + isDeleted +
            '}';
    }

}
