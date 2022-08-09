package vn.nodo.tcpaymentpaymentservice.controller.advice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class AppControllerAdvice {

    private final ObjectMapper objectMapper;

    public AppControllerAdvice(ObjectMapper mapper) {
        objectMapper = mapper;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ObjectNode> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        ObjectError error0 = allErrors.get(0);
        String message = error0.getDefaultMessage();
        ObjectNode node = objectMapper.createObjectNode();
        node.put("message", message);
        return ResponseEntity.status(400).body(node);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ObjectNode> handleResponseStatusException(ResponseStatusException e) {
        ObjectNode node = objectMapper.createObjectNode();
        node.put("message", e.getReason());
        return ResponseEntity.status(e.getRawStatusCode()).body(node);
    }

}
