pipeline {
    agent any

    tools {
        maven 'Maven'   // make sure Maven is configured in Jenkins
        jdk 'Java'      // make sure JDK is configured
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
                // Example:
                // bat 'jmeter -n -t testplan.jmx -l result.jtl'
            }
        }

        stage('Allure Report') {
            steps {
                echo 'Generating Allure Report'
                // Requires Allure plugin installed
                // allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
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
