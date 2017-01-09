package uk.cooperca.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.cooperca.entity.Operation;
import uk.cooperca.repository.OperationRepository;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller for {@link Operation}s.
 *
 * @author Charlie Cooper
 */
@RestController
@RequestMapping("/api/operations")
public class OperationResource {

    @Autowired
    private OperationRepository operationRepository;

    @GetMapping
    public ResponseEntity<List<Operation>> getAll() {
        List<Operation> operations = operationRepository.findAll();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOne(@PathVariable Long id) {
        Operation operation = operationRepository.findOne(id);
        return Optional.ofNullable(operation)
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
