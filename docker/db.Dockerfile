FROM mysql:5.7

COPY docker/init-script.sql /docker-entrypoint-initdb.d


