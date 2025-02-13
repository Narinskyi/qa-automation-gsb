# qa-automation-gsb

```
Task #1 (Test Case Design)
1. Navigate to: https://s.gsb.co.zm/
2. Cover Login form with Test Cases (specify test design technics were used)
Task #2 (UI)
1. Navigate to: https://s.gsb.co.zm/
2. Choose "Upcoming" tab
3. Search for all events, which have odds value between 1.5 - 3.34
4. Print filtered events, add validation
Task #3 (API)
1. Implement any POST API call from given website: https://s.gsb.co.zm/ (auth is not needed)
2. Use devTools -> Network to get data for it
3. Validate JSON schema for this API call
4. Use serialization/ deserialization for request/ response
__________________________________________________________________________________________________________________________
General requirements:
- Create public repository (e.g. Github)
- Add logging/ reporting
- Use gradle task to run tests via command line
- Implement parallel run (extra task)
Tech stack:
- Java 17
- Gradle 8.6
- TestNG
- Selenium WebDriver (Selenide)
- REST Assured
```

## How to run the tests

### Configuration
- **Config file:**  
  All the project settings (like `baseUrl`, `browser`, `timeout`, etc.) are located in: 
src/main/resources/config.properties

- **Logging:**  
  Log settings (console, file) are set in: src/main/resources/log4j2.xml

**TestNG Suite:**  
The test suite run config file is located here: src/test/resources/testng.xml

- The suite can be run in parallel using the `parallel` attribute and `thread-count`.

### Running tests with Gradle
Execute from the project root:

- **Run all tests:**
```bash
./gradlew clean test
```

- **Generate Allure report:**
```bash
./gradlew allureServe
```

