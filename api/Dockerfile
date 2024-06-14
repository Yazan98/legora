# Use the official Node.js image as the base image
FROM node:18-alpine

# Set the working directory inside the container
#WORKDIR /usr/src/app

# Install Python, g++, and make (necessary for node-gyp and other build tools)
RUN apk add --no-cache python3 py3-pip make g++


# Copy the package.json and package-lock.json files to the working directory
COPY package*.json ./

# Install the app dependencies
RUN npm install --legacy-peer-deps --force

# Copy the rest of the application code to the working directory
COPY . .

# Expose the port that the app runs on
EXPOSE 8000

# Define the command to run the app
CMD [ "npm", "run", "start" ]