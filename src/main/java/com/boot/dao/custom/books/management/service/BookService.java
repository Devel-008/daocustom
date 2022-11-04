package com.boot.dao.custom.books.management.service;

import com.boot.dao.custom.books.management.exception.BookAlreadyExistsException;
import com.boot.dao.custom.books.management.book.Book;
import com.boot.dao.custom.books.management.repository.BookRepo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;


@Component
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    //get all books
    public List<Book> getListOfBooks(){
        return (List<Book>) this.bookRepo.findAll();
    }
    //get books by id
    public Book getBookById(int id, Logger logger){
        logger.info("Logger intialized to getBookById ");
        Book book = null;
        try{
            book = this.bookRepo.findById(id);
        }catch (NoSuchElementException e){
            logger.error("Got an Error: ",e);
            logger.info(" ID {} not found!!!",id);
        }
        return book;
    }
    //get books by name
    public Book getBookByTitle(String title, Logger logger){
        Book book = null;
        try {
            book = bookRepo.findByTitle(title);
        }catch (NoSuchElementException e){
            logger.error("Got an Error: ",e);
            logger.info(" Book with Title {} not found!!! ", title);
        }
        return book;
    }
    //adding the book
    public String addBook(Book book){
        Book book1 = bookRepo.findById(book.getId());

        if(book1 == null) {
            bookRepo.save(book);
            return "Book with ID " + book.getId() + " added successfully!!";
        }else {
            throw new BookAlreadyExistsException("Book with ID " + book.getId() + " already exist");
        }
    }
    //adding the multiple book
    public List<Book> addBooks(List<Book> book){
        return (List<Book>) bookRepo.saveAll(book);
    }
    //delete data
    public String deleteBook(int id) {
        Book book = bookRepo.findById(id);
        if(book == null){
            throw new NoSuchElementException("No book with ID " + id + "exist !!");
        }else {
            bookRepo.deleteById(id);
            return "Book with ID " + id + " deleted successfully!!";
        }
    }

    public String updateBook(Book book, int id,Logger logger) {
        Book book1 = bookRepo.findById(id);
        if(book1 == null){
            logger.error("No such Customer exist with ID " + id);
            throw new NoSuchElementException("No such Customer exist with ID " + id);
        }else {
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setPrice(book.getPrice());
            bookRepo.save(book1);
            return "Record for ID" + id + "updated successfully";
        }
    }
}
