package com.farenda.java.nio;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

public class WatchServiceExample {

    public static void main(String[] args) throws IOException {
        Path logs = Paths.get("/var/log");
        WatchServiceExample example = new WatchServiceExample();
        example.monitor(logs);
    }

    private final WatchService watcher;

    public WatchServiceExample() throws IOException {
        watcher = FileSystems.getDefault().newWatchService();
    }

    private void monitor(Path dir) throws IOException {
        // See StandardWatchEventKinds for more event types.
        dir.register(watcher, ENTRY_MODIFY);

        System.out.println("Starting monitoring of: " + dir);

        boolean monitor = true;
        while (monitor) {
            WatchKey key = waitForChange();

            if (key == null) {
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == OVERFLOW) {
                    System.err.println("Overflow event: rechecking.");
                    continue;
                }

                // Modified path is relative to parent:
                Path modifiedPath = (Path) event.context();
                System.out.printf("%s modified %d times.%n",
                        modifiedPath, event.count());

                // Reset to receive further events:
                // False means that the key became invalid.
                monitor = key.reset();
            }
        }

        System.err.printf("The key for %s is no longer valid!%n", dir);

        watcher.close();
    }

    private WatchKey waitForChange() {
        try {
            return watcher.take();
        } catch (InterruptedException e) {
            System.err.println("Error while waiting for key: "
                    + e.getMessage());
        }
        return null;
    }
}
