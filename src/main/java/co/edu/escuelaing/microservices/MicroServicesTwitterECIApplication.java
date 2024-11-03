package co.edu.escuelaing.microservices;

import co.edu.escuelaing.microservices.service.UserServicesMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;


@EnableMongoRepositories
@SpringBootApplication
public class MicroServicesTwitterECIApplication {
    @Autowired
    UserServicesMap userServices;

    public static void main(String[] args){
        SpringApplication app = new SpringApplication(MicroServicesTwitterECIApplication.class);
        app.setDefaultProperties(Collections.<String, Object>singletonMap("server.port", getPort()));
        app.run(args);
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}
