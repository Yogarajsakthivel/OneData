package com.example.demo.repositoryimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MemberModel;
import com.example.demo.repository.MemberRepository;
@Repository
public class MemberRepositoryImpl implements MemberRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MemberModel> findAll() {
        String sql = "SELECT * FROM members";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MemberModel.class));
    }

    @SuppressWarnings("deprecation")
	@Override
    public MemberModel findById(Long id) {
        String sql = "SELECT * FROM members WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(MemberModel.class));
    }

    @Override
    public void save(MemberModel membermodel) {
        String sql = "INSERT INTO members (name, phone, registeredDate) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, membermodel.getName(), membermodel.getPhone(), membermodel.getRegisteredDate());
    }

    @Override
    public void update(MemberModel membermodel) {
        String sql = "UPDATE members SET name = ?, phone = ?, registeredDate = ? WHERE id = ?";
        jdbcTemplate.update(sql, membermodel.getName(), membermodel.getPhone(), membermodel.getRegisteredDate(), membermodel.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM members WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
