pipeline {
    
    agent any 
    
    tools {
        maven "LOCAL_MAVEN"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "*********************CHECKOUT*********************"
                git branch: 'master', url: 'https://github.com/KevinLennoz/calculator.git'
            }
        }
        
        stage('Compile') {
            steps {
                echo "*********************COMPILE*********************"
                sh 'mvn compile'
            }
        }
        
        stage('Test') {
            steps {
                echo "*********************TEST*********************"
                sh 'mvn test'
            }
            
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo "*********************PACKAGE*********************"
                sh 'mvn package -Dmaven.test.skip=true'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            } 
        }
        
        stage('SSH transfer') {
            steps {
                echo "*********************SSH TRANSFER*********************"
                script {
                    sshPublisher(publishers: [
                        sshPublisherDesc(configName: 'docker-host', transfers:[
                            sshTransfer(
                              execCommand: '''
                                    echo "### Cleaning project ###";
                                    sudo docker stop demo  || true;
                                    sudo docker rm demo || true;
                                    sudo docker rmi demo || true;
                                '''
                            ),
                            sshTransfer(
                                sourceFiles:"target/*.jar",
                                removePrefix: "target",
                                remoteDirectory: "//home//vagrant",
                                execCommand: "ls /home/vagrant"
                            ),
                            sshTransfer(
                                sourceFiles:"Dockerfile",
                                removePrefix: "",
                                remoteDirectory: "//home//vagrant",
                                execCommand: '''
                                    cd /home/vagrant;
                                    sudo docker build -t demo .;
                                    sudo run -d --name demo -p 8080:8080 demo;
                                '''
                            )
                        ])
                    ])                
                }
            }
        }
    }
}