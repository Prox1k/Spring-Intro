package spring.intro;

import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user1 = new User();
        user1.setLogin("prox1");
        user1.setPassword("pass");
        userService.add(user1);

        User user2 = new User();
        user2.setLogin("prox2");
        user2.setPassword("pass2");
        userService.add(user2);
        System.out.println(userService.listUsers());
    }
}
