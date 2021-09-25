# CollectorAPI

## Java API for [liftOffProject](https://github.com/EyeTyrant/liftOffProject) hosted on Heroku. MySQL database hosted on Amazon RDS.
The app can be accessed at https://eyetyrant.github.io/collectorui/ with your Firefox browser,
(not yet compatible with Safari, Chrome, or Edge).

## OR

From Dockerhub:

With docker installed, type the following commands in your cli to interact with the app:

1. docker pull eyetyrant/collectorapi:v1.0
2. docker pull eyetyrant/collectorui:v1.0
3. docker run  --rm -d -p 8080:8080 eyetyrant/collectorapi:v1.0
4. docker run --rm -d -p 4200:80 eyetyrant/collectorui:v1.0
5. Navigate to localhost:4200 in your browser to interact.
6. Register.
7. Login.
8. Click on "Your Collections" to CRUD your collection.

To pull images from the github repositories instead of Dockerhub, add the prefix ghcr.io/ 
