package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {


   @Autowired
   private UserDao userDao;
   @Override
   public void addUserWithCar(UserService userService, String firstName, String lastName, String email, String model, int series) {
    userDao.addUserWithCar(userService,firstName,lastName,email,model,series);
   }

   @Override
   @Transactional
   public void saveUser(User user) {
      userDao.saveUser(user);
   }

   @Override
   @Transactional
   public void saveCar(Car car) {
      userDao.saveCar(car);
   }

   @Transactional
   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      return userDao.getUserByCarModelAndSeries(model,series);
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

}
