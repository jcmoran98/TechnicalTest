package com.pfcti.technicaltest.exceptions;

/**
 * Exception thrown when an entity is not found.
 * 
 * 
 * @author  José Chávez
 * @since   0.0.1
 */
public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@code EntityNotFoundException} with {@code null}
     * as its detail message. The cause is not initialized.
     */
    public EntityNotFoundException() {
        super();
    }

    /**
     * Constructs a new {@code EntityNotFoundException} with the specified
     * detail message. The cause is not initialized.
     * 
     * @param message the detail message, which is saved for later retrieval
     *                by the {@link #getMessage()} method
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code EntityNotFoundException} with the specified
     * cause and a detail message of {@code (cause == null ? null : cause.toString())}.
     * 
     * @param cause the cause of the exception, which is saved for later retrieval
     *              by the {@link #getCause()} method. (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new {@code EntityNotFoundException} with the specified
     * detail message and cause.
     * 
     * @param message the detail message, which is saved for later retrieval
     *                by the {@link #getMessage()} method
     * @param cause the cause of the exception, which is saved for later retrieval
     *              by the {@link #getCause()} method. (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
