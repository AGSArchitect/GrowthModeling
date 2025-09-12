package com.cardioai.tools.modeling;

import java.util.ArrayList;
import java.util.List;

/**
 * Forecast
 *
 * @author Ariel Gonzalez
 * @version 1.0
 */
public class Forecast {

    private final String id;
    private final Configuration configuration;
    private final List<Metrics> metrics;
    private final long created;

    public Forecast(String id, Configuration configuration, long created) {
        this.id = id;
        this.configuration = configuration;
        this.created = created;
        metrics = new ArrayList();
    }

    public String getId() {
        return id;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<Metrics> getMetrics() {
        return metrics;
    }

    public long getCreated() {
        return created;
    }
}
