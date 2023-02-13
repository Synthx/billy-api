package org.yezebi.billy.spring.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    private String id;
}
