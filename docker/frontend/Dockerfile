FROM node:14.16.0-buster

# create maven cache for non-root user
RUN mkdir -p /home/node/.npm

# create /makstat folder (bind-mount location)
RUN mkdir /makstat
WORKDIR /makstat

# by default, start the react dev server
CMD ["bash", "-c", "npm install && npm run dev"]

# run dev server at port 5000
EXPOSE 5000
