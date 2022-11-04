package com.boot.dao.custom.books.management.controller;

import com.boot.dao.custom.books.management.book.Book;
import com.boot.dao.custom.books.management.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookService service;

    //get all book handler
    @GetMapping("service/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> bookList = this.service.getListOfBooks();
        if(bookList.size() <= 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println(service.getListOfBooks());
        return ResponseEntity.of(Optional.of(bookList)) ;
    }
    //get single book handler
    @GetMapping("service/book/{id}")
    public ResponseEntity<Book> getBooksByID(@PathVariable("id") int id){
        Book book = service.getBookById(id,logger);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println(book);
        return ResponseEntity.of(Optional.of(book)) ;
    }
   //get single book by name controller
    @GetMapping("service/books/{title}")
    public ResponseEntity<Book> getBooksByTitles(@PathVariable("title") String title){
        Book book = service.getBookByTitle(title,logger);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println(service.getBookByTitle(title,logger));
        return ResponseEntity.of(Optional.of(book));
    }
    //adds single book
    @PostMapping("service/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try {
             this.service.addBook(book);
            System.out.println(book);
            return ResponseEntity.of(Optional.of(book));
        }catch (NullPointerException | UnsupportedOperationException | IllegalArgumentException e){
            logger.error("Error while creating ID {} :", book.getId() , e);
            ResponseEntity<Book> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            logger.error(String.valueOf(response));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //add or create multiple book handler
    @PostMapping( "service/adds")
    public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> book){
        List<Book> books = null;
        try {
           if(book != null && !book.isEmpty()){
               books = service.addBooks(book);
           return ResponseEntity.of(Optional.of(books));
           }
       }catch (NullPointerException | UnsupportedOperationException | IllegalArgumentException e){
           logger.error("Error while adding these data {} :", books , e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
        return null;
    }
    //delete book handler
    @DeleteMapping("service/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id){
            if(this.service.deleteBook(id) != null){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }
    //update book handler
    @PutMapping("service/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id){
        try {
            this.service.updateBook(book,id,logger);
            System.out.println(book);
            return ResponseEntity.ok().body(book);
        }catch (NoSuchElementException | NullPointerException e){
            logger.error("Error with ID {} :", id , e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
