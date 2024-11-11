package com.example.demo.dao;

import com.example.demo.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDao extends JpaRepository<FieldEntity,String> {
    FieldEntity findByFieldCode(String fieldCode);
}
