package fu.capstone.outlookintegrationdemo.configs;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.spring.cloud.autoconfigure.implementation.context.properties.AzureGlobalProperties;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GraphConfig {
    private static final String SCOPE = "https://graph.microsoft.com/.default";

    @Bean
    public GraphServiceClient getClientCredentialsToken() {
        ClientSecretCredential credential = new ClientSecretCredentialBuilder()
                .tenantId("insert-your-tenant-id")
                .clientId("insert-your-client-id")
                .clientSecret("insert-your-client-secret")
                .build();
        return new GraphServiceClient(credential, SCOPE);
    }
}
