package de.dseelp.discordsystem.core.spring.profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("sqlite")
@Component
public class SQLiteConfiguration implements DataSourceConfiguration{
    /*
    spring.datasource.url=jdbc:h2:mem:jpadb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=mypass
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.h2.console.enabled=true
     */

    @Value("spring.jpa.properties.hibernate.dialect")
    private String dialect = "org.hibernate.dialect.SQLiteDialect";


    @Override
    @Bean
    @ConfigurationProperties(value = "spring.datasource")
    public DataSource getSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setDatabaseName("test");
        dataSource.setUrl("jdbc:sqlite:discord.db");
        return dataSource;
    }
}
