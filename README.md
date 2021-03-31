# MakStat-Revised

## Docker Setup
- The `docker` folder contains the setup for deployable images.
  The same images are used as basis for the development setup.
- `docker-compose.yml` uses the deployable images and starts
  them with configuration tuned for development.

## Docker Compose Cheat Sheet (development)
- start all containers: `docker-compose up`
- build and start all containers: `docker-compose up --build`
- stop all containers: `docker-compose down`
- build single container: `docker-compose build <service-name>`
- run single container: `docker-compose run <service-name>`
- run single container with different command: e.g. `docker-compose run <service-name> bash -c 'echo hello world'`
- list and remove volumes (NOTE this will remove database data and app cache): `docker volume ls` & `docker volume rm <volume-name>`
