FROM node:latest

LABEL maintainer="raj.durvasula@gmail.com"

ENV server_name
ENV server_ip

WORKDIR /usr/src/app

COPY rdaddrbook-client/ ./

RUN echo "$server_ip server_name" >> /etc/hosts && \
  sed -i -e "s~192.168.101.21~$server_ip~g" src/environments/environment.ts

RUN npm install
  

EXPOSE 4200

CMD [ "npm", "start" ]

