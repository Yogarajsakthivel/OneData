package com.example.demo.service;

import java.util.List;

import com.example.demo.model.MemberModel;

public interface MemberService {

	List<MemberModel> getAllMembers();
    MemberModel getMemberById(Long id);
    void addMember(MemberModel member);
    void updateMember(Long id, MemberModel member);
    void deleteMember(Long id);
}
