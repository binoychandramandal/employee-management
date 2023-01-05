package com.bookmyshow.service.impl;

import com.bookmyshow.service.BaseService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
@Setter
@Getter
public class BaseServiceImpl<T,I> implements BaseService<T,I> {

   private JpaRepository<T,I> jpaRepository;

    public BaseServiceImpl(JpaRepository<T, I> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public BaseServiceImpl() {
    }

    @Override
    public T add(T entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public boolean delete(I id) {
        try {
            if( jpaRepository.existsById(id)){
                jpaRepository.deleteById(id);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public T update(T entity) {

        return jpaRepository.save(entity);
    }

    @Override
    public Optional<T> findById(I id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }
}
