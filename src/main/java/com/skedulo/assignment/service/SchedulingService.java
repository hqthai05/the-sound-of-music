package com.skedulo.assignment.service;

import com.skedulo.assignment.model.Performance;
import com.skedulo.assignment.model.RawPerformance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class will handle logic to schedule performance
 *
 * @author Simon Huynh
 */
public class SchedulingService {

    // List all performances were optimized
    private List<RawPerformance> result = new ArrayList<>();

    public void run(List<Performance> performances) {
        while (!performances.isEmpty()) {
            performances = processScheduling(performances);
        }
    }

    private List<Performance> processScheduling(List<Performance> performances) {
        // Sort elements first to make sure the first element is highest priority and first start time
        sortByStartTimeAndPriority(performances);

        // Pending queue will store all performances which don't display in current list
        List<Performance> pendingPerformances = new ArrayList<>();

        // Checking performance is temporary performance will select to display.
        Performance checkingPerformance = performances.get(0);
        for (Performance currentPerformance : performances) {
            if (checkingPerformance.equals(currentPerformance)) {
                // Ignore the first performance, waiting for the second to compare with the first one
                continue;
            }

            /*
                If 2 performances were overlapped, we will follow 2 conditions as bellow
                    1. Priority:
                        a. Lower priority of performance will validate time range before add to pending queue.
                        b. The one with higher priority will become candidate performance and waiting for next validation with next performance
                    2. Time range:
                        a. Lower priority has finish time before higher priority  --> don't add to pending queue
                        b. Lower priority has finish time after higher priority --> update start time and add to pending queue
             */
            if (isOverlap(checkingPerformance, currentPerformance)) {
                if (currentPerformance.getPriority() > checkingPerformance.getPriority()) {
                    result.add(new RawPerformance(checkingPerformance.getBand(), checkingPerformance.getStart(), currentPerformance.getStart()));
                    if (currentPerformance.getLocalDateTimeFinishTime().isBefore(checkingPerformance.getLocalDateTimeFinishTime())) {
                        checkingPerformance.setStart(currentPerformance.getFinish());
                        pendingPerformances.add(checkingPerformance);
                    }
                    checkingPerformance = currentPerformance;
                } else {
                    // Update time range of current performance before add to pending queue
                    if (checkingPerformance.getLocalDateTimeFinishTime().isBefore(currentPerformance.getLocalDateTimeFinishTime())) {
                        currentPerformance.setStart(checkingPerformance.getFinish());
                        pendingPerformances.add(currentPerformance);
                    }
                }
            }
            /*
                If candidate performance is not overlapped by next performance. We will combine all remaining performances
                with all performances in pending queue and waiting for result from next wave of process.
             */
            else {
                pendingPerformances.add(currentPerformance);
            }
        }

        result.add(new RawPerformance(checkingPerformance.getBand(), checkingPerformance.getStart(), checkingPerformance.getFinish()));
        return pendingPerformances;
    }

    /**
     * Sort collection by start time first and priority if they have the same start time
     * This action will reduce complexity of algorithm by provide the sorted list
     *
     * @param performances
     */
    private void sortByStartTimeAndPriority(List<Performance> performances) {
        Comparator<Performance> compareByStartTime = Comparator.comparing(Performance::getLocalDateTimeStartTime);
        Comparator<Performance> compareByPriority = Comparator.comparing(Performance::getPriority);
        Comparator<Performance> sortPerformanceByStartTimeAndPriority = compareByStartTime.thenComparing(compareByPriority.reversed());
        performances.sort(sortPerformanceByStartTimeAndPriority);
    }

    /**
     * This function will check 2 performances were overlap or not
     *
     * @param a
     * @param b
     * @return true if they were overlapped.
     */
    private boolean isOverlap(Performance a, Performance b) {
        return (a.getLocalDateTimeFinishTime().isAfter(b.getLocalDateTimeStartTime())
                && a.getLocalDateTimeStartTime().isBefore(b.getLocalDateTimeFinishTime()));
    }

    public List<RawPerformance> getResult() {
        return result;
    }

    public void clearResult() {
        result.clear();
    }

}
