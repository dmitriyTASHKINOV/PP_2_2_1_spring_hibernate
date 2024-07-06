package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;

import java.util.List;

public interface UserDao {
   void addUserWithCar(UserService userService, String firstName, String lastName, String email, String model, int series);
   void saveUser(User user);
   void saveCar(Car car);
   User getUserByCarModelAndSeries(String model, int series);
   void add(User user);
   List<User> listUsers();
}
