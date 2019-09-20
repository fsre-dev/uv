package com.jis.uv.service;

import com.jis.uv.model.Member;
import com.jis.uv.model.MemberAudit;
import com.jis.uv.model.Membership;
import com.jis.uv.model.enums.ActionEnum;
import com.jis.uv.model.enums.Gender;
import com.jis.uv.model.enums.MemberTypeEnum;
import com.jis.uv.repository.MemberAuditRepository;
import com.jis.uv.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberAuditRepository memberAuditRepository;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private MembershipAuditService membershipAuditService;


    private final String emailDomainRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private final String onlyDigitsRegex = "^[0-9]*$";
    private final String phoneAndCellNumberRegex = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
    private final String identityCardRegex = "^[A-Za-z0-9]+$";
    private final String firstAndLastNameRegex = "^[\\p{L}\\s.’\\-,]+$";

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Page<Member> findAll(Pageable pageRequest) {
        return memberRepository.findAll(pageRequest);
    }

    public Page<Member> findAllDynamic(Pageable pageRequest, Specification<Member> specification) {
        return memberRepository.findAll(specification, pageRequest);
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

    @Transactional
    public Member createMember(Member member) {
        member.setDeleted(false);
        member.getMembership().setDeleted(false);
        Member createdMember = memberRepository.save(member);

        MemberAudit createdMemberAudit = new MemberAudit(createdMember);
        createdMemberAudit.setAction(ActionEnum.ADD);

        memberAuditRepository.saveAndFlush(createdMemberAudit);
        membershipAuditService.createAudit(createdMember.getMembership());
        return createdMember;
    }

    @Transactional
    public Member updateMember(Member member, Long id) {
        member.setId(id);
        Member updatedMember = memberRepository.save(member);

        MemberAudit updatedMemberAudit = new MemberAudit(updatedMember);
        updatedMemberAudit.setAction(ActionEnum.UPD);

        memberAuditRepository.saveAndFlush(updatedMemberAudit);
        return updatedMember;
    }

    @Transactional
    public Member deleteMember(Long id) throws Exception {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            throw new Exception("Can't delete. Member does not exist");
        }
        member.setDeleted(true);
        Member terminatedMember = memberRepository.save(member);

        MemberAudit terminatedMemberAudit = new MemberAudit(terminatedMember);
        terminatedMemberAudit.setAction(ActionEnum.TERM);

        Membership membership = membershipService.findById(terminatedMember.getMembership().getId()).get();
        membershipService.delete(membership.getId());

        memberAuditRepository.saveAndFlush(terminatedMemberAudit);
        return terminatedMember;
    }


    public void deletePermanentlyMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Boolean validateMember(Member member) {

        return  member.getFirstName().matches(firstAndLastNameRegex)
                && member.getLastName().matches(firstAndLastNameRegex)
                && !member.getFirstName().trim().isEmpty()
                && !member.getLastName().trim().isEmpty()
                && member.getEmail().matches(emailDomainRegex)
                && member.getOib().matches(onlyDigitsRegex)
                && member.getOib().length() == 11
                && member.getCardNumber().matches(onlyDigitsRegex)
                && member.getCardNumber().length() == 16
                && member.getPassportNumber().matches(onlyDigitsRegex)
                && member.getPassportNumber().length() == 9
                && member.getIdentityCard().matches(identityCardRegex)
                && member.getIdentityCard().length() == 9
                && member.getPhoneNumber().matches(phoneAndCellNumberRegex)
                && member.getCellNumber().matches(phoneAndCellNumberRegex)
                && member.getBirthDate().before(new Date(System.currentTimeMillis()));

    }
}
