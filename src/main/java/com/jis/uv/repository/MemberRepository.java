package com.jis.uv.repository;

import com.jis.uv.model.Member;
import com.jis.uv.model.enums.Gender;
import com.jis.uv.model.enums.MemberTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    Page<Member> findAllByLastName(String lastName, Pageable pageRequest);

    Page<Member> findAllByFirstName(String firstName, Pageable pageRequest);

    Page<Member> findAllByGender(Gender gender, Pageable pageRequest);

    Page<Member> findAllByMemberType(MemberTypeEnum memberType, Pageable pageRequest);

    Page<Member> findAllByAddress(String Address, Pageable pageRequest);

    Page<Member> findAllByCity(String City, Pageable pageRequest);

    Page<Member> findAllByState(String state, Pageable pageRequest);

    Page<Member> findAllByCellNumber(String cellNumber, Pageable pageRequest);

    Page<Member> findAllByPhoneNumber(String phoneNumber, Pageable pageRequest);

    Member findByPassportNumber(String passportNumber);

    Member findByOib(String oib);

    Member findByEmail(String eMail);

    Member findByCardNumber(String cardNumber);

    Member findByIdentityCard(String identityCard);
}
