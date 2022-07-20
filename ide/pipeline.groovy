pipeline {
    agent any
    
    stages {
        stage('pullRepo') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'test1', url: 'https://github.com/chilager/jgsu-spring-petclinic.git'
                
			}
		}
        stage('test') { 
            steps {
                sh './mvnw clean test'
            }
        }
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                sh './mvnw clean package'
               
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
    }
