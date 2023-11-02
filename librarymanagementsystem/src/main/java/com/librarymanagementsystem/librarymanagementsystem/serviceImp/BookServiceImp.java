package com.librarymanagementsystem.librarymanagementsystem.serviceImp;

import com.librarymanagementsystem.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.BookException;
import com.librarymanagementsystem.librarymanagementsystem.repository.BookRepository;
import com.librarymanagementsystem.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
//        Book newBook = new Book();
//        newBook.setTitle(Book.getTitle());
//        newBook.setAuthor(Book.getAuthor());
//        newBook.setTitle(String.valueOf(Book.getCopies()));
//        newBook.setAuthor(Book.getDescription());
       return bookRepository.save(book);

    }

    @Override
    public List<Book> getBooks()  {
        return bookRepository.findAll();

    }

    @Override
    public Book updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getBookId()).orElse(null);

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setCopies(book.getCopies());
        existingBook.setDescription(book.getDescription());
        existingBook.setStatus(book.getStatus());

        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getBook(int id){
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public double calculateFine(Book book) {
        if(!book.getSubmitDate().isBefore(book.getReturnDate())){
            long daysOverDue = ChronoUnit.DAYS.between(book.getReturnDate(),book.getSubmitDate());
            double finePerDay = 1.0;
            return finePerDay*daysOverDue;
        }
        else
            return 0.0;
    }

}
