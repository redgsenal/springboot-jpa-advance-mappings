package com.springboot.tutorials.cruddemo.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

    // annotate class as entity and map to db table

    // define fields

    // annotate fields with db column names

    // create constructors

    // generate getters / setters (try lombok)

    // generate toString (try lombok)

    // do the same with Instructor class

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name="youtube_channel")
    private String youtubeChannel;

    @NonNull
    @Column(name="hobby")
    private String hobby;

    @NonNull
    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;

}
