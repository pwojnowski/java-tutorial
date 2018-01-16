package com.farenda.java.lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

import static java.util.stream.Collectors.joining;

public class ProcessBuilderExample {

    private File workingDirectory = new File("/tmp");
    private File logFile = new File("/tmp/process.log");

    public static void main(String[] args) throws IOException {
        ProcessBuilderExample example = new ProcessBuilderExample();
        example.start();
    }

    private void start() throws IOException {
        Process memInfo = runCommand("cat", "/proc/meminfo");
        displayMemoryInfo(memInfo);

        Process ls = runCommand("ls", "/root");
        try {
            // End the process to get "exit value":
            ls.destroyForcibly().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Exit code: " + ls.exitValue());
    }

    private void displayMemoryInfo(Process memInfo)
            throws IOException {
        System.out.println("Memory:");
        try (BufferedReader reader = createReader(memInfo)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private BufferedReader createReader(Process process) {
        return new BufferedReader(
                new InputStreamReader(
                        process.getInputStream()));
    }

    private Process runCommand(String... command)
            throws IOException {
        ProcessBuilder builder = new ProcessBuilder(command);

        // Optionally specify working directory:
        builder.directory(workingDirectory);
        // Append all errors from process to log file:
        builder.redirectError(Redirect.appendTo(logFile));

        String cmdLine = builder.command()
                .stream()
                .collect(joining(" "));

        System.out.println("\nStarting: " + cmdLine);
        Process process = builder.start();
        System.out.printf("'%s' running: %b%n",
                cmdLine, process.isAlive());

        return process;
    }
}
