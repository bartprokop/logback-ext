package name.prokop.bart.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a in-memory logging appender for logback.
 *
 * @author Bartłomiej Prokop
 */
public class MemoryAppender extends AppenderBase<ILoggingEvent> {

    private int limit = 150;
    private final List<ILoggingEvent> events = new ArrayList<>();

    @Override
    public void start() {
        super.start();
        MemoryAppenderInstance.setInstance(this);
    }

    @Override
    public void stop() {
        MemoryAppenderInstance.setInstance(null);
        super.stop();
        events.clear();
    }

    @Override
    protected void append(ILoggingEvent e) {
        synchronized (events) {
            events.add(e);
            if (events.size() > limit && limit > 0) {
                events.remove(0);
            }
        }
    }

    public List<ILoggingEvent> getEvents() {
        List<ILoggingEvent> retVal = new ArrayList<>();
        synchronized (events) {
            retVal.addAll(events);
        }
        return retVal;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
