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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Mazda", 3)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Mazda", 3)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Lexus RX", 450)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Lexus RX", 450)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }

      User testUser = userService.getUserByCarModelAndSeries("Lexus RX", 450);
         System.out.println("Id = "+ testUser.getId());
         System.out.println("First Name = "+testUser.getFirstName());
         System.out.println("Last Name = "+testUser.getLastName());
         System.out.println("Email = "+testUser.getEmail());
         System.out.println(testUser.getCar().getModel() + " " + testUser.getCar().getSeries());
         System.out.println();

      context.close();
   }
}
