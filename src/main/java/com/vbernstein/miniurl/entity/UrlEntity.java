package com.vbernstein.miniurl.entity;

import java.sql.Date;

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
    String url;
    
    @Column(name="BASE")
    String longUrl;

    @Column(name="DATE_CREATED")
    Date dateCreated;
}
