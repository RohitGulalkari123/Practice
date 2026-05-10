package com.core.javaFeatures;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Java11Features {
    public static void main(String[] args) throws IOException {
        /*
         * ============================================================
         * 1. var in Lambda Parameters
         * ============================================================
         *
         * BEFORE JAVA 11:
         *
         * (String s) -> s.length()
         *
         * ISSUE:
         * - Cannot use annotations consistently
         *
         * JAVA 11:
         * - Allows var in lambda
         */

        List<String> names = List.of("Rohit", "Java", "Spring");

        names.stream()
                .map((var name) -> name.toUpperCase())
                .forEach(System.out::println);

        /*
         * ============================================================
         * 2. String New Methods
         * ============================================================
         */

        String blankString = "   ";

        // BEFORE:
        // blankString.trim().isEmpty()

        // JAVA 11:
        System.out.println(blankString.isBlank());

        String text = "Java\nSpring\nKafka";

        // BEFORE:
        // Complex split logic

        // JAVA 11:
        text.lines().forEach(System.out::println);

        // repeat()
        System.out.println("=".repeat(20));

        // strip() -> Unicode aware trim
        System.out.println("   Hello   ".strip());

        /*
         * ============================================================
         * 3. Files.readString() and writeString()
         * ============================================================
         *
         * BEFORE:
         * Files.readAllBytes(path)
         *
         * JAVA 11:
         * Cleaner API
         */

        Path path = Path.of("sample.txt");

        Files.writeString(path, "Java 11 New File API");

        String fileContent = Files.readString(path);

        System.out.println(fileContent);

        /*
         * ============================================================
         * 4. Collection.toArray(IntFunction)
         * ============================================================
         *
         * BEFORE:
         * list.toArray(new String[0])
         *
         * JAVA 11:
         */

        String[] array = names.toArray(String[]::new);

        for (String s : array) {
            System.out.println(s);
        }

        /*
         * ============================================================
         * 5. Optional.isEmpty()
         * ============================================================
         */

        Optional<String> optional = Optional.empty();

        // BEFORE:
        // !optional.isPresent()

        // JAVA 11:
        System.out.println(optional.isEmpty());


    }
}
