package uk.cooperca.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.cooperca.entity.Operation;
import uk.cooperca.service.OperationService;

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
    private OperationService operationService;

    @GetMapping
    public ResponseEntity<List<Operation>> getAll() {
        List<Operation> operations = operationService.findAll();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOne(@PathVariable Long id) {
        Operation operation = operationService.findOne(id);
        return Optional.ofNullable(operation)
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity performOperation(@PathVariable Long id) {
        try {
            operationService.performOperation(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(null);
    }
}
