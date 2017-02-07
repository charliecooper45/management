package uk.cooperca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.cooperca.command.CommandExecutor;
import uk.cooperca.entity.Operation;
import uk.cooperca.repository.OperationRepository;

import java.io.IOException;
import java.util.List;

/**
 * Service for {@link Operation}s.
 *
 * @author Charlie Cooper
 */
@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private CommandExecutor commandExecutor;

    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    public Operation findOne(Long id) {
        return operationRepository.findOne(id);
    }

    public void performOperation(Long id) throws IOException, InterruptedException {
        commandExecutor.executeOperation(id);
    }
}
