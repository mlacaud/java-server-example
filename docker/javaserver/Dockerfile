FROM java:8
MAINTAINER Mathias LACAUD <mlacaud@viotech.net>

EXPOSE 9000

RUN apt-get update && apt-get install -y netcat
ADD javaserver.jar javaserver.jar
COPY entrypoint.sh /sbin/entrypoint.sh
RUN chmod 755 /sbin/entrypoint.sh

CMD /sbin/entrypoint.sh
