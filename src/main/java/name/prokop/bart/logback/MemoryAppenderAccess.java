package name.prokop.bart.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;

public class MemoryAppenderAccess {

    public static String asText() {
        MemoryAppender instance = MemoryAppenderInstance.getInstance();
        if (instance == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (ILoggingEvent e : instance.getEvents()) {
            sb.append(e);
            sb.append('\n');
        }
        return sb.toString();
    }
}
