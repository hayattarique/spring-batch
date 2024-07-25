#Spring Batch CSV to Database Project

Overview
This project demonstrates a Spring Batch application that reads data from a CSV file and writes it into a database. During the development of this project, I discovered significant performance differences between using Spring Data JPA and JDBC, and I opted for JDBC due to its superior performance.

#Features
Read data from a CSV file.
Process data using Spring Batch.
Write data into a database.
Optimized performance using JDBC over Spring Data JPA.

#Performance Insights
Spring Data JPA: Inserting 1000 records took 3 to 4 minutes.
JDBC: Inserting 1000 records took only 3 seconds.

#Technologies Used
Spring Boot
Spring Batch
JDBC
H2 Database (for simplicity, can be replaced with any other database)
Maven
