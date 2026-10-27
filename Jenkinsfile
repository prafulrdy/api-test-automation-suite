pipeline {
  agent any
  stages {
    stage('REST Assured') { steps { sh 'mvn -B clean test' } }
    stage('Newman') { steps { sh 'npm ci || npm install'; sh 'npm run newman' } }
  }
  post {
    always {
      junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml,newman/*.xml'
      archiveArtifacts allowEmptyArchive: true, artifacts: 'target/surefire-reports/**,newman/**'
    }
  }
}
