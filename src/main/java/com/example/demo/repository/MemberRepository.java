package com.example.demo.repository;


import java.util.List;

import com.example.demo.model.MemberModel;

public interface MemberRepository

{

	List<MemberModel> findAll();
    MemberModel findById(Long id);
    void save(MemberModel membermodel);
    void update(MemberModel membermodel);
    void delete(Long id);
    
}
