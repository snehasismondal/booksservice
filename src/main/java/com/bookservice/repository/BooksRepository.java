package com.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookservice.entity.Books;

public interface BooksRepository extends JpaRepository<Books,String> {

}
