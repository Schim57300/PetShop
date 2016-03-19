package my.petshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class MyPetShop {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyPetShop.class, args);
    }
    
}
