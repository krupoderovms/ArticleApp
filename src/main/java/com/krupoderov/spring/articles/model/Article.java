package com.krupoderov.spring.articles.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * В данном классе описывается модель(статья),
 * которая будет помещена в базу данных
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
@Entity
@Data
public class Article implements Serializable, Comparable<Article> {

    /* Описываем параметры */
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column(length = 1000000)
    @Lob
    private String content;
    @Column
    private long creationTimestamp;

    @Override
    public int compareTo(Article that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }
}
