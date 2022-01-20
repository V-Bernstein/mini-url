package com.vbernstein.miniurl.repository;

import com.vbernstein.miniurl.entity.UrlEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Integer> {
    
}
