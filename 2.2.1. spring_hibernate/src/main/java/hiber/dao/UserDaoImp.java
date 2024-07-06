package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      String hql = "SELECT u FROM User u JOIN FETCH u.car c WHERE c.model = :model AND c.series = :series";
      Query<User> query = session.createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.uniqueResult();
   }

   @Override
   public void saveUser(User user) {
      Session session = sessionFactory.getCurrentSession();
      session.save(user);
   }

   @Override
   public void saveCar(Car car) {
      Session session = sessionFactory.getCurrentSession();
      session.save(car);
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void addUserWithCar(UserService userService, String firstName, String lastName, String email, String model, int series) {
      User user = new User(firstName, lastName, email);
      userService.saveUser(user);
      Car car = new Car(model, series, user);
      userService.saveCar(car);
      user.setCar(car);
      userService.add(user);
   }
}
