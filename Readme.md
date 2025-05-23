# A simple server written with pure Java.
The goal of this project is to make a web server with as many features as possible with just Java. No libraries needed. This is just for learning purposes.
#
How to run it 🤔

First, make sure you have Docker installed.

###
Build the Docker image 🔨
```
docker build -t java-server .
```

###
Run the container ⚙️
```
docker run -p 8080:8080 java-server
```
####
And go to http://localhost:8080 on your browser
