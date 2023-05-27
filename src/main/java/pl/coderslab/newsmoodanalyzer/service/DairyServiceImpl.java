package pl.coderslab.newsmoodanalyzer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.coderslab.newsmoodanalyzer.exception.ResourceNotFoundException;
import pl.coderslab.newsmoodanalyzer.model.Dairy;
import pl.coderslab.newsmoodanalyzer.repository.DairyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DairyServiceImpl implements DairyService {

    private final DairyRepository dairyRepository;

    @Override
    public List<Dairy> getAllDairies() {
        return dairyRepository.findAll();
    }


    @Override
    public Dairy getDairyById(Long id) {
        return dairyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dairy not found with id: " + id));
    }

    @Override
    public Dairy createDairy(Dairy dairy) {
        return dairyRepository.save(dairy);
    }

    @Override
    public Dairy updateDairy(Dairy dairy) {
        return dairyRepository.save(dairy);
    }

    @Override
    public void deleteDairy(Long id) {
        dairyRepository.deleteById(id);
    }
}
