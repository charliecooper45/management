package uk.cooperca.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.cooperca.entity.Operation;
import uk.cooperca.repository.OperationRepository;

import java.util.List;

/**
 * Rest controller for Operations.
 *
 * @author Charlie Cooper
 */
@RestController
@RequestMapping
public class OperationResource {

    @Autowired
    private OperationRepository operationRepository;

    @GetMapping
    public ResponseEntity<List<Operation>> get() {
        List<Operation> operations = operationRepository.findAll();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
}
