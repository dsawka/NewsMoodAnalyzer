package pl.coderslab.newsmoodanalyzer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Author author;

    @Column(length = 10000)
    private String content;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "post")
    private List<Evaluation> evaluations;

    @ManyToOne(optional = false)
    private Dairy dairy;

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

}
