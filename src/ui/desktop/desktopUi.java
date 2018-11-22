package ui.desktop;

import domain.model.Person;

import javax.swing.*;
import java.sql.*;
import java.util.Properties;

public class desktopUi {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX33?currentSchema=r0703601";
        properties.setProperty("user","local_r0703601");
        properties.setProperty("password","dvrJéU7R0U23eYi:");
        properties.setProperty("ssl","true");
        properties.setProperty("sslfactory","org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","prefer");


        boolean end = false;
        while(!end) {
            int choice = Integer.parseInt(JOptionPane.showInputDialog("1. show overview\n2. add person"));
            switch (choice) {
                case 1:
                    Connection connection = DriverManager.getConnection(url, properties);
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery("SELECT * FROM person");
                    try {
                        while (result.next()) {
                            String userid = result.getString("userid");
                            String email = result.getString("email");
                            String passwd = result.getString("passwd");
                            String firstname = result.getString("firstname");
                            String lastname = result.getString("lastname");

                            Person p = new Person(userid, email, passwd, firstname, lastname);
                            System.out.println(p);
                        }
                    } catch (SQLException exc){
                        JOptionPane.showMessageDialog(null,exc.getMessage());
                    }
                    statement.close();
                    connection.close();
                    end = true;
                    break;
                case 2:
                    String useridAdd = JOptionPane.showInputDialog("userid");
                    String emailAdd = JOptionPane.showInputDialog("email");
                    String passwdAdd = JOptionPane.showInputDialog("passwd");
                    String firstnameAdd = JOptionPane.showInputDialog("firstname");
                    String lastnameAdd = JOptionPane.showInputDialog("lastname");

                    Connection connectionAdd = DriverManager.getConnection(url, properties);
                    Statement statementAdd = connectionAdd.createStatement();
                    statementAdd.execute("INSERT INTO person(userid,email,passwd,firstname,lastname) VALUES (" + useridAdd + ",\'" + emailAdd + "\',\'" + passwdAdd + "\',\'" + firstnameAdd + "\',\'" +lastnameAdd +"\')");
                    statementAdd.close();
                    connectionAdd.close();
                    break;
            }
        }
    }
}
