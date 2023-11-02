package com.librarymanagementsystem.librarymanagementsystem.service;

import com.librarymanagementsystem.librarymanagementsystem.dto.BookDto;
import com.librarymanagementsystem.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.librarymanagementsystem.exception.BookException;
import com.librarymanagementsystem.librarymanagementsystem.exception.UserException;

import java.util.List;

public interface BookService {
    Book addBook(Book book) throws BookException;
    List<Book> getBooks() throws BookException;
    Book updateBook(Book book) throws BookException;
    void deleteBook(int id) throws BookException;
    Book getBook(int id) throws BookException;
    double calculateFine(Book book) throws Exception;
}
