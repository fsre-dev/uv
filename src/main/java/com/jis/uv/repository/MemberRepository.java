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

    Member findByPassportNumber(String passportNumber);

    Member findByOib(String oib);

    Member findByEmail(String eMail);

    Member findByCardNumber(String cardNumber);

    Member findByIdentityCard(String identityCard);

}
