package com.jh.de.pacdetails.config;

import com.azure.security.keyvault.secrets.SecretClient;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "sqlmiPtrEntityManagerFactory", basePackages = {
        "com.jh.de.pacdetails.repo.ptr" }, transactionManagerRef = "sqlmiPtrTransactionManager")
public class SQLMIPTRDatasource {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.sqlmi.ptr")
    public DataSourceProperties sqlmiPtrDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.sqlmi.configuration")
    public DataSource sqlMiPtrDataSource(Environment env, KeyVaultConfig keyVaultConfig) {
         if(StringUtils.equals(env.getProperty("ENV_NAME"),"local")) {
             return sqlmiPtrDataSourceProperties().initializeDataSourceBuilder()
                     .type(HikariDataSource.class).build();
        }
        String userNameKey = env.getProperty("spring.datasource.sqlmi.ptr.username");
        String passwordKey = env.getProperty("spring.datasource.sqlmi.ptr.password");
        SecretClient secretClient = keyVaultConfig.createSecretClient(env);
        String userName = secretClient.getSecret(userNameKey).getValue();
        String password = secretClient.getSecret(passwordKey).getValue();
        return DataSourceBuilder.create()
                        .url(env.getProperty("spring.datasource.sqlmi.ptr.url"))
                .username(userName)
                .password(password)
                .build();

    }

    @Primary
    @Bean(name = "sqlmiPtrEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sqlMiMemberEntityManagerFactory(EntityManagerFactoryBuilder builder, Environment env, KeyVaultConfig keyVaultConfig) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.implicit_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        properties.put("hibernate.physical_naming_strategy",
                "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");

        return builder
                .dataSource(sqlMiPtrDataSource(env, keyVaultConfig))
                .packages("com.jh.de.pacdetails.model.entity.ptr")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager sqlmiPtrTransactionManager(
            final @Qualifier("sqlmiPtrEntityManagerFactory") LocalContainerEntityManagerFactoryBean sqlMiMemberEntityManagerFactory) {
        return new JpaTransactionManager(sqlMiMemberEntityManagerFactory.getObject());
    }
}
