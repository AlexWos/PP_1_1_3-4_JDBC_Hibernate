package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args)  {
        userService.createUsersTable();

        userService.saveUser("Питер", "Грю", (byte) 78);
        userService.saveUser("Алекс", "Раш", (byte) 74);
        userService.saveUser("Макс", "Далис", (byte) 59);
        userService.saveUser("Антон", "Андерсон", (byte) 74);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
