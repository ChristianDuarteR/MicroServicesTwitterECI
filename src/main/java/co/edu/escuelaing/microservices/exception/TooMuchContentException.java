package co.edu.escuelaing.microservices.exception;

public class TooMuchContentException extends Exception{
    public TooMuchContentException(){
        super("El post no puede superar los 140 caracteres");
    }
}
