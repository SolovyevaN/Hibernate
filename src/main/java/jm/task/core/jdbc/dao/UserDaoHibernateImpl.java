package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction ;
        try  {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            try {
                String sql = "CREATE TABLE IF NOT EXISTS users (" +
                        "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(50) NOT NULL, " +
                        "last_name VARCHAR(50) NOT NULL, " +
                        "age TINYINT NOT NULL)";
                session.createSQLQuery(sql).executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null){
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
    }
}

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession(session);
    }
}

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            users = session.createQuery("from User ", User.class).list();
        } catch (Exception e) {
            if (users != null) {
                e.printStackTrace();
            }
        } finally {
            HibernateUtil.closeSession(session);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete from User ").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

