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

        stage('delete kubernetes objects'){
            steps {
                script{
                    try{
                        bat '''
                            minikube -p minikube docker-env --shell cmd > temp.cmd
                            call temp.cmd
                            del temp.cmd

                            echo "=========deleting objects============"

                            kubectl delete -f istio.yml

                            kubectl delete -f kubernetes.yml
                            kubectl delete -f ingress.yml
                            kubecrl delete -f secrets.yml

                            kubectl delete -f ./agent/agent-svc.yml
                            kubectl delete -f ./agent/agent-cm.yml
                            kubectl delete -f ./agent/agent-dc.yml

                        '''
                    }
                    catch(err) {
                        echo err.getMessage()
                    }
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

                        echo "=========creating objects============"

                        kubectl apply -f kubernetes.yml
                        kubectl apply -f ingress.yml
                        kubectl apply -f secrets.yml

                        kubectl apply -f ./agent/agent-cm.yml
                        kubectl apply -f ./agent/agent-dc.yml
                        kubectl apply -f ./agent/agent-svc.yml

                        kubectl apply -f istio.yml
                    '''
                }
            }
        }
    }
}