package server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "server")
public class ServerConfig {

    @Bean("server")
    public Server server(){
        Server server = new Server();
        return server;
    }
}
