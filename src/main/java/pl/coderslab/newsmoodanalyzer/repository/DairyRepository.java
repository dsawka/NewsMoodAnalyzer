package pl.coderslab.newsmoodanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.newsmoodanalyzer.model.Dairy;


@Repository
public interface DairyRepository extends JpaRepository<Dairy, Long> {
}

