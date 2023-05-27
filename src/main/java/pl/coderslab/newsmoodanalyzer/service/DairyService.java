package pl.coderslab.newsmoodanalyzer.service;

import pl.coderslab.newsmoodanalyzer.model.Dairy;

import java.util.List;


public interface DairyService {
    List<Dairy> getAllDairies();

    Dairy getDairyById(Long id);

    Dairy createDairy(Dairy dairy);

    Dairy updateDairy(Dairy dairy);

    void deleteDairy(Long id);
}


