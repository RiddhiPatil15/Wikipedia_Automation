pipeline {
    agent any

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/RiddhiPatil15/Wikipedia_Automation.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Generate Feature File') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass="utils.FeatureGeneratorRunner"'
            }
        }

        stage('Run Tests Again') {
            steps {
                bat 'mvn test'
            }
        }

        stage('JMeter Test') {
            steps {
                bat '''
                if exist report rmdir /s /q report
                if exist results.jtl del results.jtl
                
                jmeter -n -t jmeter/wikipedia_load_test.jmx -l results.jtl -e -o report
                '''
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false,
                       results: [[path: 'target/allure-results']]
            }
        }

        stage('Publish JMeter Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'report',
                    reportFiles: 'index.html',
                    reportName: 'JMeter Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.txt', fingerprint: true
        }
        success {
            echo 'Build Successful'
        }
        failure {
            echo 'Build Failed'
        }
    }
}
