package pl.coderslab.newsmoodanalyzer.service;

import pl.coderslab.newsmoodanalyzer.model.Author;

import java.util.List;


public interface AuthorService {

    List<Author> getAllAuthors();

    Author getAuthorById(Long id);

    Author createAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthor(Long id);
}
