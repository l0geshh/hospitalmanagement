# =====================================================================
#   COMPLETE GUIDE - DO ANY JAVA CI/CD PROJECT FROM SCRATCH
#   Updated for: Logesh | L0geshh
# =====================================================================

## YOUR CREDENTIALS

| Service   | Username              | Password / Token                |
|-----------|-----------------------|---------------------------------|
| GitHub    | L0geshh | Githuber@789                     |
| DockerHub | logeshhh4zz           | Docker123@369                 |
| Jenkins   | admin                 | (your Jenkins admin password)   |

Jenkins password hint: double-click GET-JENKINS-PASSWORD.bat on Desktop

---

## GOLDEN RULE FOR NAMING YOUR PROJECT

Use ALL LOWERCASE for your project name everywhere EXCEPT Java class names.

EXAMPLE - Project = "Shopping Cart System":

  Java/JUnit class names  -> ShoppingCart / ShoppingCartTest  (PascalCase)
  EVERYTHING ELSE         -> shoppingcart                     (all lowercase)

YOUR REPLACEMENTS IN ALL FILES:
  YOUR-PROJECT-NAME    ->  shoppingcart     (Jenkinsfile, Dockerfile, deployment.yaml, pom.xml)
  YourProgram.java     ->  ShoppingCart.java          (only Java file name)
  YourProgramTest.java ->  ShoppingCartTest.java      (only JUnit file name)

---

## WHERE EXACTLY TO CHANGE (by file)

### pom.xml  (change 2 places)
  <artifactId>shoppingcart</artifactId>          <- LOWERCASE
  <mainClass>com.demo.ShoppingCart</mainClass>   <- PascalCase (Java class)

### Dockerfile  (change 1 place)
  COPY target/shoppingcart-1.0.jar app.jar       <- LOWERCASE (must match artifactId)

### deployment.yaml  (change ALL 6 occurrences -> lowercase)
  name: shoppingcart
  app: shoppingcart
  image: logeshhh4zz/shoppingcart:latest           <- your Docker Hub username is logeshhh4zz

### Jenkinsfile  (change 3 places marked with // CHANGE)
  url: ".../shoppingcart.git"                    <- GitHub repo name
  docker build -t .../shoppingcart:latest        <- project name
  kubectl rollout restart deployment/shoppingcart <- project name

### Java files  (PascalCase - Java convention)
  ShoppingCart.java       <- PascalCase
  ShoppingCartTest.java   <- PascalCase

---

## STEP 0 - START JENKINS & DOCKER (Every time)

  1. Double-click START-JENKINS.bat on Desktop
  2. Open Docker Desktop from Start Menu -> wait for "Engine running" bottom left
  3. Open browser: http://localhost:8080
     Login: admin / (your password)

---

## STEP 1 - COPY TEMPLATE

  Right-click project-template-main folder -> Copy -> Paste -> Rename to your project (lowercase)
  NEVER edit the original template!

---

## STEP 2 - RENAME & REPLACE

  In VS Code, press Ctrl+H to Find and Replace in each file:
  Replace YOUR-PROJECT-NAME with shoppingcart (all lowercase)
  Replace YourProgram with ShoppingCart (only in Java files)

  Rename the Java files:
    YourProgram.java     -> ShoppingCart.java
    YourProgramTest.java -> ShoppingCartTest.java

---

## STEP 3 - WRITE JAVA CODE & JUNIT TESTS

  Edit ShoppingCart.java       <- your main logic
  Edit ShoppingCartTest.java   <- your JUnit test cases

---

## STEP 4 - RUN LOCALLY (test before Jenkins)

  Open PowerShell in your project folder:

  mvn clean test       <- Run JUnit tests
   mvn clean package    <- Build JAR

  java -jar target\hospitalmanagement-1.0.jar       <- Run app

---

## STEP 5 - PUSH TO GITHUB

  1. github.com -> New repo -> name: shoppingcart -> Public -> No README -> Create

  2. Open PowerShell inside your project folder:

     git init
     git add .
     git commit -m "Initial commit"
     git branch -M main
     git remote add origin https://github.com/L0geshh/hospitalmanagement.git
     git push -u origin main

     (when asked for username: L0geshh)
     (when asked for password: Githuber@789)

---

## STEP 6 - JENKINS PIPELINE

  1. http://localhost:8080 -> New Item
  2. Name: shoppingcart-CICD -> Select Pipeline -> OK
  3. Scroll to Pipeline section -> Definition: Pipeline script
  4. Copy and paste the full Jenkinsfile contents into the Script box
     (remember to change YOUR-PROJECT-NAME to shoppingcart in the script)
  5. Save -> Build Now

  PIPELINE STAGES (all should go GREEN):
  [Clone] -> [Build with Maven] -> [Run JUnit Tests] -> [Build Docker Image] -> [Push to DockerHub] -> [Deploy to Kubernetes]

---

## STEP 7 - VERIFY

  kubectl get pods    <- should show 2 Running pods
  kubectl get svc     <- should show NodePort service

---

## SHOW FACULTY (4 outputs)

  1. Java:       java -jar target\shoppingcart-1.0.jar
  2. JUnit:      C:\tools\maven\bin\mvn.cmd test  (show Tests run: X, Failures: 0)
  3. Jenkins:    http://localhost:8080 -> show green Stage View (6 green boxes)
  4. Docker:     hub.docker.com/u/logeshhh4zz

---

## COMMON ERRORS & FIXES

  "mvn not found"
  -> Use full path: C:\tools\maven\bin\mvn.cmd
  -> OR open a new PowerShell window

  "repository name must be lowercase"
  -> Image name in Jenkinsfile/deployment.yaml not lowercase. Fix it!

  "failed to connect to docker API"
  -> Open Docker Desktop from Start Menu and wait for "Engine running"

  Jenkins not opening
  -> Double-click START-JENKINS.bat on Desktop

  Docker push takes long (first time only!)
  -> First push uploads layers. After that, all builds take ~30 seconds total.

  "unauthorized: incorrect username or password" in Docker push
  -> Go to Jenkins -> Credentials -> edit dockerhub-credentials
  -> Username: logeshhh4zz | Password: Docker123@369

  NodePort conflict (2nd, 3rd project)
  -> Change nodePort in deployment.yaml:
     1st project -> 30008
     2nd project -> 30009
     3rd project -> 30010

---

## JENKINS CREDENTIALS ALREADY CONFIGURED

  ID: dockerhub-credentials     -> logeshhh4zz / Docker123@369    (Docker Hub)
  ID: github-credentials    -> L0geshh          (GitHub)
  ID: kubercongif     -> kubeconfig file                (Kubernetes)

  These are already set in Jenkins. DO NOT recreate them.
  Just make sure credentialsId in Jenkinsfile matches exactly:
  -> "dockerhub-credentials"  (not dockerhub-creds, not docker_cred)
  -> "kubercongif"  (not kubeconfig, not kube-config)
