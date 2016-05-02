package spring4Template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring4Template.configuration.SpringJpaConfig;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(transactionManagerRef = "txManager", entityManagerFactoryRef = "entityManagerFactory")
@Import(SpringJpaConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}