pipeline {
    agent { docker 'sbs-ubuntu:16.04' }
    stages {
         stage('test'){
             steps {
                  sh "ls -la"
                  sh "sbt \"project daas-appointment\" clean test"
                  sh "sbt \"project command-controller\" clean test"
             }
         }

         stage('build image'){
             steps {
               // set the version of the build artifact to the Jenkins BUILD_NUMBER so you can
               // map artifacts to Jenkins builds
                  sh "sbt \"project daas-appointment\" docker:stage"
                  sh "sbt \"project command-controller\" docker:stage"
             }
         }

         stage('push image'){
             steps {
                  sh "sbt \"project daas-appointment\" docker:publish"
                  sh "sbt \"project command-controller\" docker:publish"
             }
         }

         stage('deploy'){
             steps {
                 script {
                     userInput = false
                     didTimeout = false
                     echo "You are able to deploy this, wanna deploy it?"
                     try {
                         timeout(time: 30, unit: 'SECONDS') { // change to a convenient timeout for you
                             userInput = input(id: 'Proceed1', message: 'Wanna deploy this microservice?', parameters: [[$class: 'BooleanParameterDefinition', defaultValue: false, description: '', name: 'Please confirm you agree with this']])
                         }
                     } catch(err) { // timeout reached or input false
                         echo "As request has not been accepted this changes won't be deployed"
                     }

                     if (userInput == true) {
                          withCredentials([string(credentialsId: 'admin', variable: 'PW1')]) {
                              echo "Reloading marathon executes here...but not yet"
                          }
                     }
                 }
             }
         }
    }
}
