package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MemberModel;
import com.example.demo.service.MemberService;

@RestController

@RequestMapping("/api/members")

public class MemberController {
	
	@Autowired (required=false)
    private MemberService memberService;

    @GetMapping
    public List<MemberModel> getAllMembers()
    
    {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    
    public ResponseEntity<MemberModel> getMemberById(@PathVariable Long id)
    {
        MemberModel member = memberService.getMemberById(id);
        
        return (member != null) ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }

    
    @PostMapping("/add")
    
    public ResponseEntity<Void> addMember(@RequestBody MemberModel membermodel)
    {
        memberService.addMember(membermodel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable Long id, @RequestBody MemberModel membermodel) {
        memberService.updateMember(id, membermodel);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
	
}
