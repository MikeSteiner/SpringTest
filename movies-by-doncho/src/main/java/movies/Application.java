package movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        System.out.println(greeter.notifySuccessfulAppStart());

        SpringApplication.run(Application.class, args);
    }
}
