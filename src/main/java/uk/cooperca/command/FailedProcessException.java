package uk.cooperca.command;

/**
 * An exception thrown when a process exits with a non-zero exit value.
 *
 * @author Charlie Cooper
 */
public class FailedProcessException extends Exception {

    public FailedProcessException(int exitValue) {
        super("process failed with status code " + exitValue);
    }
}
