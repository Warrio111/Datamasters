package com.datamasters.controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ExceptionHandler {
    public void handleException(Exception ex) {
        // Log the exception to a file with a timestamp.
        try (FileWriter fileWriter = new FileWriter("error.log", true);
             PrintWriter writer = new PrintWriter(fileWriter)) {

            LocalDateTime timestamp = LocalDateTime.now();
            writer.println("Timestamp: " + timestamp);
            writer.println("An exception occurred:");
            ex.printStackTrace(writer);
            writer.println(); // Add a newline for separation.

            // You can also send error notifications or perform additional actions here.
            // For example, sending an email or alerting an administrator.

            System.err.println("An exception occurred: " + ex.getMessage());
        } catch (IOException ioException) {
            // If there's an issue writing to the log file, print a message to the console.
            System.err.println("Failed to log the exception: " + ioException.getMessage());
        }
    }
}
