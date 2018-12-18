# Development

The Docker Compose driver is decoupled from the EPM and therefore has a different development environment. 

## EPM Development environment

Prerequisites:
* Java JDK 8 
* Gradle
* Intellij Idea - Optional

Finally clone the repository:

```bash 
git clone https://github.com/elastest/elastest-platform-manager
```
There are two ways to start it:

* Using the command line: Inside the project folder run: ``` ./gradlew build && java -jar build/libs/elastest-platform-manager-0.9.0.jar```
* Using Intellij Idea: Import the project as a gradle project and then run the application.

## The Docker Compose Adapter development environment

Prerequisites:
* Python 2.7
* Pycharm - optional

Clone the repository and install the needed tools:

```bash
git clone https://github.com/mpauls/epm-client-docker-compose
pip install docker-compose grpcio grpcio-tools
```
Then you can start it from the "run.py" file. 
