package uk.cooperca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.cooperca.entity.Execution;
import uk.cooperca.repository.ExecutionRepository;

import java.util.List;

/**
 * Service for {@link Execution}s.
 *
 * @author Charlie Cooper
 */
@Service
public class ExecutionService {

    @Autowired
    private ExecutionRepository executionRepository;

    public Execution saveOrUpdate(Execution execution) {
        return executionRepository.saveAndFlush(execution);
    }

    public List<Execution> findAll() {
        return executionRepository.findAll();
    }
}
