FROM node:20-alpine AS build
WORKDIR /app
COPY ../app/package.json .
RUN npm install
COPY ../app ./
RUN npm run build

FROM node:20-alpine
WORKDIR /app
COPY --from=build /app/dist ./dist
COPY --from=build /app/node_modules ./node_modules
COPY ../app/package.json .
COPY ../app/vite.config.ts .

RUN npm install typescript

CMD [ "npm", "run", "preview" ]
