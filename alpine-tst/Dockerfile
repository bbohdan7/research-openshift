FROM alpine

RUN adduser --disabled-password -u 123456 bogdan

RUN mkdir /blabla ; touch /blabla/text.txt ; touch /blabla/text2.txt
RUN chown -R bogdan:bogdan /blabla

USER bogdan
