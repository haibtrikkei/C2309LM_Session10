package upload_firebase.demo_thymeleaf_firebase.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Bean
    public Storage storage() throws IOException {
        InputStream inputStream = new ClassPathResource("firebase_config.json").getInputStream();
        return StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(inputStream)).build().getService();
    }
}
