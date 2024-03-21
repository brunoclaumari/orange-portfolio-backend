package com.orangejuice.portfolio.controllers.exceptions;

import java.time.Instant;

import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.orangejuice.portfolio.services.exceptions.DatabaseException;
import com.orangejuice.portfolio.services.exceptions.MyValidationException;
import com.orangejuice.portfolio.services.exceptions.ResourceNotFoundException;
import com.orangejuice.portfolio.services.exceptions.UnauthorizedException;

import jakarta.servlet.http.HttpServletRequest;



@ControllerAdvice
public class ResourceExceptionHandler {	
	


	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.BAD_REQUEST;//400
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Bad Request");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<StandardError> unauthorized(UsernameNotFoundException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.UNAUTHORIZED;//401
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Unauthorized access. User name not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}	
	
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<StandardError> unauthorized(UnauthorizedException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.UNAUTHORIZED;//401
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Unauthorized");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<StandardError> badCredentials(AuthenticationException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.UNAUTHORIZED;//401
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Bad credentials.");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}	
	
	@ExceptionHandler(ApplicationContextException.class)
	public ResponseEntity<StandardError> databaseOut(ApplicationContextException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.SERVICE_UNAVAILABLE;//503
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Data base is out.");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
//	//EntityNotFoundException
//	@ExceptionHandler(EntityNotFoundException.class)
//	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
//		HttpStatus status=HttpStatus.UNPROCESSABLE_ENTITY;//422
//		
//		StandardError err=new StandardError();
//		err.setTimestamp(Instant.now());
//		err.setStatus(status.value());
//		err.setError("Entidade não encontrada. Reveja os parâmetros");
//		err.setMessage(e.getMessage());
//		err.setPath(request.getRequestURI());
//		
//		return ResponseEntity.status(status).body(err);
//	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.NOT_FOUND;//404
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Entidade ou recurso solicitado não encontrado.");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.BAD_REQUEST;//400
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Erro de acesso aos dados. Contate o administrador do sistema");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	//MyValidationException
	@ExceptionHandler(MyValidationException.class)
	public ResponseEntity<StandardError> validation(MyValidationException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.UNPROCESSABLE_ENTITY;//422
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Email repetido");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}

	
}
