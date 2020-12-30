package com.skedulo.assignment.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * @author Simon Huynh
 */
public class RawPerformance {

    private String band;
    private String start;
    private String finish;

    public RawPerformance(String band, String start, String finish) {
        this.band = band;
        this.start = start;
        this.finish = finish;
    }

    public String getBand() {
        return band;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawPerformance that = (RawPerformance) o;
        return Objects.equals(band, that.band) &&
                Objects.equals(start, that.start) &&
                Objects.equals(finish, that.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(band, start, finish);
    }

    public LocalDateTime getLocalDateTimeStartTime(){
        return ZonedDateTime.parse(start).toLocalDateTime();
    }

    public LocalDateTime getLocalDateTimeFinishTime() {
        return ZonedDateTime.parse(finish).toLocalDateTime();
    }

    @Override
    public String toString() {
        return "RawPerformanceDTO{" +
                "band='" + band + '\'' +
                ", start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                '}';
    }
}
