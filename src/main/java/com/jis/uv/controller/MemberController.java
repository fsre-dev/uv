package com.jis.uv.controller;

import com.jis.uv.model.Member;
import com.jis.uv.model.enums.Gender;
import com.jis.uv.model.enums.MemberTypeEnum;
import com.jis.uv.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/all")
    private ResponseEntity<List<Member>> findAll() {
        List<Member> members = memberService.findAll();
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/all", params = {"page", "size"})
    private ResponseEntity<Page<Member>> findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAll(PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/firstname", params = {"page", "size", "value"})
    private ResponseEntity<Page<Member>> findAllByFirstName(@RequestParam("value") String firstName, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByFirstName(firstName, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/lastname", params = {"page", "size", "value"})
    private ResponseEntity<Page<Member>> findAllByLastName(@RequestParam("value") String lastName, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByLastName(lastName, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/phonenumber", params = {"page", "size", "value"})
    private ResponseEntity<Page<Member>> findAllByPhoneNumber(@RequestParam("value") String phoneNumber, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByPhoneNumber(phoneNumber, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/gender", params = {"page", "size", "value"})
    private ResponseEntity<Page<Member>> findAllByGender(@RequestParam("value") Gender gender, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByGender(gender, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/membertype", params = {"page", "size", "value"})
    private ResponseEntity<Page<Member>> findAllByMemberType(@RequestParam("value") MemberTypeEnum memberType, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByMemberType(memberType, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/address", params = {"page", "size", "value"})
    private ResponseEntity<Page<Member>> findAllByAddress(@RequestParam("value") String address, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByAddress(address, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/city", params = {"page", "size", "value"})
    private ResponseEntity<Page<Member>> findAllByCity(@RequestParam("value") String city, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByCity(city, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/state", params = {"page", "size", "value"})
    private ResponseEntity<Page<Member>> findAllByState(@RequestParam("value") String state, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByState(state, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/cellnumber", params = {"page", "size", "value"})
    private ResponseEntity<Page<Member>> findAllByCellNumber(@RequestParam("value") String cellNumber, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Member> members = memberService.findAllByCellNumber(cellNumber, PageRequest.of(page, size));
        if (members.isEmpty() || members == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(params = {"id"})
    private ResponseEntity<Member> findById(@RequestParam("id") Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping(params = {"email"})
    private ResponseEntity<Member> findByEmail(@RequestParam("email") String eMail) {
        Member member = memberService.findByEmail(eMail);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping(params = {"oib"})
    private ResponseEntity<Member> findByOib(@RequestParam("oib") String oib) {
        Member member = memberService.findByOib(oib);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping(params = {"passportnumber"})
    private ResponseEntity<Member> findByPassportNumber(@RequestParam("passportnumber") String passportNumber) {
        Member member = memberService.findByPassportNumber(passportNumber);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping(params = {"cardnumber"})
    private ResponseEntity<Member> findByCardNumber(@RequestParam("cardnumber") String cardNumber) {
        Member member = memberService.findByCardNumber(cardNumber);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.createMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<Member> updateMember(@RequestBody Member member, @PathVariable(value = "id") Long id) {
        Member updatedMember = memberService.findById(id);
        if (updatedMember == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updatedMember = memberService.updateMember(member, id);
        return new ResponseEntity<>(updatedMember, HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/delete/{id}")
    private ResponseEntity<Member> deleteMember(@PathVariable(value = "id") Long id) {
        Member deletedMember = memberService.findById(id);
        if (deletedMember == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        deletedMember = memberService.deleteMember(deletedMember);
        return new ResponseEntity<>(deletedMember, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/deletePermanently/{id}")
    private ResponseEntity<Void> deleteMemberPermanently(@PathVariable(value = "id") Long id) {
        Member deletedMember = memberService.findById(id);
        if (deletedMember == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        memberService.deletePermanentlyMember(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
