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
