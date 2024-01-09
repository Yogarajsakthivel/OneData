package com.example.demo.service;

import java.util.List;

import com.example.demo.model.BookModel;

public interface BookService {

    List<BookModel> getAllBooks();

    BookModel getBookById(Long id);

    void addBook(BookModel bookModel);

    void updateBook(Long id, BookModel bookModel);

    void deleteBook(Long id);

    List<BookModel> searchBooks(String keyword);
}
