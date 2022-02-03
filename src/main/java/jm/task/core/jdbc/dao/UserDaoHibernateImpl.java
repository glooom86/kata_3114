package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction;
        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS mynewdatabase.users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "name VARCHAR(45)," +
                "lastName VARCHAR(45)," +
                "age INT NOT NULL);";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction;
        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS mynewdatabase.users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction;
        User user = new User(name, lastName, age);
        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User с именем – "+name+" добавлен в базу данных");
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction;
        try(Session session = Util.getSessionFactory().openSession()){
            User user = session.get(User.class, id);
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List users;
        Transaction transaction;
        try(Session session = Util.getSessionFactory().openSession()){
            String sql = "select * from users";
            transaction = session.beginTransaction();
            users = session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
            transaction.commit();
        }
        return (List<User>) users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction;
        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            String sql = "TRUNCATE users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
