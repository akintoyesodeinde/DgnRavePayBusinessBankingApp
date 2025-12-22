package business.banking.dgnravepay.auth.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig {

    /**
     * Single allowed frontend origin
     * application.properties:
     * frontend.url=http://localhost:5173
     */
    @Value("${frontend.url}")
    private String frontendUrl;

    /**
     * CORS configuration for React (Vite)
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(frontendUrl)
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }

            /**
             *  Support image/jpeg;charset=UTF-8 for byte[] responses
             */
            @Override
            public void extendMessageConverters(
                    List<org.springframework.http.converter.HttpMessageConverter<?>> converters) {

                ByteArrayHttpMessageConverter byteConv = converters.stream()
                        .filter(c -> c instanceof ByteArrayHttpMessageConverter)
                        .map(c -> (ByteArrayHttpMessageConverter) c)
                        .findFirst()
                        .orElseGet(ByteArrayHttpMessageConverter::new);

                List<MediaType> types = new ArrayList<>(byteConv.getSupportedMediaTypes());
                try {
                    MediaType jpegWithCharset =
                            MediaType.parseMediaType("image/jpeg;charset=UTF-8");
                    if (!types.contains(jpegWithCharset)) {
                        types.add(jpegWithCharset);
                    }
                } catch (Exception ignored) {}

                byteConv.setSupportedMediaTypes(types);

                if (!converters.contains(byteConv)) {
                    converters.add(0, byteConv);
                }
            }
        };
    }
}
