package pl.coderslab.newsmoodanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.newsmoodanalyzer.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
