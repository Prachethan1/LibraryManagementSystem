package com.librarymanagementsystem.librarymanagementsystem.controller;

import com.librarymanagementsystem.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.BookException;
import com.librarymanagementsystem.librarymanagementsystem.service.BookService;
import com.librarymanagementsystem.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) throws BookException {
        return bookService.addBook(book);

    }

    @GetMapping("/books")
    public List<Book> getBooks() throws BookException {
        return bookService.getBooks();

    }

    @PutMapping("/updateBook")
    public Book updateBook(@RequestBody Book updatedBook) throws BookException {
        return bookService.updateBook(updatedBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable int id) throws BookException{
        bookService.deleteBook(id);
    }

    @GetMapping("/bookId/{id}")
    public Book getBook(@PathVariable int id) throws BookException{
        Book book = bookService.getBook(id);
        return book;
    }


    @PostMapping("/return/{bid}")
    public String returnBook(@PathVariable int bid, @RequestParam int uid) throws Exception {
        Book book = bookService.getBook(bid);
        User user = userService.getUser(uid);

//        LocalDate currentDate = new LocalDate();
//        book.setSubmitDate(currentDate);

        if(book.getSubmitDate().isBefore(book.getReturnDate())) {
            return "Book returned successful and no fine applied";
        }
        else{
            double fine = bookService.calculateFine(book);
            userService.updateTotalFine(user, fine);
            return String.format("Book returned successfully and total fine is %.2f", fine);
        }
    }

    @PostMapping("/payFine/{uid}")
    public String payFine(@PathVariable int uid, @RequestParam double amount) throws Exception {
        User user = userService.getUser(uid);
        userService.payFine(user, amount);
        if(user.getTotalFine()==0)
        return "fine collection successful";
        else
            return String.format("Remaining fine amount %.2f", user.getTotalFine());
    }

}
