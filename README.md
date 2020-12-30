# Skedulo assignment - The sound of music

Sally Salamander is going to the "Big Weekend Out" music festival, and has a list of acts she wants to see. 
Unfortunately, many of these performances overlap in time. In order to determine where to be at what time, Sally has taken the list of events published on the Big Weekend website and added a priority to each from 1 to 10 (from least to most important).

**com.skedulo.assignment.Performance**
```bash
 - band: String 
 - start: DateTime 
 - finish: DateTime 
 - priority: Integer
``` 

## Requirement
Write a program for Sally that takes a list of com.skedulo.assignment.Performance objects as input and produces Sally’s optimal schedule. 
1. Keep in mind Sally wants to see the best performance at any given time. This may mean cutting one event short to see a higher priority performance, then returning to the original later. 
2. You can assume Sally has a teleportation device and can travel between stages instantaneously! 
3. If two performances starting at the same time have the same priority, Sally is happy to go to either one. 
4. There may also be gaps where no performances are on. 

The program should take one argument on the command line: the path to an input file. 
2. The input file will be JSON, containing an array of com.skedulo.assignment.Performance objects as described above. See the end of this document for an example. 
a. DateTime’s are represented as strings in ISO8601 format. 
b. To simplify the solution, you can assume the file exists, fits into memory and contains valid JSON (no need for error handling when ingesting the file). 
3. The program should create a new JSON file in the same directory as the input file, with the extension changed to ‘.optimal.json’. 
a. For example, if the input file name was ‘performances.json’, the output file name would be ‘performances.optimal.json’. 
4. The output file should represent Sally’s schedule for the festival as an array of objects, as defined below. See the end of this document for an example.
**ScheduledPerformance** 
```bash
 - band: String 
 - start: DateTime 
 - finish: DateTime
``` 

## How run application
We have to install Maven (3.5.2) and Java (1.8) in machine before have further action to set up and run project
Extract the zip file and open commandline from this point, type following bellow commands

**Build project**
```bash
build.bat
```
**Run project**

Only 1 parameter is the path to contain files
```bash
run.bat src\main\resources\samples
```

After run successfully, new file name "overlapping.optimal.json" was created. It will contain the optimal list of performances. 

## Example
Input file (overlapping.json): 
```bash
[ 
    { 
        "band" : "Soundgarden", 
        "start" : "1993-05-25T02:00:00Z", 
        "finish" : "1993-05-25T02:50:00Z", 
        "priority" : 5 
    }, 
    { 
        "band" : "Pearl Jam", 
        "start" : "1993-05-25T02:15:00Z", 
        "finish" : "1993-05-25T02:35:00Z", 
        "priority" : 9 
    } 
] 
```
Output file (overlapping.optimal.json): 
```bash
[ 
    { 
        "band" : "Soundgarden", 
        "start" : "1993-05-25T02:00:00Z", 
        "finish" : "1993-05-25T02:15:00Z" 
    }, 
    { 
        "band" : "Pearl Jam", 
        "start" : "1993-05-25T02:15:00Z", 
        "finish" : "1993-05-25T02:35:00Z" 
    }, 
    { 
        "band" : "Soundgarden", 
        "start" : "1993-05-25T02:35:00Z", 
        "finish" : "1993-05-25T02:50:00Z" 
    } 
]
```