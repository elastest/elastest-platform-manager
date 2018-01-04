FROM openjdk:alpine
MAINTAINER baeldung.com

# Set Image Labels
ARG GIT_COMMIT=unspecified
LABEL git_commit=$GIT_COMMIT

ARG COMMIT_DATE=unspecified
LABEL commit_date=$COMMIT_DATE

ARG VERSION=unspecified
LABEL version=$VERSION 

COPY epm.jar .

EXPOSE 8180

ENTRYPOINT java -jar epm.jar
