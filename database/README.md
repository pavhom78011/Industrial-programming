#Working with databases (SQLite)
## Statement of the task
Create a database in which the id, name, age of a person will be stored, have the ability to add information to the database, delete information from the database, sort, display information on the screen.
## Required dependencies
To run the project you will need:
SQLite
JavaFX
##Functions provided by the DatabaseOperations class for working with the database:
clearDatabase() - deletes the contents of the database
createNewTable() - creates a database if it has not yet been created
insert(String name, int age) - adds an element to the database
delete(int id) - deletes an element from the database
shiftIds() - restores the order of id
selectAll() - displays the contents of the database in the console
getAllNamesOfColumns() - gets the names of all the columns of the database as a StringTokenizer
GetAllInfo() - returns an ArrayList<String>, each element of which contains information about one of the columns
