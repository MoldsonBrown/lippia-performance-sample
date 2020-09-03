## Lippia Web sample project - Linux users

## System Requirements :
+ Docker 18.09: https://docs.docker.com/install/linux/docker-ce/ubuntu/
+ Docker compose 1.24: https://docs.docker.com/compose/install/

# Getting started
 [Download]: <https://bitbucket.org/crowdarautomation/lippia-performance-sample-project/get/master.zip>
- [Download] and unzip the source repository for this guide, or clone it using Git:
    ``` $ git clone https://bitbucket.org/crowdarautomation/lippia-performance-sample-project.git ```
- Go to root directory  
    ``` $ cd lippia-performance-sample-project ```
## Strategies to run
***
- [Stack](#remote) (running docker-compose)  

#### Stack strategy
##### Executing tests in you local machine from command line
- From terminal  execute  
`$ sudo docker-compose -f docker-compose.yml up --abort-on-container-exit --exit-code-from lippia`    
   
```
$sudo docker-compose -f docker-compose.yml up --abort-on-container-exit --exit-code-from lippia
Starting lippia-performance-sample-project_lippia_1 ... done
Attaching to lippia-performance-sample-project_lippia_1
lippia_1  | Running custom Command mvn clean verify -Plocal -Dcucumber.tags=["@Example","--tags","@1ps"]
lippia_1  | [INFO] Scanning for projects...
lippia_1  | [INFO] 
lippia_1  | [INFO] -----------< com.Crowdar:lippia-performance-example-project >-----------
lippia_1  | [INFO] Building performance-automation 1.0-SNAPSHOT
lippia_1  | [INFO] --------------------------------[ jar ]---------------------------------
lippia_1  | [INFO] 
lippia_1  | [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ lippia-performance-example-project ---
lippia_1  | [INFO] Deleting /opt/workspace/automation/target
lippia_1  | [INFO] 
lippia_1  | [INFO] --- maven-resources-plugin:2.4:resources (default-resources) @ lippia-performance-example-project ---
lippia_1  | [INFO] Using 'UTF-8' encoding to copy filtered resources.
lippia_1  | [INFO] Copying 3 resources
lippia_1  | [INFO] 
lippia_1  | [INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ lippia-performance-example-project ---
lippia_1  | [INFO] Changes detected - recompiling the module!
lippia_1  | [INFO] Compiling 17 source files to /opt/workspace/automation/target/classes
lippia_1  | [INFO] 
lippia_1  | [INFO] --- maven-resources-plugin:2.4:testResources (default-testResources) @ lippia-performance-example-project ---
lippia_1  | [INFO] Using 'UTF-8' encoding to copy filtered resources.
lippia_1  | [INFO] Copying 4 resources
lippia_1  | [INFO] 
lippia_1  | [INFO] --- maven-compiler-plugin:3.7.0:testCompile (default-testCompile) @ lippia-performance-example-project ---
lippia_1  | [INFO] Changes detected - recompiling the module!
lippia_1  | [INFO] Compiling 5 source files to /opt/workspace/automation/target/test-classes
lippia_1  | [INFO] /opt/workspace/automation/src/test/java/com/crowdar/performance/steps/TemplatedSteps.java: Some input files use or override a deprecated API.
lippia_1  | [INFO] /opt/workspace/automation/src/test/java/com/crowdar/performance/steps/TemplatedSteps.java: Recompile with -Xlint:deprecation for details.
lippia_1  | [INFO] /opt/workspace/automation/src/test/java/com/crowdar/performance/steps/TemplatedSteps.java: Some input files use unchecked or unsafe operations.
lippia_1  | [INFO] /opt/workspace/automation/src/test/java/com/crowdar/performance/steps/TemplatedSteps.java: Recompile with -Xlint:unchecked for details.
lippia_1  | [INFO] 
lippia_1  | [INFO] --- maven-surefire-plugin:3.0.0-M4:test (default-test) @ lippia-performance-example-project ---
lippia_1  | [INFO] 
lippia_1  | [INFO] -------------------------------------------------------
lippia_1  | [INFO]  T E S T S
lippia_1  | [INFO] -------------------------------------------------------
lippia_1  | [INFO] Running TestSuite
lippia_1  | @Performance @Example
lippia_1  | Feature: Existing jmx file
lippia_1  | COMO un usuario del sitio
lippia_1  | QUIERO ejecutar peticiones a las Sucursales
lippia_1  | PARA verificar que el mismo responda apropiadamente.
lippia_1  | 
lippia_1  |   Scenario Outline: Run performance with existing jmx file # src/test/resources/features/feature_example.feature:7
lippia_1  |     Given configure the performance test with '<iterations>' iterations '<users>' users and a ramp time of '<ramp_time>' seconds
lippia_1  |     When run the script '<script_name>'
lippia_1  |     And remplace variables with '<json>'
lippia_1  | 
lippia_1  |     @1ps
lippia_1  |     Examples: 
lippia_1  | 
lippia_1  |   @Performance @Example @1ps
lippia_1  |   Scenario Outline: Run performance with existing jmx file                                            # src/test/resources/features/feature_example.feature:15
lippia_1  |     Given configure the performance test with '1' iterations '1' users and a ramp time of '1' seconds # ExistingFileSteps.configThreadGroup(String,String,String)
lippia_1  |     When run the script 'lippia_example'                                                              # ExistingFileSteps.runScript(String)
lippia_1  |     And remplace variables with 'exampleJson'                                                         # ExistingFileSteps.replaceVariables(String)
lippia_1  | [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.601 s - in TestSuite
lippia_1  | [INFO] 
lippia_1  | [INFO] Results:
lippia_1  | [INFO] 
lippia_1  | [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
lippia_1  | [INFO] 
lippia_1  | [INFO] 
lippia_1  | [INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ lippia-performance-example-project ---
lippia_1  | [INFO] Building jar: /opt/workspace/automation/target/lippia-performance-example-project-1.0-SNAPSHOT.jar
lippia_1  | [INFO] 
lippia_1  | [INFO] >>> jmeter-maven-plugin:2.8.6:jmeter (jmeter-tests) > :configure @ lippia-performance-example-project >>>
lippia_1  | [INFO] 
lippia_1  | [INFO] --- jmeter-maven-plugin:2.8.6:configure (configure) @ lippia-performance-example-project ---
lippia_1  | [INFO] -------------------------------------------------------
lippia_1  | [INFO]  Configuring JMeter...
lippia_1  | [INFO] -------------------------------------------------------
lippia_1  | [INFO]  Building JMeter directory structure...
lippia_1  | [INFO]  Configuring JMeter artifacts :[]
lippia_1  | [INFO]  Populating JMeter directory ...
lippia_1  | [INFO]  Copying extensions [plugins:influxdbwriter:1.2, plugins:postgresql:42.2] to JMeter lib/ext directory /opt/workspace/automation/target/jmeter/lib/ext with downloadExtensionDependencies set to true ...
lippia_1  | [INFO]  Copying  JUnit libraries [] to JMeter junit lib directory /opt/workspace/automation/target/jmeter/lib/junit with downloadLibraryDependencies set to true ...
lippia_1  | [INFO]  Copying test libraries [] to JMeter lib directory /opt/workspace/automation/target/jmeter/lib with downloadLibraryDependencies set to true ...
lippia_1  | [INFO]  Configuring jmeter properties ...
lippia_1  | [INFO]  Generating JSON Test config ...
lippia_1  | [INFO] 
lippia_1  | [INFO] <<< jmeter-maven-plugin:2.8.6:jmeter (jmeter-tests) < :configure @ lippia-performance-example-project <<<
lippia_1  | [INFO] 
lippia_1  | [INFO] 
lippia_1  | [INFO] --- jmeter-maven-plugin:2.8.6:jmeter (jmeter-tests) @ lippia-performance-example-project ---
lippia_1  | [INFO]  
lippia_1  | [INFO] -------------------------------------------------------
lippia_1  | [INFO]  P E R F O R M A N C E    T E S T S
lippia_1  | [INFO] -------------------------------------------------------
lippia_1  | [INFO]  
lippia_1  | [INFO] Will generate HTML report in /opt/workspace/automation/target/jmeter/reports/lippia_example
lippia_1  | [INFO]  
lippia_1  | [INFO] Executing test: lippia_example.jmx
lippia_1  | [INFO] Starting process with:[java, -Xms512M, -Xmx512M, -Djava.awt.headless=true, -jar, ApacheJMeter-5.1.jar, -d, /opt/workspace/automation/target/jmeter, -e, -j, /opt/workspace/automation/target/jmeter/logs/lippia_example.jmx.log, -l, /opt/workspace/automation/target/jmeter/results/20200901-lippia_example.csv, -n, -o, /opt/workspace/automation/target/jmeter/reports/lippia_example, -t, /opt/workspace/automation/target/jmeter/testFiles/lippia_example.jmx, -L, DEBUG]
lippia_1  | [INFO] WARNING: An illegal reflective access operation has occurred
lippia_1  | [INFO] WARNING: Illegal reflective access by com.thoughtworks.xstream.core.util.Fields (file:/opt/workspace/automation/target/jmeter/lib/xstream-1.4.11.jar) to field java.util.TreeMap.comparator
lippia_1  | [INFO] WARNING: Please consider reporting this to the maintainers of com.thoughtworks.xstream.core.util.Fields
lippia_1  | [INFO] WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
lippia_1  | [INFO] WARNING: All illegal access operations will be denied in a future release
lippia_1  | [INFO] Creating summariser <summary>
lippia_1  | [INFO] Created the tree successfully using /opt/workspace/automation/target/jmeter/testFiles/lippia_example.jmx
lippia_1  | [INFO] Starting the test @ Tue Sep 01 14:50:42 UTC 2020 (1598971842645)
lippia_1  | [INFO] Waiting for possible Shutdown/StopTestNow/HeapDump/ThreadDump message on port 4445
lippia_1  | [INFO] Warning: Nashorn engine is planned to be removed from a future JDK release
lippia_1  | [INFO] summary =      1 in 00:00:01 =    0.8/s Avg:   998 Min:   998 Max:   998 Err:     0 (0.00%)
lippia_1  | [INFO] Tidying up ...    @ Tue Sep 01 14:50:44 UTC 2020 (1598971844848)
lippia_1  | [INFO] ... end of run
lippia_1  | [INFO] Completed Test: /opt/workspace/automation/target/jmeter/testFiles/lippia_example.jmx
lippia_1  | [INFO] 
lippia_1  | [INFO] --- maven-cucumber-reporting:3.8.0:generate (execution) @ lippia-performance-example-project ---
lippia_1  | [WARNING] /opt/workspace/automation/target/cucumber-reports does not exist.
lippia_1  | [INFO] 
lippia_1  | [INFO] --- jmeter-maven-plugin:2.8.6:results (jmeter-check-results) @ lippia-performance-example-project ---
lippia_1  | [INFO] Will scan results using format:{"resultFilesLocations":["/opt/workspace/automation/target/jmeter/results/20200901-lippia_example.csv"],"resultsOutputIsCSVFormat":true,"generateReports":true}
lippia_1  | [INFO] Parsing results file '/opt/workspace/automation/target/jmeter/results/20200901-lippia_example.csv' in format 'CSV'
lippia_1  | [INFO] Scanned file '/opt/workspace/automation/target/jmeter/results/20200901-lippia_example.csv', number of results in failure:'0'
lippia_1  | [INFO] Scanned file '/opt/workspace/automation/target/jmeter/results/20200901-lippia_example.csv', number of results in success:'1'
lippia_1  | [INFO]  
lippia_1  | [INFO] Performance Test Results
lippia_1  | [INFO]  
lippia_1  | [INFO] Result (.jtl) files scanned: 1
lippia_1  | [INFO] Successful requests:         1
lippia_1  | [INFO] Failed requests:             0
lippia_1  | [INFO] Failures:                    0.0% (0.0% accepted)
lippia_1  | [INFO] ------------------------------------------------------------------------
lippia_1  | [INFO] BUILD SUCCESS
lippia_1  | [INFO] ------------------------------------------------------------------------
lippia_1  | [INFO] Total time:  21.121 s
lippia_1  | [INFO] Finished at: 2020-09-01T14:50:47Z
lippia_1  | [INFO] ------------------------------------------------------------------------
lippia_1  | [INFO] Shutdown detected, destroying JMeter process...
lippia-performance-sample-project_lippia_1 exited with code 0



```
  
> This strategy consumes the bonigarcia solution to get the correct Driver to interact with your browser.
>In this case, Maven will be excute tests phase by using your local browser directly.

***
