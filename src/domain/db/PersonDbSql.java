package domain.db;

import domain.db.DbException;
import domain.db.PersonDb;
import domain.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDbSql implements PersonDb {
    private Properties properties;
    private String url;

    public PersonDbSql(Properties properties){
        try{
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        }catch (Exception e){
            throw new DbException(e.getMessage(),e);
        }
    }

    @Override
    public Person get(String personId) {
        Person person = null;
        String sql = "SELECT * FROM person WHERE userid=?";
        try(
            Connection connection = DriverManager.getConnection(url,properties);
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, personId);
            ResultSet result = statement.executeQuery();
            result.next();

            String userid = result.getString("userid");
            String email = result.getString("email");
            String passwd = result.getString("passwd");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            try{
                person = new Person(userid,email,passwd,firstname,lastname);
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
        return person;
    }

    @Override
    public List<Person> getAll() {

        List<Person> persons = new ArrayList<>();
        try(
            Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement()
        ){
            ResultSet result = statement.executeQuery("SELECT * FROM person");
            while (result.next()) {
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String userid = result.getString("userid");
                String email = result.getString("email");
                String passwd = result.getString("passwd");
                try {
                    Person person = new Person(userid, email, passwd, firstname, lastname);
                    persons.add(person);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
        return persons;
    }

    @Override
    public void add(Person person) {
        String sql = "INSERT INTO person(userid,email,passwd,firstname,lastname) VALUES (?,?,?,?,?)";
        try(
            Connection connection = DriverManager.getConnection(url,properties);
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setInt(1,person.getUserid());
            statement.setString(2,person.getEmail());
            statement.setString(3,person.getPassword());
            statement.setString(4,person.getFirstName());
            statement.setString(5,person.getLastName());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
    }

    @Override
    public void update(Person person) {
        String sql = "UPDATE person SET email = ?, passwd = ?, firstname = ?, lastname = ? WHERE userid = ?";
        if (person == null) {
            throw new DbException("No person given");
        }
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1,person.getEmail());
            statement.setString(2,person.getPassword());
            statement.setString(3,person.getFirstName());
            statement.setString(4,person.getLastName());
            statement.setInt(5,person.getUserid());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DbException("query failed", e);
        }
    }

    @Override
    public void delete(String personId) {
        String sql = "DELETE FROM person WHERE userid=?";
        try(
                Connection connection = DriverManager.getConnection(url,properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setInt(1,Integer.parseInt(personId));
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
    }

    @Override
    public int getNumberOfPersons() {
        try(
                Connection connection = DriverManager.getConnection(url,properties);
                Statement statement = connection.createStatement()
        ){
            String sql = "SELECT COUNT (*) FROM person";
            ResultSet result = statement.executeQuery(sql);
            return result.getInt("count");
        } catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
    }
}
