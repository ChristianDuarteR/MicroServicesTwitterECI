package co.edu.escuelaing.microservices.exception;

public class SecurityException extends Exception{
    public SecurityException(String message){super("Security: "+message);}
}
