package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      userService.addUserWithCar(userService, "Dima", "Tashkinov", "tash@gmail.com", "Model2", 456);
      userService.addUserWithCar(userService, "Dima1", "Tashkinov", "tash@gmail.com", "Model3", 4561);
      userService.addUserWithCar(userService, "Dima2", "Tashkinov", "tash@gmail.com", "Model4", 4562);
      userService.addUserWithCar(userService, "Dima3", "Tashkinov", "tash@gmail.com", "Model5", 4563);
      userService.getUserByCarModelAndSeries("Model2", 456);
      userService.getUserByCarModelAndSeries("Model3", 4561);
      userService.getUserByCarModelAndSeries("Model4", 4562);
      userService.getUserByCarModelAndSeries("Model5", 4563);
      context.close();
   }
}
