FROM node:14.17.0-alpine as build

WORKDIR /usr/local/app
COPY ./ /usr/local/app/

RUN npm install
RUN npm run build


FROM nginx:1.17.1-alpine
RUN chmod g+rwx /var/cache/nginx /var/run /var/log/nginx
COPY ./nginx/nginx.conf /etc/nginx/conf.d/default.conf

RUN sed -i.bak 's/listen\(.*\)80;/listen 8081;/' /etc/nginx/conf.d/default.conf
# comment user directive as master process is run as user in OpenShift anyhow
RUN sed -i.bak 's/^user/#user/' /etc/nginx/nginx.conf
COPY --from=build /usr/local/app/dist /usr/share/nginx/html

EXPOSE 80
