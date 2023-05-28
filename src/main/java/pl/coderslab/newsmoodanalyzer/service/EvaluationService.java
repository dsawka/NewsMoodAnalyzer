package pl.coderslab.newsmoodanalyzer.service;

import pl.coderslab.newsmoodanalyzer.dto.EvaluationDTO;
import pl.coderslab.newsmoodanalyzer.model.Evaluation;

import java.util.List;

public interface EvaluationService {

    List<Evaluation> getAllEvaluations();

    Evaluation getEvaluationById(Long id);

    Evaluation createEvaluation(Evaluation evaluation);

    Evaluation updateEvaluation(Evaluation evaluation);

    void deleteEvaluation(Long id);
}

