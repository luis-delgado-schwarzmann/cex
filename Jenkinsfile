pipeline {
    agent {
        docker {
            image 'ubuntu:16.04'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages {
         stage('pre-reqs'){
             steps {
                  //prepare our slave container
                  sh "apt-get -qq update && apt-get -qq -y install \
                      apt-transport-https \
                      ca-certificates \
                      curl \
                      software-properties-common \
                      openjdk-8-jre \
                      openjdk-8-jdk \
                      bc"
                  sh "curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -"
                  sh "add-apt-repository \
                      \"deb [arch=amd64] https://download.docker.com/linux/ubuntu \
                      $(lsb_release -cs) \
                      stable\""
                  sh "echo \"deb https://dl.bintray.com/sbt/debian /\" | tee -a /etc/apt/sources.list.d/sbt.list && \
                      apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823 && \
                      apt-get -qq update"
                  sh "apt-get -qq -y install docker-ce sbt=0.13.8"
             }
         }

         stage('test'){
             steps {
                  sh "Current Git Branch: `git branch | grep -e \"^*\" | cut -d \" \" -f 2`"
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
