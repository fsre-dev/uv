package com.jis.uv.repository;

import com.jis.uv.model.User;
import com.jis.uv.model.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String userName);

    User findByEMail(String eMail);

    Page<User> findAllByRole(RoleEnum role, Pageable pageRequest);

}
