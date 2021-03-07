package net.dmatseku.gps.command_manager;

public class UsageBuilder {
    private static final String openRequired = "<";
    private static final String closeRequired = ">";
    private static final String openAdditional = "[";
    private static final String closeAdditional = "]";
    private final StringBuilder result = new StringBuilder();

    private UsageBuilder(String begin) {
        result.append(begin);
    }

    public static UsageBuilder createCommandClarification
            (String command) {
        return new UsageBuilder("@gps " + command);
    }

    public static UsageBuilder createArgumentsClarification() {
        return new UsageBuilder("");
    }

    public UsageBuilder addRequiredArgument(String argName) {
        result.append(' ').append(openRequired).append(argName).append(closeRequired);
        return this;
    }

    public UsageBuilder addAdditionalArgument(String argName) {
        result.append(' ').append(openAdditional).append(argName).append(closeAdditional);
        return this;
    }

    public UsageBuilder addRequiredArgumentClarification(String argName, String description) {
        result.append('\n')
                .append(openRequired).append(argName).append(closeRequired)
                .append(" - ")
                .append(description);
        return this;
    }

    public UsageBuilder addAdditionalArgumentClarification(String argName, String description) {
        result.append('\n')
                .append(openAdditional).append(argName).append(closeAdditional)
                .append(" - ")
                .append(description);
        return this;
    }

    public String build() {
        return result.toString();
    }
}
