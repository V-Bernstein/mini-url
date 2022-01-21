package com.vbernstein.miniurl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "URL")
@Getter
@Setter
public class UrlEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name="MINI")
    String miniUrl;
    
    @Column(name="LONG")
    String longUrl;
}
