pipeline {
    agent any

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/RiddhiPatil15/Wikipedia_Automation.git'
            }
        }

        stage('Clean & Build') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Generate Feature File') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass="utils.FeatureGeneratorRunner"'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'target/allure-results']]
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
