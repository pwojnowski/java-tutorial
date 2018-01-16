package com.farenda.java.lang;

public class TryWithResources {

    static class CostlyResource implements AutoCloseable {

        public static CostlyResource open() {
            System.out.println("CostlyResource.open()");
            return new CostlyResource();
        }

        @Override
        public void close() {
            System.out.println("CostlyResource.close()");
        }
    }

    public static void main(String[] args) {
        tryWithFinally();
        tryWithResources();
    }

    private static void tryWithResources() {
        // CostlyResource.close() will be automatically called
        // at the and of the "try" block
        try (CostlyResource resource = CostlyResource.open()) {
            System.out.println("Java 7 way: Try with resources");
        }
    }

    private static void tryWithFinally() {
        CostlyResource resource = null;
        try {
            resource = CostlyResource.open();
            System.out.println("Old way: try with finally");
        } finally {
            // Have to check and call close() manually:
            if (resource != null) {
                resource.close();
            }
        }
    }
}
