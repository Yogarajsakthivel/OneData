package com.example.demo.repositoryimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BorrowModel;
import com.example.demo.repository.BorrowRepository;

@Repository
public class BorrowRepositoryImpl implements BorrowRepository {

	@Autowired
	
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BorrowModel> findAll()
    
    {
        String sql = "SELECT b.id, b.memberId, b.bookId, b.borrowedDate, b.dueDate, m.name as memberName, " +
                "m.phone as memberPhone, bk.title as bookTitle, bk.author as bookAuthor " +
                "FROM borrows b " +
                "JOIN members m ON b.memberId = m.id " +
                "JOIN books bk ON b.bookId = bk.id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BorrowModel.class));
    }

    @SuppressWarnings("deprecation")
	@Override
    public BorrowModel findById(Long id) {
        String sql = "SELECT b.id, b.memberId, b.bookId, b.borrowedDate, b.dueDate, m.name as memberName, " +
                "m.phone as memberPhone, bk.title as bookTitle, bk.author as bookAuthor " +
                "FROM borrows b " +
                "JOIN members m ON b.memberId = m.id " +
                "JOIN books bk ON b.bookId = bk.id " +
                "WHERE b.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(BorrowModel.class));
    }

    @Override
    public void borrowBook(Long memberId, Long bookId, Date borrowedDate, Date dueDate) {
        // Check if the book is available
        String availabilityCheckSql = "SELECT availableCopies FROM books WHERE id = ?";
        @SuppressWarnings("deprecation")
		Integer availableCopies = jdbcTemplate.queryForObject(availabilityCheckSql, new Object[]{bookId}, Integer.class);

        if (availableCopies > 0) {
            // Decrement availableCopies by 1
            String decrementCopiesSql = "UPDATE books SET availableCopies = availableCopies - 1 WHERE id = ?";
            jdbcTemplate.update(decrementCopiesSql, bookId);

            // Add a new Borrow record
            String borrowBookSql = "INSERT INTO borrows (memberId, bookId, borrowedDate, dueDate) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(borrowBookSql, memberId, bookId, borrowedDate, dueDate);
        } else {
            // Handle unavailability of the book
            throw new RuntimeException("Book is not available for borrowing");
        }
    }

    @Override
    public void returnBook(Long borrowId) {
        // Increment availableCopies by 1
        String incrementCopiesSql = "UPDATE books SET availableCopies = availableCopies + 1 " +
                "WHERE id IN (SELECT bookId FROM borrows WHERE id = ?)";
        jdbcTemplate.update(incrementCopiesSql, borrowId);

        // Update the Borrow record
        String returnBookSql = "UPDATE borrows SET returnedDate = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(returnBookSql, borrowId);
    }

	@Override
	public void lendBook(Long memberId, Long bookId, Date borrowedDate, Date dueDate) {
		// TODO Auto-generated method stub
		
	}
}
