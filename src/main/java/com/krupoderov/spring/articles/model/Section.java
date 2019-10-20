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
public class Section {
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
    private Date dateOfPublication;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private Set<Topic> topics;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Section parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Section> subsections = new HashSet<>();

    public boolean getIsParent() {
        return (Objects.isNull(subsections) && subsections.size() > 0);
    }
}
