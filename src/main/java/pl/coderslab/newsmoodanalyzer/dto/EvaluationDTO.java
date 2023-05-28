package pl.coderslab.newsmoodanalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationDTO {
    private Long id;
    private Long postId;
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
