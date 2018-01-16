package com.farenda.java.lang;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CompilerExample {

    private static final List<String> PROGRAM = Arrays.asList(
            "class SampleClass {",
            "  public int add(int x, int y) {",
            "    return x + y;",
            "  }",
            "}");

    public static void main(String[] args) throws IOException {
        System.out.println("Creating temporary file:");
        Path sourceFile = Files.createTempFile("java-tutorial", ".java");

        Files.write(sourceFile, PROGRAM, StandardCharsets.UTF_8);

        System.out.println("Compiling: " + sourceFile.toString());
        System.out.println("java.compiler: " + System.getProperty("java.compiler"));
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String s = sourceFile.toString();
        if (compiler.getTask(null, null, null, null, null, null).call()) {
            Path compiledFile = Paths.get(sourceFile.toString().replace(".java", ".class"));
            System.out.println("Running compiled class: " + compiledFile.toString());
        } else {
            System.out.println("Compilation failed!");
        }
    }
}
