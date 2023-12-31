# Brilloconnetz Test

Solutions to the questions 1 & 2 asked on the Brilloconnetz test. 

## Getting Started <a name = "getting_started"></a>
### Prerequisites
To successfully run this project, you'll need the following installed on your machine:
- **JDK v17.** (Other versions may serve)
- **Apache Maven**

## Usage <a name="usage"></a>
To use this project:
1. Clone the repository using the following command:
   ```
   git clone https://github.com/chijioke-ibekwe/brilloconnetz-test.git
   ```

2. Run the following command to execute the tests on the modules:
   ```
   mvn test
   ```
3. In the root of the `question-one` module, execute the following command to run question one:
   ```
   mvn compile exec:java
   ``` 
   Follow the prompts on the console to peruse the `question-one` solution.
4. In the root of the `question-two` module, execute the same command as above to run question two.  
   Question two can run in two ways: sequentially and concurrently. You'll be prompted to select what way you wish to
   run the solution, once the application starts.  

   To answer the theory question attached to question 2, on the test:
   ```
   Compare and contrast both versions of the code and state your observations.
   ```
   My observation is that the concurrent mode of execution has a more efficient use of time as the 30 seconds slots 
   all run in parallel. However the output from this mode is not ordered since the outcome of all slots are being 
   written to the console simultaneously.
   