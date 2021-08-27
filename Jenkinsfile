def projectName = 'portfolio-manager'
def dockerImageNameBack = 'portfolio-manager-back'
def dockerImageNameFront = 'portfolio-manager-front'
def version = "0.0.${currentBuild.number}"
def dockerImageTagBackend = "${dockerImageNameBack}:${version}"
def dockerImageTagFrontend = "${dockerImageNameFront}:${version}"

pipeline {
  agent any

  stages {
     stage('Build docker image for backend') {
          // this stage also builds and tests the Java project using Maven
          steps {
            sh "docker build -t ${dockerImageTagBackend} ."
          }
      }
    stage('Build docker image for frontend') {
          steps {
            sh "docker build -t ${dockerImageTagFrontend} -f js/Dockerfile js/."
          }
      }
    stage('Deploy Container To Openshift') {
      environment {
           OPENSHIFT_CREDS = credentials('openshiftCreds')
           //MYSQL_CREDS = credentials('MySQLCreds')
          }
      steps {
        sh "oc login --username=${OPENSHIFT_CREDS_USR} --password=${OPENSHIFT_CREDS_PSW}"
        sh "oc project ${projectName} || oc new-project ${projectName}"
        sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
        sh "oc new-app ${dockerImageTagBackend} -l version=${version}"
        sh "oc new-app ${dockerImageTagFrontend} -l version=${version}"
        sh "oc expose svc/${dockerImageNameBack}"
        sh "oc expose svc/${dockerImageNameFront}"
      }
    }
  }
}
