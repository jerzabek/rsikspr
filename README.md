# RSIKSPR
## Development of scalable profuction-grade information and communication systems

This monorepo contains the RSIKSPR final project code.

## Directory structure
---
- 🖥️ `services` - microservices used to interact with this application.
  - 📦 `chat-service` - responsible for messages and conversations
  - 📦 `billing-service` - handles bills which depict users accumulated debt
  - 📦 `payment-service` - handles payment operations and closing bills
- 🎥 `nginx` - proxy configuration

## Setup guide

Application is containerized so you must build then run the docker images.

### Build

Before you build the application you must prepare secrets by creating a `.env` file. It is used by docker during build.

After than simply build using docker compose.
```sh
$ cp .env.example .env

$ docker compose build
```

### Run

Services are scaled automatically, within the docker network a DNS will resolve all service addresses.

```sh
$ docker compose up -d --scale billing-service=2 --scale chat-service=3 --scale payment-service=2
```