package com.krupoderov.spring.articles.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private byte[] photo;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Photo(byte[] photo, User user) {
        this.photo = photo;
        this.user = user;
    }
}
