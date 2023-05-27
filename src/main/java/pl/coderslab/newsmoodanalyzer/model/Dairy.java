package pl.coderslab.newsmoodanalyzer.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "dairies")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dairy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Author owner;

}
