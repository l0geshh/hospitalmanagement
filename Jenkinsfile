pipeline {
    agent any

    environment {
        MVN = "C:\\tools\\maven\\bin\\mvn.cmd"
    }

    stages {
        stage("Clone Repository") {
            steps {
                git branch: "main",
                    url: "https://github.com/L0geshh/hospitalmanagement.git" // CHANGE: lowercase e.g. electricitybilling
            }
        }

        stage("Build with Maven") {
            steps { bat "%MVN% clean package" }
        }

        stage("Run JUnit Tests") {
            steps { bat "%MVN% test" }
        }

        stage("Build Docker Image") {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "dockerhub-credentials",
                    passwordVariable: "DOCKER_PASS",
                    usernameVariable: "DOCKER_USER")]) {
                    bat "docker build -t %DOCKER_USER%/hospitalmanagement:latest ." // CHANGE: lowercase e.g. electricitybilling
                }
            }
        }

        stage("Push to DockerHub") {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "dockerhub-credentials",
                    passwordVariable: "DOCKER_PASS",
                    usernameVariable: "DOCKER_USER")]) {
                    bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                    bat "docker push %DOCKER_USER%/hospitalmanagement:latest" // CHANGE: lowercase e.g. electricitybilling
                }
            }
        }

        stage("Deploy to Kubernetes") {
            steps {
                withCredentials([file(credentialsId: 'kubercongif', variable: 'KUBECONFIG')]) {
                    bat "kubectl apply -f deployment.yaml"
                    bat "kubectl rollout restart deployment/hospitalmanagement" // CHANGE: lowercase e.g. electricitybilling
                }
            }
        }
    }
}
