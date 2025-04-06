def s_branch = env.BRANCH_NAME as String
def registry = "containerregistry.spot-me-app.com/spotme/" as String
def localRegistry = "http://192.168.1.227:8082/" as String
def localRegistryUrl = "http://192.168.1.227:8082" as String
def registryUrl = "https://containerregistry.spot-me-app.com" as String
s_branch = s_branch.replaceAll("/","_")

pipeline{
    agent any
    stages{
        stage("Clean Up"){
            steps{
                deleteDir()
            }
        }
        stage("Clone repo"){
            steps{
                checkout scm
            }
        }

        stage("Build"){
            steps{
                sh ''' mvn clean install -ntp -Dmaven.test.skip '''
            }
        }
        stage("Test"){
            steps{
               dir("./"){
                sh ''' echo "Fake Test" '''
               }
              }
            }
        stage("Store Artifacts"){
            steps{
               archiveArtifacts artifacts: 'spotme-rest/target/*.jar', followSymlinks: false
            }
        }
        stage("Image Upload"){
            steps(){
                script{
                        try{
                            docker.withRegistry(registryUrl,'spotme-containerregistry') {
                                sh "docker system prune -a -f"
                                def smrest = docker.build("spotme/spotme-rest:${s_branch}","./")
                                //sh "docker push ${registry}spotme-rest:${s_branch}"


                                //"docker push ${registry}spotme-web:${s_branch}"

                                // or docker.build, etc.
                                smrest.push()

                            }
                        }catch(e){
                            echo 'Tunnel URL did not work for image push, trying to push via intranet'
                            docker.withRegistry(localRegistryUrl,'spotme-containerregistry') {
                                def smrest_l = docker.build("spotme/spotme-rest:${s_branch}","./")


                                // or docker.build, etc.
                                smrest_l.push()

                            }
                        }

                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                        def mvn = tool 'maven';
                        try{
                        withSonarQubeEnv() {
                            sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=spotme -Dsonar.projectName='spotme'"
                        }}catch (e){
                            println "Sonar Analysis could not operate"
                        }
            }
            }
        }
        stage('Revalidate K8s tokens') {
            steps {
                script {
                        try{
                            sh "kubectl rollout restart ds -n kube-system calico-node"
                            println "Restarted calico-node"
                        }catch (e){
                            println "Could not restart calico-node"
                        }
                }
            }
        }

    }
    post {
           always{
                cleanWs()
           }
    }