pipeline {
    agent any

    tools {
        maven 'Maven_Latest'
        jdk 'jdk17'
    }

    environment {
        MAVEN_OPTS = '-Xms256m -Xmx1024m'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/RiddhiPatil15/Wikipedia_Automation.git'
            }
        }

        stage('Init Local Maven Repo') {
            steps {
                bat 'mkdir %WORKSPACE%\\.m2'
            }
        }

        stage('Clean Project') {
            steps {
                bat 'mvn clean -Dmaven.repo.local=%WORKSPACE%\\.m2'
            }
        }

        stage('Generate Feature Files') {
            steps {
                bat 'mvn test-compile exec:java -Dmaven.repo.local=%WORKSPACE%\\.m2 -Dexec.mainClass="utils.FeatureGeneratorRunner" -Dexec.classpathScope=test'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test -Dmaven.repo.local=%WORKSPACE%\\.m2'
            }
        }

        stage('Allure Report') {
            steps {
                echo 'Generating Allure Report'
                bat 'mvn allure:report -Dmaven.repo.local=%WORKSPACE%\\.m2'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.html, **/target/allure-results/**', allowEmptyArchive: true
        }

        success {
            echo '✅ BUILD SUCCESS'
        }

        failure {
            echo '❌ BUILD FAILED'
        }
    }
}
