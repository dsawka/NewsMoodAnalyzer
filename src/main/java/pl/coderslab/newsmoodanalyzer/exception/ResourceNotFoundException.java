package pl.coderslab.newsmoodanalyzer.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String author, String id, Long id1) {
        super(String.format("%s not found with %s : '%s'", author, id, id1));
    }
}

