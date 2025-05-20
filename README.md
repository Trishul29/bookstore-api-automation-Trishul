📚 Bookstore API Automation with CI/CD Integration

Overview

This project automates end-to-end API testing of a Bookstore application using Cucumber BDD and TestNG in Java with Rest Assured.

💻 Tech Stack Overview

Component	Description
🧠 IDE	IntelliJ IDEA
☕ Language	Java 11+
🔄 Framework	Rest Assured + Cucumber BDD – For behavior-driven API automation
🛠 Build Tool	Maven – Dependency management and CI/CD integration
✅ Test Execution	TestNG – For parallel execution, retries, and robust test orchestration (Can be Implemented for future Sscope with Cucumber Integration)
📊 Reporting	Allure Report – For detailed visual test reports with failure categorization and trends

🧪 Why This Stack?
TestNG over JUnit:
Better suited for non-Spring Boot projects, offers flexible test orchestration, retries, and parallel runs.

Rest Assured + Cucumber:
Provides readable BDD-style tests, making scenarios easy to understand and maintain.
Seperate Test Layer, Client Layer, and Service Layer. This separation helps keep  code clean, maintainable, and hides implementation details.

Allure Reporting:
Generates rich reports with detailed test steps, failure categories, and history.

Maven:
Manages dependencies and facilitates easy CI/CD pipeline integration.

🚀 Features & Endpoints Covered
This automation covers the following REST endpoints:

HTTP Method	Endpoint	Description
POST	/signup	Register a new user
POST	/login	Authenticate user & get token
POST	/books	Create a new book
PUT	/books/{id}	Update book by ID
GET	/books/{id}	Fetch book details by ID
GET	/books	Fetch all books
DELETE	/books/{id}	Delete a book by ID

📁 Project Structure
bash
Copy
Edit
src/
 ├── main/java/            # POJO classes Constants,utilities, property files
 ├── test/java/            #  API clients, and service layer,Step definitions, runners, and test logic
 └── test/resources/
      ├── features/        # Cucumber feature files

      
             
🔧 How to Set Up & Run the Project
Prerequisites
Java 11+

Maven 3.6+

IntelliJ IDEA or any Java IDE

Running Tests
Clone the repo:

bash
Copy
Edit
git clone <your-repo-url>
cd <project-folder>
Run tests via Maven:

bash
Copy
Edit
mvn clean test
Generate and open Allure reports:


📊 Allure Reporting
Allure is integrated into this framework to generate visually rich test execution reports.

🛠 Prerequisites
Java 11+

Maven

Allure CLI (Command Line Interface)

🔧 Install Allure CLI
On macOS (using Homebrew):
bash
Copy
Edit
brew install allure
On Windows (using Scoop):
bash
Copy
Edit
scoop install allure
Or download manually from Allure Releases

🚀 How to Generate and View Reports
Run your test suite
This will generate the result files required for the report.

bash
Copy
Edit
mvn clean test
Generate and serve the report

bash
Copy
Edit
allure serve target/allure-results
This command will:

Generate the report

Automatically open it in your browser

🗂️ Generate Static Report (Optional)
To generate a static version of the report (can be shared or committed):

bash
Copy
Edit
allure generate target/allure-results --clean -o target/allure-report
Then open:

text
Copy
Edit
target/allure-report/index.html
🧪 Allure Integration in Cucumber
Ensure this plugin is added in @CucumberOptions of your runner:

java
Copy
Edit
plugin = {
"pretty",
"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
"html:target/cucumber-reports.html"
}

✅ Benefits of Allure
Interactive and rich test UI

Categorization of test failures

Tracks test history and trends

Easily integrates with CI tools (e.g., Jenkins, GitHub Actions)




🎯 CI/CD Integration
Prerequisites
Jenkins installed with required plugins: Git, Maven, Pipeline, Allure



Ngrok (for local development webhook exposure)



Sample Jenkins Pipeline for QA Automation
groovy
Copy
Edit
pipeline {
    agent any
    tools { 
        maven 'Maven 3.6.3'
        allure 'Allure' 
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Trishul29/bookstore-api-automation-Trsihul.git', branch: 'main'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Generate Allure Report') {
            steps {
                sh 'mvn allure:report'
            }
        }
    }
    post {
        always {
            allure([ includeProperties: false, jdk: '', results: [[path: 'target/allure-results']] ])
        }
    }
}


Development Workflow with Jenkins & Ngrok
Set up Jenkins jobs for your Dev and QA repos.

Use Ngrok to expose local servers for webhook triggers:

bash
Copy
Edit
ngrok http 8080
Configure GitHub webhook with the Ngrok URL for Jenkins to trigger builds on commits.

Commit changes → Dev Jenkins job builds → On success, triggers QA automation job → Allure reports generated.

🔑 Key Benefits
Automated tests ensure API stability on every commit.

BDD-style tests improve collaboration between devs, QAs, and stakeholders.

Detailed reports help quickly identify issues.

CI/CD pipeline ensures continuous testing and faster feedback.

