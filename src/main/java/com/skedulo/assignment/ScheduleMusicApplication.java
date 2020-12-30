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
                List<String> files = CommonFunctions.listFilesUnderDirectory(args[0]);
                for(String fileName : files) {
                    List<Performance> performances = CommonFunctions.readFile(fileName);
                    SchedulingService service = new SchedulingService();
                    service.run(performances);
                    CommonFunctions.writeFile(fileName, service.getResult());

                    System.out.println("\n===========================================================================");
                    System.out.println("Created successfully file which contain list of optimal performances.\n" +
                            "Please check the file " + fileName + ".optimal.json inside current working directory.");
                    System.out.println("===========================================================================");
                }
                break;
            default:
                System.out.println("\n==================================================================");
                System.out.println("Unsupported function. Please make sure have parameter to valid path");
                System.out.println("==================================================================");
                break;
        }
    }

}
