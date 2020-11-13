# employees
A SSYS spring boot challenge

To create the database docker image, type on terminal:
  docker --name postgres -p5432:5432 -e POSTGRES_PASSWORD=ssysisloves2s2 -d postgres
  
After the database is totaly initialized, go to the project's path and type:
  docker build -t ssys/employee .
  docker run ssys/employee -p 8000:8000
  
