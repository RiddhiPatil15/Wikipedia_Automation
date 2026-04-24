pipeline {
    agent any

    tools {
        maven 'Maven_Latest'
        jdk 'jdk17'
    }

    environment {
        MAVEN_OPTS = '-Xms256m -Xmx1024m'
        MAVEN_REPO = "${WORKSPACE}\\.m2"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/RiddhiPatil15/Wikipedia_Automation.git'
            }
        }

        // ✅ Create isolated Maven repo (no global dependency)
        stage('Init Local Maven Repo') {
            steps {
                bat 'if not exist "%MAVEN_REPO%" mkdir "%MAVEN_REPO%"'
            }
        }

        // ✅ Force Maven to use stable central repo
        stage('Setup Maven Settings') {
            steps {
                writeFile file: 'settings.xml', text: '''
<settings>
  <mirrors>
    <mirror>
      <id>central</id>
      <mirrorOf>*</mirrorOf>
      <url>https://repo.maven.apache.org/maven2</url>
    </mirror>
  </mirrors>
</settings>
'''
            }
        }

        // ✅ Clean with retry (handles network failures)
        stage('Clean Project') {
            steps {
                retry(2) {
                    bat '''
                    mvn clean ^
                    -s settings.xml ^
                    -Dmaven.repo.local="%MAVEN_REPO%" ^
                    -Dhttps.protocols=TLSv1.2
                    '''
                }
            }
        }

        // ✅ Generate feature files
        stage('Generate Feature Files') {
            steps {
                retry(2) {
                    bat '''
                    mvn test-compile exec:java ^
                    -s settings.xml ^
                    -Dmaven.repo.local="%MAVEN_REPO%" ^
                    -Dexec.mainClass="utils.FeatureGeneratorRunner" ^
                    -Dexec.classpathScope=test ^
                    -Dhttps.protocols=TLSv1.2
                    '''
                }
            }
        }

        // ✅ Run tests
        stage('Run Tests') {
            steps {
                retry(2) {
                    bat '''
                    mvn test ^
                    -s settings.xml ^
                    -Dmaven.repo.local="%MAVEN_REPO%" ^
                    -Dhttps.protocols=TLSv1.2
                    '''
                }
            }
        }

        // ✅ Allure report
        stage('Allure Report') {
            steps {
                echo 'Generating Allure Report'
                bat '''
                mvn allure:report ^
                -s settings.xml ^
                -Dmaven.repo.local="%MAVEN_REPO%"
                '''
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
