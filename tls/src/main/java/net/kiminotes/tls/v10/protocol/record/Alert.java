/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.record;

import java.io.Serializable;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class Alert implements Serializable {

    private static final long serialVersionUID = 2999849127353764992L;

    private AlertLevel       level;
    private AlertDescription description;

    public AlertLevel getLevel() {
        return level;
    }

    public void setLevel(AlertLevel level) {
        this.level = level;
    }

    public AlertDescription getDescription() {
        return description;
    }

    public void setDescription(AlertDescription description) {
        this.description = description;
    }
}
