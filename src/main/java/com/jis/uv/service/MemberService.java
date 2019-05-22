package com.jis.uv.service;

import com.jis.uv.model.Member;
import com.jis.uv.model.enums.Gender;
import com.jis.uv.model.enums.MemberTypeEnum;
import com.jis.uv.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private final String emailDomainRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private final String onlyDigitsRegex = "^[0-9]*$";
    private final String phoneAndCellNumberRegex = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
    private final String identityCardRegex = "^[A-Za-z0-9]+$";

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Page<Member> findAll(Pageable pageRequest) {
        return memberRepository.findAll(pageRequest);
    }

    public Page<Member> findAllDynamic(Pageable pageRequest, Specification<Member> specification) {
        return memberRepository.findAll(specification, pageRequest);
    }

    public Page<Member> findAllByFirstName(String firstName, Pageable pageRequest) {
        return memberRepository.findAllByFirstName(firstName, pageRequest);
    }

    public Page<Member> findAllByLastName(String lastName, Pageable pageRequest) {
        return memberRepository.findAllByLastName(lastName, pageRequest);
    }

    public Page<Member> findAllByPhoneNumber(String phoneNumber, Pageable pageRequest) {
        return memberRepository.findAllByPhoneNumber(phoneNumber, pageRequest);
    }

    public Page<Member> findAllByGender(Gender gender, Pageable pageRequest) {
        return memberRepository.findAllByGender(gender, pageRequest);
    }

    public Page<Member> findAllByMemberType(MemberTypeEnum memberType, Pageable pageRequest) {
        return memberRepository.findAllByMemberType(memberType, pageRequest);
    }

    public Page<Member> findAllByAddress(String address, Pageable pageRequest) {
        return memberRepository.findAllByAddress(address, pageRequest);
    }

    public Page<Member> findAllByCity(String city, Pageable pageRequest) {
        return memberRepository.findAllByCity(city, pageRequest);
    }

    public Page<Member> findAllByState(String state, Pageable pageRequest) {
        return memberRepository.findAllByState(state, pageRequest);
    }

    public Page<Member> findAllByCellNumber(String cellNumber, Pageable pageRequest) {
        return memberRepository.findAllByCellNumber(cellNumber, pageRequest);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member findByEmail(String eMail) {
        return memberRepository.findByEmail(eMail);
    }

    public Member findByOib(String oib) {
        return memberRepository.findByOib(oib);
    }

    public Member findByPassportNumber(String passportNumber) {
        return memberRepository.findByPassportNumber(passportNumber);
    }

    public Member findByCardNumber(String cardNumber) {
        return memberRepository.findByCardNumber(cardNumber);
    }

    public Member findByIdentityCard(String identityCard) {
        return memberRepository.findByIdentityCard(identityCard);
    }

    public Member createMember(Member member) {
        member.setDeleted(false);
        return memberRepository.save(member);
    }

    public Member updateMember(Member member, Long id) {
        member.setId(id);
        return memberRepository.save(member);
    }

    public Member deleteMember(Member member) {
        member.setDeleted(true);
        return memberRepository.save(member);
    }

    public void deletePermanentlyMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Boolean validateMember(Member member) {

        if (!member.getEmail().matches(emailDomainRegex)) {
            return false;
        }
        if (!member.getOib().matches(onlyDigitsRegex) || member.getOib().length() != 11) {
            return false;
        }
        if (!member.getCardNumber().matches(onlyDigitsRegex) || member.getCardNumber().length() != 16) {
            return false;
        }
        if (!member.getPassportNumber().matches(onlyDigitsRegex) || member.getPassportNumber().length() != 9) {
            return false;
        }
        if (!member.getIdentityCard().matches(identityCardRegex) || member.getIdentityCard().length() != 9) {
            return false;
        }
        if (!member.getPhoneNumber().matches(phoneAndCellNumberRegex)) {
            return false;
        }
        if (!member.getCellNumber().matches(phoneAndCellNumberRegex)) {
            return false;
        }
        if (member.getBirthDate().after(new Date(System.currentTimeMillis()))) {
            return false;
        }

        return true;
    }

}
