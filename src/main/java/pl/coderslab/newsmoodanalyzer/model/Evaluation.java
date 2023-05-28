package pl.coderslab.newsmoodanalyzer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluations")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private double joy;

    private double sadness;

    private double fear;

    private double anger;

    private double surprise;

    private double neutrality;

    private double satisfaction;

    private double trust;

    private double envy;

    private double disgust;

    private double excitement;

    private double positiveSentiment;

    private double negativeSentiment;
}
