FROM node:20-alpine AS build

WORKDIR /app

COPY ../proxy/package.json .

RUN npm install

COPY ../proxy/ ./

CMD ["node", "src/main.js"]
