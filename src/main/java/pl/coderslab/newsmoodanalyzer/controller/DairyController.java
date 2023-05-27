package pl.coderslab.newsmoodanalyzer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.newsmoodanalyzer.dto.DairyDTO;
import pl.coderslab.newsmoodanalyzer.model.Author;
import pl.coderslab.newsmoodanalyzer.model.Dairy;
import pl.coderslab.newsmoodanalyzer.service.AuthorService;
import pl.coderslab.newsmoodanalyzer.service.DairyService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/dairies")
@Slf4j
@RequiredArgsConstructor
public class DairyController {

    private final DairyService dairyService;
    @Autowired
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<DairyDTO>> getAllDairies() {
        List<Dairy> dairies = dairyService.getAllDairies();
        List<DairyDTO> dairyDTOs = dairies.stream()
                .map(this::mapDairyToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dairyDTOs);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DairyDTO> getDairyById(@PathVariable("id") Long id) {
        Dairy dairy = dairyService.getDairyById(id);
        DairyDTO dairyDTO = mapDairyToDto(dairy);
        return ResponseEntity.ok(dairyDTO);
    }

    @PostMapping
    public ResponseEntity<DairyDTO> createDairy(@RequestBody DairyDTO dairyDTO) {
        Dairy dairy = mapDtoToDairy(dairyDTO);
        Dairy createdDairy = dairyService.createDairy(dairy);
        DairyDTO createdDairyDTO = mapDairyToDto(createdDairy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDairyDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DairyDTO> updateDairy(@PathVariable("id") Long id, @RequestBody DairyDTO dairyDTO) {
        Dairy existingDairy = dairyService.getDairyById(id);
        Dairy updatedDairy = mapDtoToDairy(dairyDTO);
        updatedDairy.setId(existingDairy.getId());
        Dairy savedDairy = dairyService.updateDairy(updatedDairy);
        DairyDTO savedDairyDTO = mapDairyToDto(savedDairy);
        return ResponseEntity.ok(savedDairyDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDairy(@PathVariable("id") Long id) {
        dairyService.deleteDairy(id);
        return ResponseEntity.noContent().build();
    }

    private DairyDTO mapDairyToDto(Dairy dairy) {
        return DairyDTO.builder()
                .id(dairy.getId())
                .name(dairy.getName())
                .ownerId(dairy.getOwner().getId())
                .build();
    }


    private Dairy mapDtoToDairy(DairyDTO dto) {
        Dairy dairy = new Dairy();
        dairy.setId(dto.getId());
        dairy.setName(dto.getName());
        // Pobieram i ustawiam właściciela Dairy
        Author owner = authorService.getAuthorById(dto.getOwnerId());
        dairy.setOwner(owner);
        return dairy;
    }

}
