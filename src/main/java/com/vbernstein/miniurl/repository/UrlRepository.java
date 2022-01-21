package com.vbernstein.miniurl.repository;

import java.util.Optional;

import com.vbernstein.miniurl.entity.UrlEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Integer> {
    @Query(value = "SELECT BASE FROM URL WHERE MINI = ?1", nativeQuery=true)
    Optional<String> getByMini(String mini);
}
