package com.jis.uv.service;

import com.jis.uv.model.User;
import com.jis.uv.model.enums.RoleEnum;
import com.jis.uv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SessionRegistry sessionRegistry;

    private final String passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private final String usernameRegex = "^[a-zA-Z0-9._-]{3,}$";
    private final String emailDomainRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    public UserService() {
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public Page<User> findAllByRole(RoleEnum role, Pageable pageRequest) {
        return userRepository.findAllByRole(role, pageRequest);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByEMail(String eMail) {
        return userRepository.findByEMail(eMail);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        String password = user.getPassword();

        user.setDeleted(false);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public User updateUser(User user, Long id) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void updateUserPassword(User user, Long id) throws Exception {
        String newPassword = user.getPassword();
        if (!user.getPassword().matches(passwordRegex)) {
            throw new Exception("Password is invalid");
        } else {
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setId(id);
            userRepository.save(user);

            sessionRegistry.getAllPrincipals().forEach( principal -> {
                if(((org.springframework.security.core.userdetails.User) principal).getUsername().equals(user.getUsername())){
                    sessionRegistry.getAllSessions(principal,false).forEach(sessionInformation -> sessionInformation.expireNow());
                }
            });
        }
    }

    public User login(User user, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticationResult = authenticationManager.authenticate(authenticationToken);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authenticationResult);

        sessionRegistry.registerNewSession(request.getSession().getId(), authenticationResult.getPrincipal());

        return userRepository.findByUsername(user.getUsername());
    }

    public User deleteUser(User user) {
        user.setDeleted(true);
        return userRepository.save(user);
    }

    public void deletePermanentlyUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean validateUser(User user) {
        if (user.getPassword().matches(passwordRegex) && user.getUsername().matches(usernameRegex) && user.geteMail().matches(emailDomainRegex)) {
            return true;
        } else {
            return false;
        }
    }
}
