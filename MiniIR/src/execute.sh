javac Main.java
java Main < $1.java > miniirtest/$1.miniIR
cd miniirtest
javac Main.java
java Main < $1.miniIR
cd ..