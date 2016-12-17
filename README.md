# cloud-computing-instance-management

For the REST docs, go to http://localhost:8080/jsondoc-ui.html, insert http://localhost:8080/jsondoc in the box and get the documentation.

The database is in H2 and it is recreated every time that runs the application. Default users are:

### Users ###
Username: userOne
Password: passOne

Username: userTwo
Password: passTwo

Username: userTwo
Password: passTwo

The application uses Token and UUID as authentication keys. 

### Instances ###

* Instances are associated to Plans.
* Instances have the information about CPU, memory and storage size.

### Plans ###

* Plans have the information about the pricing per CPU, memory, etc.

### User Instances ###

* The instances from the user.

### History ###

* It has the history from the user instances (turn on, turn off and time).

