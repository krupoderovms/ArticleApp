package com.krupoderov.spring.articles.model;

import com.krupoderov.spring.articles.utils.Constants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * В данном классе описывается модель(статья),
 * которая будет помещена в базу данных
 *
 * @author Krupoderov Mikhail
 * @version 1.1
 */
@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Size(min = Constants.TITLE_LENGTH_MIN, max = Constants.TITLE_LENGTH_MAX)
    private String title;

    @NotEmpty
    @Size(min = Constants.CONTENT_LENGTH_MIN, max = Constants.CONTENT_LENGTH_MAX)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfChange;

    @ManyToOne
    @JoinColumn(name = "CHANGED_USER_ID")
    private User changedUser;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "SECTION_ID")
    private Section section;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private Set<Reply> replies;

    public boolean getIsChanged() {
        return Objects.nonNull(dateOfChange);
    }
}
