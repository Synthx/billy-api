package org.yezebi.billy.spring.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfig {
    private final ApiProperties properties;

    @Bean
    FirebaseApp firebaseApp(GoogleCredentials googleCredentials) {
        final FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .setProjectId(properties.getFirebase().getProjectId())
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    GoogleCredentials googleCredentials() throws IOException {
        final Resource credentials = properties.getFirebase().getCredentials();
        if (credentials != null) {
            return GoogleCredentials.fromStream(credentials.getInputStream());
        }

        return GoogleCredentials.getApplicationDefault();
    }

    @Bean
    FirebaseAuth firebaseAuth(FirebaseApp firebaseApp) {
        return FirebaseAuth.getInstance(firebaseApp);
    }
}
