package filesJob;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileLoggerConfigurationLoader {
    @SneakyThrows
    public static FileLoggerConfiguration load(String configPath) {
        Properties props = new Properties();
        try (InputStream inputStream = FileLoggerConfiguration.class.getResourceAsStream(configPath)) {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var path = (String) props.get("path");
        var level = LoggingLevel.valueOf(props.get("level").toString());
        var maxSize = Integer.parseInt(props.get("max-size").toString());
        return new FileLoggerConfiguration(path, level, maxSize);
    }
}
