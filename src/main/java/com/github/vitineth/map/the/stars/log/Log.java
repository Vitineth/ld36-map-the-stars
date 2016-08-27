package com.github.vitineth.map.the.stars.log;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A basic logger class that will print log messages of various types with the time they were sent and an identifier
 * for the sender.
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class Log {

    private static PrintStream out = System.out;
    private static PrintStream err = System.err;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd//HH:mm:ss");

    /**
     * Logs a message to the standard output.<br>
     *     <code>yyyy.MM.dd//HH:mm:ss:[&lt;type&gt;][&lt;label&gt;]:&lt;message&gt;</code>
     * @param type The type of error message. (eg Info, Warning)
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     * @param error If the output should use the error output stream.
     */
    private static void logMessage(String type, String label, String message, boolean error) {
        (error ? err : out).println(simpleDateFormat.format(new Date()) + ":[" + type.toUpperCase() + "][" + label + "]: " + message);
        (error ? err : out).flush();
    }

    /**
     * A wrapper call to {@link #logMessage(String, String, String, boolean)} that will parse a throwable into a 
     * readable stack trace. This will iterate through the {@link StackTraceElement} of the throwable and convert it
     * into a readable output. It will always be logged to the error stream, never to standard out to make it clear 
     * that an error as been logged. The actual message will be logged on whichever stream is determined by error.
     * @param type The type of error message. (eg Info, Warning)
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     * @param error If the output should use the error output stream.
     * @param throwable The throwable error to log.
     */
    private static void logMessage(String type, String label, String message, boolean error, Throwable throwable) {
        StringBuilder log = new StringBuilder();
        log.append(throwable.getClass().getName()).append(": ").append(throwable.getMessage()).append("\n");
        for (StackTraceElement element : throwable.getStackTrace()) {
            log.append("\t").append(element.getClassName()).append(".").append(element.getMethodName()).append("(").append(element.getFileName()).append(":").append(element.getLineNumber()).append(")\n");
        }
        logMessage(type, label, message, error);
        logMessage(type, label, log.toString(), true);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an info message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void info(String label, String message) {
        logMessage("info", label, message, false);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an info message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void info(String label, String message, Throwable error) {
        logMessage("info", label, message, false, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an info message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void i(String label, String message) {
        info(label, message);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an info message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void i(String label, String message, Throwable error) {
        info(label, message, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an debug message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void debug(String label, String message) {
        logMessage("debug", label, message, false);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an debug message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void debug(String label, String message, Throwable error) {
        logMessage("debug", label, message, false, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an debug message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void d(String label, String message) {
        debug(label, message);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an debug message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void d(String label, String message, Throwable error) {
        debug(label, message, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an warn message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void warn(String label, String message) {
        logMessage("warn", label, message, false);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an warn message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void warn(String label, String message, Throwable error) {
        logMessage("warn", label, message, false, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an warn message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void w(String label, String message) {
        warn(label, message);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an warn message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void w(String label, String message, Throwable error) {
        warn(label, message, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an error message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void error(String label, String message) {
        logMessage("error", label, message, true);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an error message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void error(String label, String message, Throwable error) {
        logMessage("error", label, message, true, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an error message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void e(String label, String message) {
        error(label, message);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an error message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void e(String label, String message, Throwable error) {
        error(label, message, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an severe message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void severe(String label, String message) {
        logMessage("severe", label, message, true);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an severe message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void severe(String label, String message, Throwable error) {
        logMessage("severe", label, message, true, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an severe message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void s(String label, String message) {
        severe(label, message);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an severe message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void s(String label, String message, Throwable error) {
        severe(label, message, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an fatal message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void fatal(String label, String message) {
        logMessage("fatal", label, message, true);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an fatal message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void fatal(String label, String message, Throwable error) {
        logMessage("fatal", label, message, true, error);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an fatal message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void f(String label, String message) {
        fatal(label, message);
    }

    /**
     * A wrapper for {@link #logMessage(String, String, String, boolean)} to print an fatal message.
     * @param label The label of the sender. This could be a class name or just a unique identifier.
     * @param message The actual message to be logged
     */
    public static void f(String label, String message, Throwable error) {
        fatal(label, message, error);
    }

}
