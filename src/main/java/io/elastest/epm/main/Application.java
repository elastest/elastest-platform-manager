package io.elastest.epm.main;

import io.elastest.epm.core.AdapterManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"io.elastest.epm", "io.elastest.epm.configuration"})
@EntityScan(basePackages = "io.elastest.epm.model")
@EnableJpaRepositories(value = "io.elastest.epm.repository")
public class Application implements CommandLineRunner {

    @Autowired private AdapterManagement adapterManagement;

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
        throw new ExitException();
        }
        adapterManagement.init();
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Application.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
      return 10;
    }
    }
}
