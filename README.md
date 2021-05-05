# MakStat-Revised
### About the project
This is the repo for the MakStat-Revised project. If you want to know what this project is about, pleace check [this document](https://drive.google.com/file/d/10VEVDZCsURH2MZd_uOWtVnn_RNcUJ3hD/view?usp=sharing) (accessible from any SEEU account).

Check `example.env` to get to know the configured ports and services. By default, the front-end is served at port 5000, the back-end at port 8080, and a database GUI at port 9000.

### Setup
- The `docker` folder contains the setup for deployable images.
  The same images are used as basis for the development setup.
- `docker-compose.yml` uses the deployable images and starts
  them with configuration tuned for development.

### Instructions to run the setup in your computer
- make sure you have installed Docker (for Windows, see Docker Desktop), and docker-compose. 
- clone the repo
- change working dir to the repo dir: `cd makstat-revised`
- create the required env variables: `cp example.env .env`
- start all containers: `docker-compose up`

### Docker Compose Cheat Sheet (development)
- start all containers: `docker-compose up`
- build and start all containers: `docker-compose up --build`
- stop all containers: `docker-compose down`
- build single container: `docker-compose build <service-name>`
- run single container: `docker-compose run <service-name>`
- run single container with different command: e.g. `docker-compose run <service-name> bash -c 'echo hello world'`
- list and remove volumes: `docker volume ls` & `docker volume rm <volume-name>`

### Common Problems
1. When pulling new changes that affect the database structure/migration files, you may need to remove the database volume in order for the backend to run without errors. To do so, run the following in the project dir:
(WARNING: all data inserted manually in the database will be lost!)
```
docker-compose down
docker volume rm makstat-revised_db_data
docker-compose up
```
