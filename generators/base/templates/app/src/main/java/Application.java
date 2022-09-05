package <%= packageName %>;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class <%= configOptions.appNameCapi %> {

    public static void main(String[] args) {
        SpringApplication.run(<%= configOptions.appNameCapi %>.class, args);
    }
}
