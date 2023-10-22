package com.datamasters.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ExceptionHandler {
    private static final String ERROR_LOG_FILE = "error.log";

    public void handleException(Exception ex) {
        try {
            logException(ex);
            notifyAdmin(ex);
        } catch (IOException ioException) {
            System.err.println("Failed to log the exception: " + ioException.getMessage());
        }
    }

    private void logException(Exception ex) throws IOException {
        try (FileWriter fileWriter = new FileWriter(ERROR_LOG_FILE, true);
             PrintWriter writer = new PrintWriter(fileWriter)) {

            LocalDateTime timestamp = LocalDateTime.now();
            writer.println("Timestamp: " + timestamp);
            writer.println("An exception occurred:");
            ex.printStackTrace(writer);
            writer.println();
        }
    }

    private void notifyAdmin(Exception ex) {
        System.err.println("An exception occurred: " + ex.getMessage());
    }
}
