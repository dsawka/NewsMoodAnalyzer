package pl.coderslab.newsmoodanalyzer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.newsmoodanalyzer.dto.EvaluationDTO;
import pl.coderslab.newsmoodanalyzer.model.Evaluation;
import pl.coderslab.newsmoodanalyzer.service.EvaluationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/evaluations")
@Slf4j
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;
    private static final ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<EvaluationDTO>> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationService.getAllEvaluations();
        List<EvaluationDTO> evaluationDTOs = evaluations.stream()
                .map(this::mapEvaluationToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(evaluationDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationDTO> getEvaluationById(@PathVariable("id") Long id) {
        Evaluation evaluation = evaluationService.getEvaluationById(id);
        EvaluationDTO evaluationDTO = mapEvaluationToDto(evaluation);
        return ResponseEntity.ok(evaluationDTO);
    }

    @PostMapping
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        Evaluation evaluation = mapDtoToEvaluation(evaluationDTO);
        Evaluation createdEvaluation = evaluationService.createEvaluation(evaluation);
        EvaluationDTO createdEvaluationDTO = mapEvaluationToDto(createdEvaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvaluationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationDTO> updateEvaluation(@PathVariable("id") Long id, @RequestBody EvaluationDTO evaluationDTO) {
        Evaluation existingEvaluation = evaluationService.getEvaluationById(id);
        Evaluation updatedEvaluation = mapDtoToEvaluation(evaluationDTO);
        updatedEvaluation.setId(existingEvaluation.getId());
        Evaluation savedEvaluation = evaluationService.updateEvaluation(updatedEvaluation);
        EvaluationDTO savedEvaluationDTO = mapEvaluationToDto(savedEvaluation);
        return ResponseEntity.ok(savedEvaluationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable("id") Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }

    private EvaluationDTO mapEvaluationToDto(Evaluation evaluation) {
        return modelMapper.map(evaluation, EvaluationDTO.class);
    }

    private Evaluation mapDtoToEvaluation(EvaluationDTO evaluationDTO) {
        return modelMapper.map(evaluationDTO, Evaluation.class);
    }
}