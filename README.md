How to run:

1. Download and extract file (If you extract to desktop you can drag the folder over Intelij icon to open it)
2. To run the terminal test run the "Register.java" file.
3. To run the GUI test run the "MakingChange.java" file.


(Lab 4 document starts here)

Design Pattern:

  Selected Patterns:
   1. Observer Pattern
    Advantages:
   - Decouples UI components (RegisterPanel and PursePanel)
   - Enables automatic updates when purse state changes. 
   - Makes adding new display components easier.
     
  2. Template Pattern
    Advantages:
   - Standardizes the change-making algorithm structure
   - Makes it easier to add new calculation methods
   - Maintains consistency in how change is calculated

  Observer Pattern Implementation:
  1. New Interface
   - PurseObserver: Interface for objects that observe Purse changes.
  2. Modified Classes
   - Purse.java: Added observer management
   - PursePanel.java: Implements PurseObserver
   - RegisterPanel.java: Modified to work with observer pattern

  Template Pattern Implementation:
  1. New Abstract Class:
   - changeCalculator: Defines template for change-making algorithm
  2. New Concrete Classes:
   - StandardChangeCalculator: Implements original algorithm
   - MinimumCoinsCalculator: Alternative implementation
  3. Modified Classes
   - Register.java: Updated to use ChangeCalculator


 Integration with Existing Code:
 - Both patterns work together seamlessly
 - Observer Pattern handles UI update
 - Template Method Pattern manages change calculation
 - Original functionality maintained while improving desig
 - All UI components and core classes remain compatible

(Lab 4 document ends here)

  


[sorry for blurry images for bills. Manual resizing messed it up :( ]
