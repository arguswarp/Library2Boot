package com.argus.alishevspring.Library2Boot.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;
    @Column(name = "fullname")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String fullName;
    @Column(name = "age_of_birth")
    @Min(value = 1901, message = "Age of birth should be greater than 1900")
    private int ageOfBirth;
    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(int personId, String fullName, int ageOfBirth) {
        this.personId = personId;
        this.fullName = fullName;
        this.ageOfBirth = ageOfBirth;
    }
}
