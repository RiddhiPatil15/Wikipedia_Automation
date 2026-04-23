pipeline {
    agent any

    tools {
        maven 'Maven_Latest'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/RiddhiPatil15/Wikipedia_Automation.git'
            }
        }

        stage('Clean Project') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Generate Feature File') {
            steps {
                bat 'mvn test-compile exec:java -Dexec.mainClass="utils.FeatureGeneratorRunner" -Dexec.classpathScope=test'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('JMeter Test') {
            steps {
                echo 'Running JMeter Tests (if configured)'
            }
        }

        stage('Allure Report') {
            steps {
                echo 'Generating Allure Report'
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Publishing reports'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.html', allowEmptyArchive: true
        }
        success {
            echo '✅ Build SUCCESS'
        }
        failure {
            echo '❌ Build FAILED'
        }
    }
}
