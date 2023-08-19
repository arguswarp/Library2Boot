package com.argus.alishevspring.Library2Boot.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

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
    private String title;
    @Column(name = "author")
    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 1, max = 30, message = "Author name should be between 1 and 30 characters")
    private String author;
    @Column(name = "age_of_publishment")
    @Min(value = 0, message = "Age of publishment should be be greater than 0")
    private int ageOfPublishment;

    @Column(name = "assigned_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedAt;

    @Transient
    private boolean overdue;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    //name - FK from Many side, refCol - PK from One side
    private Person owner;

    public Book(int bookId, String title, String author, int ageOfPublishment) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ageOfPublishment = ageOfPublishment;
    }

    public Book() {

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAgeOfPublishment() {
        return ageOfPublishment;
    }

    public void setAgeOfPublishment(int ageOfPublishment) {
        this.ageOfPublishment = ageOfPublishment;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Date assignedAt) {
        this.assignedAt = assignedAt;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
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
