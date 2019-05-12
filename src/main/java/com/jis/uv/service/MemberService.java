package com.jis.uv.service;

import com.jis.uv.model.Member;
import com.jis.uv.model.enums.Gender;
import com.jis.uv.model.enums.MemberTypeEnum;
import com.jis.uv.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private List<Member> findAll() {
        return memberRepository.findAll();
    }

    private Page<Member> findAll(Pageable pageRequest) {
        return memberRepository.findAll(pageRequest);
    }

    private Page<Member> findAllByFirstName(String firstName, Pageable pageRequest) {
        return memberRepository.findAllByFirstName(firstName, pageRequest);
    }

    private Page<Member> findAllByLastName(String lastName, Pageable pageRequest) {
        return memberRepository.findAllByLastName(lastName, pageRequest);
    }

    private Page<Member> findAllByPhoneNumber(String phoneNumber, Pageable pageRequest) {
        return memberRepository.findAllByPhoneNumber(phoneNumber, pageRequest);
    }

    private Page<Member> findAllByGender(Gender gender, Pageable pageRequest) {
        return memberRepository.findAllByGender(gender, pageRequest);
    }

    private Page<Member> findAllByMemberType(MemberTypeEnum memberType, Pageable pageRequest) {
        return memberRepository.findAllByMemberType(memberType, pageRequest);
    }

    private Page<Member> findAllByAddress(String address, Pageable pageRequest) {
        return memberRepository.findAllByAddress(address, pageRequest);
    }

    private Page<Member> findAllByCity(String city, Pageable pageRequest) {
        return memberRepository.findAllByCity(city, pageRequest);
    }

    private Page<Member> findAllByState(String state, Pageable pageRequest) {
        return memberRepository.findAllByState(state, pageRequest);
    }

    private Page<Member> findAllByCellNumber(String cellNumber, Pageable pageRequest) {
        return memberRepository.findAllByCellNumber(cellNumber, pageRequest);
    }

    private Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    private Member findByEmail(String eMail) {
        return memberRepository.findByEmail(eMail);
    }

    private Member findByOib(String oib) {
        return memberRepository.findByOib(oib);
    }

    private Member findByPassportNumber(String passportNumber) {
        return memberRepository.findByPassportNumber(passportNumber);
    }

    private Member findByCardNumber(String cardNumber) {
        return memberRepository.findByCardNumber(cardNumber);
    }

    private Member createMember(Member member) {
        return memberRepository.save(member);
    }

    private Member updateMember(Member member, Long id) {
        member.setId(id);
        return memberRepository.save(member);
    }

    private Member deleteMember(Member member) {
        return memberRepository.save(member);
    }

    private void deletePermanentlyMember(Long id) {
        memberRepository.deleteById(id);
    }
}
