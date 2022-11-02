package com.boot.dao.custom;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<Book,Integer> {
    public Book findById(int id);
    public Book findByTitle(String title);
}
