# Broken Windows Investigation: Annotated dataset

This repository contains annotated data related to the *Broken Windows Investigation* thesis conducted by [Hampus Broman](https://github.com/HBroman) and [William Levén](https://github.com/williamleven)

Read the *Broken windows Investigation* thesis for more information about the procedure used to collect and annotate this data.
 
## Repository content

* `README.md` contains some general information about this data repository.
* `data.csv` is a generated table of all extracted data for the *Broken window Investigation*. Note that this repository also contains the raw data as well as any intermediary steps so that you can extract any data you want from the complete data set.
* `.manual_grouping.json` contains information about how user input has ben categorized into larger groups.
* `.manual_rules.json` contains information about which SonarQube rules were triggered by the submissions and if the rule should be ignored or not.
* Each directory represents the submissions of one participant.
    * `.session.json` contains information about the participant and their participation.
    * `.manual_inspection.json` contains our manual input and interpretation of the information about the participant and their participation.
    * `booking` & `tickets` contains information about the task specific submission made by each participant. Some files may not exist in those directories if the participant didn't complete the task.
        * `.submission.json` contains information about the submission as reported by the research tool. 
        * `.manual_inspection.json` contains our manual input and interpretation about the scenario submission.
        * `.diff` contains a compact list of changes made to the system by the participant.
        * `.compilation[.error]` contains the log output from the compilation of the system. The suffix indicates if there were any errors.
        * `.execution[.error]` contains the log output from the execution of the system. The suffix indicates if there were any errors.
        * `.sonarscanner[.error]` contains the log output from running the `sonar-scanner` utility on the system. The suffix indicates if there were any errors.
        * `.issues.json` issues encountered while scanning the system with SonarQube.
        * `.submission` contains the complete system submitted by the participant.
    
    
## Notes
1. Some personal comments submitted by participants has been removed from the dataset (2 instances).
2. Annotation is not guaranteed to be complete in cases where the submission wasn't approved.

If you have any questions or feel that anything is amiss, please [open an issue](https://github.com/BrokenWindowsInvestigation/Data/issues).
