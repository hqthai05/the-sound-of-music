# Skedulo assignment - The sound of music

Sally Salamander is going to the "Big Weekend Out" music festival, and has a list of acts she wants to see. 
Unfortunately, many of these performances overlap in time. In order to determine where to be at what time, Sally has taken the list of events published on the Big Weekend website and added a priority to each from 1 to 10 (from least to most important).

**Performance**
```bash
 - band: String 
 - start: DateTime 
 - finish: DateTime 
 - priority: Integer
``` 

## Your Challenge
Write a program for Sally that takes a list of Performance objects as input and produces Sally’s optimal schedule. 
1. Keep in mind Sally wants to see the best performance at any given time. This may mean cutting one event short to see a higher priority performance, then returning to the original later. 
2. You can assume Sally has a teleportation device and can travel between stages instantaneously! 
3. If two performances starting at the same time have the same priority, Sally is happy to go to either one. 
4. There may also be gaps where no performances are on. 


## Instruction
The program should take one argument on the command line: the path to an input file. 
2. The input file will be JSON, containing an array of Performance objects as described above. See the end of this document for an example. 
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

**Please provide an archive file containing your source code, plus two simple scripts in the root directory:** 
```bash
a. build.sh or build.bat - builds the program 
b. run.sh or run.bat - a wrapper that runs the program 
```
- This script takes a single argument as described above and executes the program. 

We will check your solution by building it using the build script and then executing the run script multiple times with various input files.

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