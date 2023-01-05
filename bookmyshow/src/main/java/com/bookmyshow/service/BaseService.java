package com.bookmyshow.service;

import com.bookmyshow.entity.City;

import java.util.List;
import java.util.Optional;

public  interface BaseService<T,I> {
    T add(T entity);
    boolean delete(I id );
    T update(T entity);
    Optional<T> findById(I id);
    List<T> findAll();
}
