package ru.otus.spring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "commentary")
@NamedEntityGraph(
        name = "Commentary.Book.Author.Genre",
        attributeNodes = {@NamedAttributeNode(value = "book", subgraph = "book-subgraph"),},
        subgraphs = {@NamedSubgraph(
                        name = "book-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("author"),
                                @NamedAttributeNode("genre")
                        })
                    })
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text", nullable = false)
    private String text;
    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Book book;

    public Commentary(Long id, String text, Book book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }
}
