node('docker'){

   stage('pre-reqs'){
      //prepare our slave container
      sh "echo \"deb https://dl.bintray.com/sbt/debian /\" | sudo tee -a /etc/apt/sources.list.d/sbt.list && \
          apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823 && \
          apt-get update && \
          apt-get -y install sbt"
   }

   stage('checkout'){
      // Get some code from a GitHub repository
      git url: 'https://github.com/luis-delgado-schwarzmann/cex'
      sh 'git clean -fdx; sleep 4;'
   }

   stage('package'){
      sh "sbt \"project daas_appointment\" clean compile"
      sh "sbt \"project command_controller\" clean compile"
   }

   stage('build image'){
   // set the version of the build artifact to the Jenkins BUILD_NUMBER so you can
   // map artifacts to Jenkins builds
      sh "sbt \"project daas_appointment\" docker:stage"
      sh "sbt \"project command_controller\" docker:stage"
   }

   stage('push image'){
      sh "sbt \"project daas_appointment\" docker:publish"
      sh "sbt \"project command_controller\" docker:publish"
   }

   stage('test'){
      sh "sbt \"project daas_appointment\" clean test"
      sh "sbt \"project command_controller\" clean test"
   }

   stage('deploy'){
      def userInput = false
      def didTimeout = false
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