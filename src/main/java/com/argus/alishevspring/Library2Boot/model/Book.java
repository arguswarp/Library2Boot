package com.argus.alishevspring.Library2Boot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 30, message = "Title should be between 1 and 30 characters")
    @ToString.Include
    private String title;
    @Column(name = "author")
    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 1, max = 30, message = "Author name should be between 1 and 30 characters")
    @ToString.Include
    private String author;
    @Column(name = "age_of_publishment")
    @Min(value = 0, message = "Age of publishment should be be greater than 0")
    @ToString.Include
    private int ageOfPublishment;

    @Column(name = "assigned_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedAt;

    @Transient
    private boolean overdue;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    //name - FK from Many side, refCol - PK from One side
    @ToString.Include
    private Person owner;

    public Book(int bookId, String title, String author, int ageOfPublishment) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ageOfPublishment = ageOfPublishment;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ageOfPublishment=" + ageOfPublishment +
                ", owner=" + owner +
                '}';
    }
}
