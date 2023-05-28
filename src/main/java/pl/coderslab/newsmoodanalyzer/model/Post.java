package pl.coderslab.newsmoodanalyzer.model;

import jakarta.persistence.*;
import lombok.*;
import pl.coderslab.newsmoodanalyzer.controller.PostController;

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

    @OneToOne(mappedBy = "post")
    private Evaluation evaluation;

    @ManyToOne(optional = false)
    private Dairy dairy;


    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

}
