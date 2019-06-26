package com.jis.uv.controller;

import com.jis.uv.model.Member;
import com.jis.uv.model.enums.Gender;
import com.jis.uv.model.enums.MemberTypeEnum;
import com.jis.uv.service.MemberService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Member>> findAll() {
        List<Member> members = memberService.findAll();
        if (members == null || members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/all", params = {"page", "size"})
    public ResponseEntity<Page<Member>> findAllDynamic(@And({
        @Spec(path = "firstName", spec = Equal.class),
        @Spec(path = "lastName", spec = Equal.class),
        @Spec(path = "memberType", spec = Equal.class),
        @Spec(path = "gender", spec = Equal.class),
        @Spec(path = "address", spec = Equal.class),
        @Spec(path = "city", spec = Equal.class),
        @Spec(path = "state", spec = Equal.class),
        @Spec(path = "phoneNumber", spec = Equal.class),
        @Spec(path = "cellNumber", spec = Equal.class),
        @Spec(path = "isDeleted", spec = Equal.class)
    }) Specification<Member> specification, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllDynamic(PageRequest.of(page, size), specification);
        if (members.isEmpty() || members == null) {
            logger.info("List of member is empty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of member is found");
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/firstname", params = {"page", "size", "value"})
    public ResponseEntity<Page<Member>> findAllByFirstName(@RequestParam("value") String firstName, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByFirstName(firstName, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            logger.info("List of members with first name {} is empty", firstName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of members with first name {} is found", firstName);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/lastname", params = {"page", "size", "value"})
    public ResponseEntity<Page<Member>> findAllByLastName(@RequestParam("value") String lastName, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByLastName(lastName, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            logger.info("List of members with last name {} is empty", lastName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of members with last name {} is found", lastName);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/phonenumber", params = {"page", "size", "value"})
    public ResponseEntity<Page<Member>> findAllByPhoneNumber(@RequestParam("value") String phoneNumber, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByPhoneNumber(phoneNumber, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            logger.info("List of members with phone number {} is empty", phoneNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of members with phone number {} is found", phoneNumber);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/gender", params = {"page", "size", "value"})
    public ResponseEntity<Page<Member>> findAllByGender(@RequestParam("value") Gender gender, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByGender(gender, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            logger.info("List of members with gender {} is empty", gender);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of members with gender {} is found", gender);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/membertype", params = {"page", "size", "value"})
    public ResponseEntity<Page<Member>> findAllByMemberType(@RequestParam("value") MemberTypeEnum memberType, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByMemberType(memberType, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            logger.info("List of members with member type {} is empty", memberType);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of members with member type {} is found", memberType);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/address", params = {"page", "size", "value"})
    public ResponseEntity<Page<Member>> findAllByAddress(@RequestParam("value") String address, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByAddress(address, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            logger.info("List of members with address {} is empty", address);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of members with address {} is found", address);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/city", params = {"page", "size", "value"})
    public ResponseEntity<Page<Member>> findAllByCity(@RequestParam("value") String city, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByCity(city, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            logger.info("List of members with city {} is empty", city);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of members with city {} is found", city);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/state", params = {"page", "size", "value"})
    public ResponseEntity<Page<Member>> findAllByState(@RequestParam("value") String state, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByState(state, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            logger.info("List of members with state {} is empty", state);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of members with state {} is found", state);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/cellnumber", params = {"page", "size", "value"})
    public ResponseEntity<Page<Member>> findAllByCellNumber(@RequestParam("value") String cellNumber, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByCellNumber(cellNumber, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            logger.info("List of members with cell number {} is empty", cellNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of members with cell number {} is found", cellNumber);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<Member> findById(@RequestParam("id") Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            logger.info("Member with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Member with id {} is founded", id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping(params = {"email"})
    public ResponseEntity<Member> findByEmail(@RequestParam("email") String eMail) {
        Member member = memberService.findByEmail(eMail);
        if (member == null) {
            logger.info("Member with email {} not found", eMail);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Member with email {} is founded", eMail);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping(params = {"oib"})
    public ResponseEntity<Member> findByOib(@RequestParam("oib") String oib) {
        Member member = memberService.findByOib(oib);
        if (member == null) {
            logger.info("Member with oib {} not found", oib);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Member with oib {} is founded", oib);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping(params = {"passportnumber"})
    public ResponseEntity<Member> findByPassportNumber(@RequestParam("passportnumber") String passportNumber) {
        Member member = memberService.findByPassportNumber(passportNumber);
        if (member == null) {
            logger.info("Member with passportNumber {} not found", passportNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Member with passportNumber {} is founded", passportNumber);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping(params = {"cardnumber"})
    public ResponseEntity<Member> findByCardNumber(@RequestParam("cardnumber") String cardNumber) {
        Member member = memberService.findByCardNumber(cardNumber);
        if (member == null) {
            logger.info("Member with cardNumber {} not found", cardNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Member with cardNumber {} is founded", cardNumber);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping(params = {"identitycard"})
    public ResponseEntity<Member> findByIdentityCard(@RequestParam("identitycard") String identityCard) {
        Member member = memberService.findByIdentityCard(identityCard);
        if (member == null) {
            logger.info("Member with identityCard {} not found", identityCard);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Member with identityCard {} is founded", identityCard);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        if (memberService.validateMember(member)) {
            logger.error("Unable to create member {}", member.toString());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Member createdMember = memberService.createMember(member);
        logger.info("Member created: {}", member.toString());
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Member> updateMember(@RequestBody Member member, @PathVariable(value = "id") Long id) {
        Member updatedMember = memberService.findById(id);
        if (updatedMember == null) {
            logger.info("Unable to find member with {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (!memberService.validateMember(member)) {
            logger.error("Unable to update member with id {}", id);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (updatedMember.equals(member)) {
            logger.info("No changes were made for member with id {}", id);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        member.setMembership(updatedMember.getMembership());
        updatedMember = memberService.updateMember(member, id);

        logger.info("Member updated: {}", member.toString());
        return new ResponseEntity<>(updatedMember, HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Member> deleteMember(@PathVariable(value = "id") Long id) {
        Member deletedMember = memberService.findById(id);
        if (deletedMember == null) {
            logger.info("Unable to find member with {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        try {
            deletedMember = memberService.deleteMember(deletedMember);
            logger.info("Member deleted: {}", deletedMember.toString());
            return new ResponseEntity<>(deletedMember, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Member couldn't be deleted: {}", deletedMember.toString());
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }


    }

    @DeleteMapping(value = "/deletePermanently/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Void> deleteMemberPermanently(@PathVariable(value = "id") Long id) {
        Member deletedMember = memberService.findById(id);
        if (deletedMember == null) {
            logger.info("Unable to find member with {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        memberService.deletePermanentlyMember(id);
        logger.info("Member deleted permanently: {}", deletedMember.toString());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
