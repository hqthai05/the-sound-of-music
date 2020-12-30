package com.skedulo.assignment;


import com.skedulo.assignment.model.Performance;
import com.skedulo.assignment.model.RawPerformance;
import com.skedulo.assignment.service.SchedulingService;
import com.skedulo.assignment.util.CommonFunctions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SchedulingServiceTest {

    private String inputDirectory = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "samples" + File.separator;
    private List<String> filesName = new ArrayList<>(Arrays.asList(
            "case-1",
            "case-2",
            "case-3",
            "case-4",
            "case-5"
    ));


    @InjectMocks
    private SchedulingService schedulingService;

    @Test
    public void testSchedulingService(){
        List<Performance> scheduledPerformances;
        List<RawPerformance> actualPerformances;
        for(String fileName : filesName) {
            System.out.println("Process validation of " + fileName);
            scheduledPerformances = CommonFunctions.readFile(inputDirectory + fileName + ".json");
            schedulingService.run(scheduledPerformances);
            actualPerformances =  CommonFunctions.readRawFile(inputDirectory + fileName + ".optimal.json");
            Assert.assertEquals(schedulingService.getResult(),actualPerformances);
            schedulingService.clearResult();
        }


    }
}
