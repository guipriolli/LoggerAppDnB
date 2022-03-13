# Logger Application - Dun & Bradstreet

Technical assessment for Dun & Bradstreet process.

## Running Application

Compile:
```
mvn clean install
```

Run Unit Tests:
```
mvn test
```

Run Program:
```
mvn exec:java -Dexec.mainClass=com.dnb.logger.LoggerAppDnB -Dexec.args="input output"
```

PS: the first argument is the input path and the second argument is the output path.