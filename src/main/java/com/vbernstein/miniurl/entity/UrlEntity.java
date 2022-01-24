package com.vbernstein.miniurl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "url")
@Getter
@Setter
@ToString
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name="mini")
    String miniUrl;
    
    @Column(name="full_url")
    String fullUrl;

    @Column(name="updated_timestamp")
    @UpdateTimestamp
    Timestamp updatedTimestamp;
}
