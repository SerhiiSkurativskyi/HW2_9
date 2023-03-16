package filesJob;

public enum LoggingLevel {
    INFO("Info level"),
    DEBUG("Debug level");

    private final String value;
    LoggingLevel(String value) {
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}

