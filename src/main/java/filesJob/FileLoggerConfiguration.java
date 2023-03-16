package filesJob;



public record FileLoggerConfiguration(
        String directoryPath,
        LoggingLevel level,
        int maxSize
) {

}
