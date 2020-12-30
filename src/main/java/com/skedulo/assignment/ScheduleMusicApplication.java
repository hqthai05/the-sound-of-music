package com.skedulo.assignment;

import com.skedulo.assignment.model.Performance;
import com.skedulo.assignment.service.SchedulingService;
import com.skedulo.assignment.util.CommonFunctions;

import java.util.List;

/**
 * The assignment backend test from Skedulo.
 * This application will provide to user the optimized list all performances
 *
 * @author  Simon Huynh
 */
public class ScheduleMusicApplication {

    public static void main(String[] args) {
        switch (args.length) {
            case 1:
                List<Performance> performances = CommonFunctions.readFile(args[0]);
                SchedulingService service = new SchedulingService();
                service.run(performances);
                CommonFunctions.writeFile(args[0], service.getResult());
                break;
            default:
                System.out.println("Unsupported function");
                break;
        }
    }

}
