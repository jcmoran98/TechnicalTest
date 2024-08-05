# TechnicalTest



### Para montar el docker solo se necesita construir y ejecutar el siguiente comando en una terminal en la carpeta raíz del proyecto
```
docker-compose up --build -d
```
### La url del swagger para acceder a toda la documentación es en cualquiera de estos dos links:
```
http://localhost:5000/swagger-ui/index.html
http://localhost:5000/swagger
```
### Si se quiere ejecutar desde un IDE recordar configurar las variables de entorno
### para poder ejecutar tanto el microservicio como las pruebas
```
SPRING_DATASOURCE_PASSWORD=pfctiuserpassword
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/pfcti
SPRING_DATASOURCE_USERNAME=pfcti_user
```
# ¡IMPORTANTE!
### El docker-compose crea la base de datos pero no ejecuta los queries de creación de tablas ni SP, por lo que se tiene que ejecutar manualmente uno por uno. Ya que por alguna razón si se ejecutan todos de golpe generan un error. El archivo de la base de datos se llama init.sql
