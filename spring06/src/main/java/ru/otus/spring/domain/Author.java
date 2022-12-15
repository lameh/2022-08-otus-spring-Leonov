package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Book> books;

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
