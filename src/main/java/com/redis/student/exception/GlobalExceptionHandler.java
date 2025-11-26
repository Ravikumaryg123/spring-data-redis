//package com.surest.student.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    public static class ApiError {
//        private String timestamp;
//        private int status;
//        private String error;
//        private String path;
//
//        public ApiError(HttpStatus status, String path) {
//            this.timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//            this.status = status.value();
//            this.error = status.getReasonPhrase();
//            this.path = path;
//        }
//        // getters and setters omitted for brevity
//    }
//
//    @ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFound ex, WebRequest request) {
//        ApiError error = new ApiError(HttpStatus.NOT_FOUND, request.getDescription(false).replace("uri=", ""));
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return new ResponseEntity<>(error, headers, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleAllExceptions(Exception ex, WebRequest request) {
//        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false).replace("uri=", ""));
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return new ResponseEntity<>(error, headers, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
