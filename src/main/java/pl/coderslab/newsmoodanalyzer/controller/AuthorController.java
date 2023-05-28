package pl.coderslab.newsmoodanalyzer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.newsmoodanalyzer.dto.AuthorDTO;
import pl.coderslab.newsmoodanalyzer.model.Author;
import pl.coderslab.newsmoodanalyzer.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        AuthorDTO authorDTO = convertToDTO(author);
        return ResponseEntity.ok(authorDTO);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = convertToEntity(authorDTO);
        Author createdAuthor = authorService.createAuthor(author);
        AuthorDTO createdAuthorDTO = convertToDTO(createdAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        Author existingAuthor = authorService.getAuthorById(id);
        Author updatedAuthor = updateAuthorFromDTO(existingAuthor, authorDTO);
        Author savedAuthor = authorService.updateAuthor(updatedAuthor);
        AuthorDTO savedAuthorDTO = convertToDTO(savedAuthor);
        return ResponseEntity.ok(savedAuthorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        return authorDTO;
    }

    private Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        return author;
    }

    private Author updateAuthorFromDTO(Author author, AuthorDTO authorDTO) {
        author.setName(authorDTO.getName());
        return author;
    }
}
