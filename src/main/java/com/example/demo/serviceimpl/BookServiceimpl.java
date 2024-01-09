package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BookModel;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;

@Service
public class BookServiceimpl implements BookService {

    @Autowired (required=false)
    private BookRepository bookRepository;

    @Override
    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookModel getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void addBook(BookModel bookModel) {
        bookRepository.save(bookModel);
    }

    @Override
    public void updateBook(Long id, BookModel bookModel) {
        bookModel.setId(id);
        bookRepository.update(bookModel);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public List<BookModel> searchBooks(String keyword) {
        return bookRepository.search(keyword);
    }
}
