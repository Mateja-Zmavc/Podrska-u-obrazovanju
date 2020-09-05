package THE.Group.Podrska.u.obrazovanju;

import THE.Group.Podrska.u.obrazovanju.config.WebConfig;
import THE.Group.Podrska.u.obrazovanju.repositories.KorisnikRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackageClasses = KorisnikRepository.class)
public class PodrskaUObrazovanjuApplication extends WebConfig {

	public static void main(String[] args) {
		SpringApplication.run(PodrskaUObrazovanjuApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Configuration
	public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

		@Override
		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/uploads/**").addResourceLocations("file:///" + System.getProperty("user.dir") + "/src/main/uploads/");
		}
	}
}
