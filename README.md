# RSIKSPR
## Development of scalable profuction-grade information and communication systems
### Setup

Generating the docker image:
```shell
$ mvn clean package && docker build --tag=rsikspr:latest .
```

Running the application:
```shell
$ docker compose up
```

### Example data

Data may be sent to `/messages` endpoint using POST method as follows:
```shell
$ curl --location --request POST 'localhost:5555/messages' 
--header 'Content-Type: application/json' 
--data-raw '{
    "content": "Lorem ipsum.",
    "authorName": "John Doe"
}'
```

In order to see messages from today a GET request can be sent to `/messages/today`.