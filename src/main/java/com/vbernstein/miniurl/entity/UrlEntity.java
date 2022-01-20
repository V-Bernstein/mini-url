package com.vbernstein.miniurl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UrlEntity {
    @Id
    @Column(name = "URL_ID")
    Integer id; // Do we need Long?

    @Column(name="MINI")
    String url;
    
    @Column(name="BASE")
    String longUrl;
}
