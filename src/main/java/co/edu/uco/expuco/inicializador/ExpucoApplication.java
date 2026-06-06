package co.edu.uco.expuco.inicializador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// scanBasePackages le dice a Spring que busque mis clases (controller, service,
// repository) en todo el paquete co.edu.uco.expuco, no solo en "inicializador".
@SpringBootApplication(scanBasePackages = "co.edu.uco.expuco")
public class ExpucoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpucoApplication.class, args);
	}

}
