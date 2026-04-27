pipeline {
    agent any

    stages {

        stage('Build & Test') {
            steps {
                bat 'mvn clean test -U -Dmaven.repo.local=.m2 -Dgrid.url=http://localhost:4444'
            }
        }

        stage('JMeter Test') {
            steps {
                bat '''
                if exist report rmdir /s /q report
                if exist results.jtl del results.jtl
                jmeter -n -t C:\\Users\\Riddh\\OneDrive\\Desktop\\CapG\\AIVS-JAVA\\MCP\\WikipediaBasedProject\\jmeter\\wikipedia_load_test.jmx -l results.jtl -e -o report
                '''
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'C:/Users/Riddh/OneDrive/Desktop/CapG/AIVS-JAVA/MCP/WikipediaBasedProject/allure-results']]
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
}
