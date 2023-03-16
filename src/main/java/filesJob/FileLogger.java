package filesJob;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileLogger {
    private final FileLoggerConfiguration configuration;

    public FileLogger(FileLoggerConfiguration configuration) {
        this.configuration = configuration;
        try {
            Files.createDirectories(Path.of(configuration.directoryPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void debug(String message) {
        if (configuration.level() == LoggingLevel.INFO) {
            return;
        }
        log(message, LoggingLevel.DEBUG);
    }

    public void info(String message) {
        if (configuration.level() == LoggingLevel.DEBUG) {
            return;
        }
        log(message, LoggingLevel.INFO);

    }

    @SneakyThrows
    private void log(String message, LoggingLevel level) {
        File file = new File(configuration.directoryPath() + "/output.log");
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm"));
        String outputMessage = String.format("%s [%s] Message %s", time, level, message);
        long fileSize;
        try {
            fileSize = Files.size(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (fileSize + outputMessage.getBytes(UTF_8).length > configuration.maxSize()) {
            throw new RuntimeException("FileMaxSizeReachedException");
        }
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(outputMessage);
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

