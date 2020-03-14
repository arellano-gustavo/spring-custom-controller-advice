/*
 * Licencia:    Este código se encuentra bajo la protección
 *              que otorga el contrato establecido entre
 *              Ultrasist SA de CV y su cliente, IMPI, por lo
 *              que queda estrictamente prohibido copiar, donar
 *              vender y/o distribuir el presente código por
 *              cualquier medio electrónico o impreso sin el
 *              permiso explícito y por escrito del cliente.
 *
 * Proyecto:    Chatbot IMPI
 * Paquete:     com.sc.config
 * Modulo:      SwaggerConfig
 * Tipo:        clase
 * Autor:       Gustavo Adolfo Arellano Sandoval (GAA)
 * Fecha:       22 de Sep de 2019 (21_24)
 * Version:     0.0.1
 * .
 * Clase que se usa para dar de alta el servicio de Swagger
 *
 *
 */
package mx.com.cinepolis.anuncios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>SwaggerConfig class.</p>
 *
 * @author alvaro.salas
 * @version $Id: $Id
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  /**
   * <p>api.</p>
   *
   * @return a {@link springfox.documentation.spring.web.plugins.Docket} object.
   */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Cinepolis, REST API Documentation by Super Goose!",
                "Servicios REST de backend para AUTH, ADMIN y TRAIN publicados !!!",
                "1.0-SNAPSHOT",
                "Terminos de servicio",
                "garellanos@ultrasist.com.mx",
                "Licencia del API",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
