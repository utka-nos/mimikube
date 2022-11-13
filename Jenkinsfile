pipeline{
    agent{
        label 'windows'
    }

    stages{
        stage('package') {
            steps{
                script{
                    bat '''
                        mvn clean package
                    '''
                }
            }
        }

        stage('delete images') {
            steps {
                script{
                    try{
                        bat '''
                            minikube -p minikube docker-env --shell cmd > temp.cmd
                            call temp.cmd
                            del temp.cmd

                            docker rmi coordinator
                            docker rmi user
                        '''
                    }
                    catch(err) {
                        echo err.getMessage()
                    }
                }
            }
        }

        stage('create images'){
            steps{
                script{
                    bat '''
                        minikube -p minikube docker-env --shell cmd > temp.cmd
                        call temp.cmd
                        del temp.cmd

                        docker build -t coordinator ./coordinator-service
                        docker build -t user ./user-service
                    '''
                }
            }
        }

        stage('kube apply') {
            steps{
                script{
                    bat '''
                        minikube -p minikube docker-env --shell cmd > temp.cmd
                        call temp.cmd
                        del temp.cmd

                        kubectl apply -f kubernetes.yml
                    '''
                }
            }
        }
    }
}