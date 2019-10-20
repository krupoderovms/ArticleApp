package com.krupoderov.spring.articles.model;

import com.krupoderov.spring.articles.utils.Constants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = Constants.CONTENT_LENGTH_MIN, max = Constants.CONTENT_LENGTH_MAX)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfPublication;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfChange;

    @ManyToOne
    @JoinColumn(name = "CHANGED_USER_ID")
    private User changedUser;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "REPLY_ID")
    private Reply replyTo;

    @OneToMany(mappedBy = "replyTo", cascade = CascadeType.ALL)
    private Set<Reply> replies = new HashSet<>();

    public boolean getIsChanged() {
        return Objects.nonNull(dateOfChange);
    }
}
