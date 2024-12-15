package co.istad.mbbanking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Mobile Banking API",
                version = "2.0",
                description = "API documentation for Mobile Banking Application"
        )
)
public class MbbankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbbankingApplication.class, args);
    }

}
