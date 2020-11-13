FROM alpine:latest

RUN adduser --disabled-password -u 12345 bogdan
RUN apk update && apk add git && apk add openjdk11 && apk add maven

USER bogdan

WORKDIR /home/bogdan

