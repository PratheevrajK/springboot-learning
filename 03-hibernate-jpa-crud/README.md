Database: MySQL, Connection: springstudent, Username: springstudent, Password: springstudent

###### Hibernate, JPA and EntityManager is same as learnt in JavaMasterclass course. Here, the EntityManager and Transaction creation and usage has been simplified.

![img.png](img.png)

![img_1.png](img_1.png)

### @Transactional - Method level:
- Automagically begin and end a transaction for your JPA code.

### @Repository - Class level:
- Specialized Annotation for DAOs
- Spring will automatically register the DAO implementation
- Spring also provides translation of any JDBC related exceptions


![img_2.png](img_2.png)