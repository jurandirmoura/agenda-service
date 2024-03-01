package br.com.mutztech.agenda.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenApi() {
        Contact contact = new Contact();
        contact.setName("Jurandir Moura");
        contact.setEmail("jurandirmoura40@gmail.com");
        contact.setUrl("https://github.com/jurandirmoura");

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Rest API Agenda")
                        .contact(contact).description("Rest API Para Agendamentos de Consultas"));

    }
}
