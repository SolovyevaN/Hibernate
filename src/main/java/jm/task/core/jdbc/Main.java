package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        HibernateUtil.getSessionFactory();
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();


        userDao.saveUser("Petya", "Ivanov", (byte) 20);
        userDao.saveUser("Zina", "Petrova", (byte) 25);
        userDao.saveUser("Kolya", "Pupkin", (byte) 31);
        userDao.saveUser("Max", "Sidorov", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

        System.out.println(userDao);
        HibernateUtil.SessionFactoryClose();
    }
}
