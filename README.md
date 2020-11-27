# crisp
Run springboot service with maven command:
mvn spring-boot:run

Make API call with Postman:

POST http://localhost:8080/crisp/order/transform
set request body to use form-data, set the key to "file" and choose file type, select expected csv file

Example file is under src/main/resources/csv/order.csv
Output file would be under root folder

This service uses springboot to provides RESTful API, and uses opencsv to handle csv file. Since file can be huge, keep in mind that don't read the whole file in memory, try to process line by line. 
Under the assumption that the target object is not going to change, user can modify application.properties file to change the order of fields.

The basic workflow is that application getting RESTful request, then choosing processor based on file extension, and outputting target file.

Things to improve in the future:
1, Use spring batch framework to handle batch process
2, Save file data into database, same for error
3, Create fields mapping, and persist into database