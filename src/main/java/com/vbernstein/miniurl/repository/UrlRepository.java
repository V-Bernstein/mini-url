package com.vbernstein.miniurl.repository;

import java.util.Optional;

import com.vbernstein.miniurl.entity.UrlEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Integer> {
    @Query(value = "SELECT * FROM url WHERE mini = ?1", nativeQuery=true)
    Optional<UrlEntity> getByMini(String mini);

    @Query(value = "SELECT * FROM url, (SELECT MAX(updated_timestamp) AS mx FROM url) AS sub WHERE url.updated_timestamp = sub.mx", nativeQuery=true)
    Optional<UrlEntity> getLatestRecord();
}
