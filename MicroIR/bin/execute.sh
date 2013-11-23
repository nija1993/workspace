javac Main.java
java Main < $1.miniIR > testing/$1.microIR
cd testing
java -jar pgi.jar < $1.microIR
cd ..