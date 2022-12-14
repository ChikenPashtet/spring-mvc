package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private static final String URL = "jdbc:mysql://localhost:3306/first_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD ="root";
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Person> index(){
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }
    public Person show(int id){
     //   return people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
        return null;
    }

    public void save(Person person){
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES("+1+",'"+person.getName()+"',"+person.getAge()+",'"+person.getEmail()+"')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
    }

    public void update(int id, Person person){
//        Person personeToBeUpdated = show(id);
//        personeToBeUpdated.setName(person.getName());
//        personeToBeUpdated.setAge(person.getAge());
//        personeToBeUpdated.setEmail(person.getEmail());

    }

    public void delete(int id) {
   //     people.removeIf(person -> person.getId()==id);
    }
}
