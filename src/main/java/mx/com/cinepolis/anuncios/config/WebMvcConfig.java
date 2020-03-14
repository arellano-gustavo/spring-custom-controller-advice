package mx.com.cinepolis.anuncios.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    
    @Value("${enable.swagger.interface}")
    private boolean enableSwaggerInterface;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(enableSwaggerInterface) {
            logger.info("Se habilita lainterfaz de swagger");
        registry
            .addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry
            .addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
        } else {
            logger.info("Interfaz de swagger Inhabilitada (enable.swagger.interface=false)");
        }

      logger.info("Setting up handlers for VueJS Locations");
      registry
          .addResourceHandler("index.html")
          .addResourceLocations("classpath:/dist/index.html");
      registry
          .addResourceHandler("/js/**")
          .addResourceLocations("classpath:/dist/js/");
      registry
      .addResourceHandler("/css/**")
      .addResourceLocations("classpath:/dist/css/");
    }
/**/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("Disabling CORS");
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST","PUT", "DELETE", "OPTIONS", "HEAD");
    }
/**/
}
