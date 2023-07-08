# Project2 
It is solution project 1 of course https://www.udemy.com/course/spring-alishev/

In the local library, they want to transition to digital bookkeeping. 
You need to develop a web application for them. Librarians should be 
able to register readers, issue books to them, and release books 
(after the reader returns the book back to the library).

### Endpoints
- http://localhost:8080/book
- http://localhost:8080/people
- http://localhost:8080/book?page=1&books_per_page=3
- http://localhost:8080/book?page=1&books_per_page=3&sort_by_year=true
- http://localhost:8080/book?sort_by_year=true
- http://localhost:8080/book/search

### Run app
- Java 17 must be installed
- Run db in docker ```docker compose up```
- Run app ```./gradlew run```

### Run postgres in docker
- Start docker container ```docker compose up```
- Stop docker container ```docker compose down```
