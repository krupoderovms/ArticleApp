package com.krupoderov.spring.articles.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/* В данном классе описывается объект(статья),
    который будет помещен в базу данных */

@Entity // класс сохраняется в базу данных
@Data // создает геттеры, сеттеры, конструкторы
public class Article implements Serializable, Comparable<Article> {

    /* Описываем параметры */
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    @Column(length = 1000000)
    @Lob // Указывает, что постоянное свойство
    // или поле должны сохраняться как большой
    // объект для поддерживаемого базой данных
    // типа большого объекта.
    private String content;
    @Column
    private long creationTimestamp;

    @Override
    public int compareTo(Article that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }
}
