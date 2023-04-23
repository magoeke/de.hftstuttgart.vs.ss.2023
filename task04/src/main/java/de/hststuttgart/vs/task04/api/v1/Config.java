package de.hststuttgart.vs.task04.api.v1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.mediatype.hal.CurieProvider;
import org.springframework.hateoas.mediatype.hal.DefaultCurieProvider;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type= { EnableHypermediaSupport.HypermediaType.HAL})
public class Config {

    @Bean
    public CurieProvider curieProvider() {
        return new DefaultCurieProvider("ex", UriTemplate.of("/docs/{rel}"));
    }
}
