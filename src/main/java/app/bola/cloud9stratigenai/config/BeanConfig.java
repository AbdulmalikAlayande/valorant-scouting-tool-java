package app.bola.cloud9stratigenai.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				       .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				       .setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}
}
