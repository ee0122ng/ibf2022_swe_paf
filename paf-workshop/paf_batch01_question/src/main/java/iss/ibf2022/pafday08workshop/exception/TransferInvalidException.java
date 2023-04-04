package iss.ibf2022.pafday08workshop.exception;

public class TransferInvalidException extends RuntimeException {

    public TransferInvalidException() {

    }

    public TransferInvalidException(String message) {

        super(message);
    }
    
}
