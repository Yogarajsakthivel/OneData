package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.BookModel;



public interface BookRepository {

	
	 List<BookModel> findAll();

	    BookModel findById(Long id);

	    void save(BookModel bookmodel);

	    void update(BookModel bookmodel);

	    void delete(Long id);

	    List<BookModel> search(String keyword);

		

}
