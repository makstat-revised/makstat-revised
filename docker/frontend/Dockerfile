FROM node:14.16.0-buster

# create /makstat folder (bind-mount location)
RUN mkdir /makstat
WORKDIR /makstat

# by default, start the react dev server
CMD ["bash", "-c", "npm install && npm run dev"]

# run dev server at port 5000
EXPOSE 5000
