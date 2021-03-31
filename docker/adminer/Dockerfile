FROM adminer:4.8.0

# adminer runs at port 8080 inside the container
EXPOSE 8080

# install adminer plugins
ENV ADMINER_PLUGINS=auto-login
COPY ./docker/adminer/plugins/auto-login.php /var/www/html/plugins/auto-login.php

# login automatically when set to true
ENV ADMINER_AUTOLOGIN=false

# autologin: database type
# options: server/mysql, sqlite, sqlite2, pgsql, oracle, mssql, firebird, simpledb, mongo, elastic, clickhouse
ENV ADMINER_DB_DRIVER=pgsql

# autologin: database server location
ENV ADMINER_DB_SERVER=''

# autologin: database name
ENV ADMINER_DB_NAME=''

# autologin: database user
ENV ADMINER_DB_USER=''

# autologin: database password
ENV ADMINER_DB_PASSWORD=''
