# QA Automation Task

## Task #1: Test Case Design
1. Navigate to: [GSB Website](https://s.gsb.co.zm/)
2. Cover the **Login form** with Test Cases (specify the test design techniques used).

## Task #2: UI Automation
1. Navigate to: [GSB Website](https://s.gsb.co.zm/)
2. Choose the **"Upcoming"** tab.
3. Search for all events that have **odds value between 1.5 - 3.34**.
4. Print the filtered events and add **validation**.

## Task #3: API Automation
1. Implement any **POST API call** from the given website: [GSB API](https://s.gsb.co.zm/) (authentication is **not needed**).
2. Use **DevTools → Network** to get request/response data.
3. **Validate JSON Schema** for this API call.
4. Use **serialization/deserialization** for request/response handling.

---

## General Requirements
✅ Create a **public repository** (e.g., GitHub).  
✅ Add **logging & reporting**.  
✅ Use a **Gradle task** to run tests via the command line.  
✅ Implement **parallel execution** (extra task).

## Tech Stack
- **Java 17**
- **Gradle 8.6**
- **TestNG**
- **Selenium WebDriver (Selenide)**
- **REST Assured**  
## Test Run Configurations


### **How to run tests**
To run tests locally using **Chrome** browser, set the following in your `config.properties` file:

```properties
browser=chrome
local=true
```

