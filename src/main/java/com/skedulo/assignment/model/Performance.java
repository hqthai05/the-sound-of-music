package com.skedulo.assignment.model;

import java.util.Objects;

/**
 * @author Simon Huynh
 */
public class Performance extends RawPerformance {
    private int priority;

    public Performance(String band, String start, String finish, int priority) {
        super(band,start,finish);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Performance that = (Performance) o;
        return priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), priority);
    }
}
