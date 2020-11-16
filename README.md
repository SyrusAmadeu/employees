# employees

<h1>A SSYS spring boot challenge</h1>

<p>To create the database docker image, type on terminal:</p>
'''
  >docker --name postgres -p5432:5432 -e POSTGRES_PASSWORD=ssysisloves2s2 -d postgres</br>
  >docker run postgres</br>
'''
<p>After the database is totaly initialized, let's create the database from inside the container</p>
<p>Type the follow commands:</p>
'''
  >docker exec -it postgres bash</br>
  >su postgres</br>
  >psql</br>
  >CREATE DATABASE employee_database</br>
  >\q</br>
  >exit</br>
'''
<p>Go to the project's path and type:</p>
'''
  >docker build -t ssys/employee .</br>
  >docker run ssys/employee -p 8000:8000</br>
'''
<p>After this, type on the browser:</p>
  http://[docker image ip]/employees
