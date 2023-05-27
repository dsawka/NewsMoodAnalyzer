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

    @ManyToOne
    private Post post;

    private int joy;

    private int sadness;

    private int fear;

    private int anger;

    private int surprise;

    private int neutrality;

    private int satisfaction;

    private int trust;

    private int envy;

    private int disgust;

    private int excitement;

    private int positiveSentiment;

    private int negativeSentiment;
}
