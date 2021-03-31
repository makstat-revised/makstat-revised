FROM postgres:13.2

# postgres runs at port 5432 inside the container
EXPOSE 5432

# file path where postgres stores the databases
# NOTE: this is moved to a non-default location so that we have
#       full control over on which volumes it is stored.
# original location: /var/lib/postgresql/data
ENV PGDATA=/postgres-data

# name of database user
ENV POSTGRES_USER=''

# password of database user
ENV POSTGRES_PASSWORD=''

# name of database
ENV POSTGRES_DB=''
