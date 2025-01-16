package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();

        userDao.saveUser ("Petya", "Ivanov", (byte) 20);
        System.out.println("User с именем Petya добавлен в базу.");
        userDao.saveUser("Zina", "Petrova", (byte) 25);
        System.out.println("User с именем Zina добавлен в базу.");
        userDao.saveUser("Kolya", "Pupkin", (byte) 31);
        System.out.println("User с именем Kolya добавлен в базу.");
        userDao.saveUser("Max", "Sidorov", (byte) 38);
        System.out.println("User с именем Max добавлен в базу.");

        userDao.removeUserById(1);
        System.out.println("User с ID = 1 удален из базы.");

        System.out.println("Список всех пользователей в базе:");
        for (User user : userDao.getAllUsers()) {
            System.out.println(user);
        }

        userDao.cleanUsersTable();
        System.out.println("Таблица пользователей очищена.");

        userDao.dropUsersTable();
        System.out.println("Таблица пользователей удалена.");

    }
}
