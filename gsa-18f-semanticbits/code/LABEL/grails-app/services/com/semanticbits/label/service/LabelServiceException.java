package com.semanticbits.label.service;

/**
 * Thrown to indicate an error in the label application service layer 
 * @author gopal
 *
 */
public class LabelServiceException extends Exception {
    private static final long serialVersionUID = 7611373392767730405L;

    public LabelServiceException(String message) {
        super(message);
    }

    public LabelServiceException(String message, Exception e) {
        super(message, e);
    }
}
