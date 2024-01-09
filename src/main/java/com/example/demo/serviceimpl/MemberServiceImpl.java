package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MemberModel;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired (required=false)
    private MemberRepository memberRepository;

    @Override
    public List<MemberModel> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public MemberModel getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public void addMember(MemberModel member) {
        memberRepository.save(member);
    }

    @Override
    public void updateMember(Long id, MemberModel member) {
        memberRepository.update(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.delete(id);
    }
}
