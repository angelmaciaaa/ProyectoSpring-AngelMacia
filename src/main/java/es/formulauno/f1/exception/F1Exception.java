package es.formulauno.f1.exception;

public class F1Exception extends Exception{
    private ErrorCode code;

    public F1Exception(ErrorCode code, String message){
        super(message);
        this.code=code;
    }

    public String getMessageError(){
        return code + ": " + getMessage();
    }
}
