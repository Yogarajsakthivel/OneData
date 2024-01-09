package com.example.demo.repositoryimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BookModel;
import com.example.demo.repository.BookRepository;

@Repository
public class BookRepositoryimpl implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BookModel> findAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BookModel.class));
    }

    @SuppressWarnings("deprecation")
	@Override
    public BookModel findById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(BookModel.class));
    }

    @Override
    public void save(BookModel bookModel) {
        String sql = "INSERT INTO books (title, author, isbn, publishedDate, availableCopies) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, bookModel.getTitle(), bookModel.getAuthor(), bookModel.getIsbn(),
                bookModel.getPublishedDate(), bookModel.getAvailableCopies());
    }

    @Override
    public void update(BookModel bookModel) {
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, " +
                "publishedDate = ?, availableCopies = ? WHERE id = ?";
        jdbcTemplate.update(sql, bookModel.getTitle(), bookModel.getAuthor(), bookModel.getIsbn(),
                bookModel.getPublishedDate(), bookModel.getAvailableCopies(), bookModel.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @SuppressWarnings("deprecation")
	@Override
    public List<BookModel> search(String keyword) {
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR isbn LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"},
                new BeanPropertyRowMapper<>(BookModel.class));
    }
}
