package com.jis.uv.repository;

import com.jis.uv.model.User;
import com.jis.uv.model.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Page<User> findAllByUsername(String userName, Pageable pageRequest);

    Page<User> findAllByEMail(String eMail, Pageable pageRequest);

    Page<User> findAllByRole(RoleEnum role, Pageable pageRequest);

}
