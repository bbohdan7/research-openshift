FROM alpine

RUN adduser --disabled-password -u 12345 bogdan

RUN apk update && apk add git
RUN git clone https://github.com/zbogdan7/wasapp /app/

RUN chown --recursive bogdan:bogdan /app/

COPY --chown=bogdan:bogdan cmd.sh /cmd.sh

USER bogdan

ENTRYPOINT ["/cmd.sh"]
