package com.krupoderov.spring.articles.model;

import com.krupoderov.spring.articles.utils.Constants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Пожалуйста заполни поле")
    @Column
    @Size(min = Constants.TITLE_LENGTH_MIN, max = Constants.TITLE_LENGTH_MAX)
    private String title;

    @NotBlank(message = "Пожалуйста заполни поле")
    @Column(length = 1000000)
    @Lob
    @Size(min = Constants.CONTENT_LENGTH_MIN, max = Constants.CONTENT_LENGTH_MAX)
    private String content;

    @Column
    private long creationTimestamp;

    @Override
    public int compareTo(Article that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }
}
