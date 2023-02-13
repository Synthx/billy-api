package org.yezebi.billy.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;

@Data
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
    private Firebase firebase;

    @Data
    public static class Firebase {
        private String projectId;
        @Nullable
        private Resource credentials;
    }
}
