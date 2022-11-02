package com.boot.dao.custom;

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
    public Book addBook(Book book){
        Book result = bookRepo.save(book);
        return result;
    }
    //adding the multiple book
    public List<Book> addBooks(List<Book> book){
        return (List<Book>) bookRepo.saveAll(book);
    }
    //delete data
    public boolean deleteBook(int id) {
       bookRepo.deleteById(id);
        return false;
    }

    public void updateBook(Book book, int id,Logger logger) {

        book.setId(id);
        bookRepo.save(book);
    }
}
