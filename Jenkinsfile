pipeline{
	agents any
	stages{
	stage('Build'){
	steps{
	echo "building the code..."
	bat "mvn clean"
	}
	}
	
	stage('Build'){
	steps{
	echo "building the code..."
	bat "mvn clean"
	}
	}
	
	stage('Test'){
	steps{
	echo "testing the code..."
	bat "mvn test"
	}
	}
	
	stage('Complile'){
	steps{
	echo "compiling the code..."
	bat "mvn compile"
	}
	}
	
	stage('Deploy'){
	steps{
	echo "deploying the project..."
	}
	}
	
	}

}