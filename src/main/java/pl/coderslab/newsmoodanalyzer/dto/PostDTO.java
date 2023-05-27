package pl.coderslab.newsmoodanalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.newsmoodanalyzer.dto.EvaluationDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PostDTO {
    private Long id;
    private Long authorId;
    private String content;
    private LocalDateTime creationDate;
    private List<EvaluationDTO> evaluations;
    private Long dairyId;

    public PostDTO() {
        this.evaluations = new ArrayList<>();
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
