package com.jh.de.pacdetails.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

@Component
public class KeyVaultConfig {

    public SecretClient createSecretClient(Environment env){
        String kvUri = "https://" + env.getProperty("KV_NAME") + ".vault.azure.net";

        return new SecretClientBuilder()
                .vaultUrl(kvUri)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }
}
