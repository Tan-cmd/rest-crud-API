package springboot.cruddemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentRestExceptionHandler {

	// Feedback required when not found
	@ExceptionHandler
	public ResponseEntity<StudentErrorReponse> handleException(StudentNotFoundException exc) {
		StudentErrorReponse error = new StudentErrorReponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<StudentErrorReponse>(error, HttpStatus.NOT_FOUND);
	}

	// Feedback required when enter char
	@ExceptionHandler
	public ResponseEntity<StudentErrorReponse> handleException(Exception exc) {
		StudentErrorReponse error = new StudentErrorReponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<StudentErrorReponse>(error,HttpStatus.BAD_REQUEST);
	}
}
