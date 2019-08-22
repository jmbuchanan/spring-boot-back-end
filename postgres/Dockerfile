#Use slim postgres image
FROM postgres:10.9-alpine

COPY schema.sql /docker-entrypoint-initdb.d/

ARG PGUSER
ARG PGPASSWORD

#VOLUME /home/matt/data:/var/lib/postgresql/data

ENV POSTGRES_USER=${PGUSER} POSTGRES_PASSWORD=${PGPASSWORD} POSTGRES_DB=site_database

