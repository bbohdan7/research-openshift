FROM ubuntu

RUN apt update && apt install -y fortune

ADD loop.sh /bin/fortuneloop.sh

ENTRYPOINT ["/bin/fortuneloop.sh"]

CMD ["10"]

