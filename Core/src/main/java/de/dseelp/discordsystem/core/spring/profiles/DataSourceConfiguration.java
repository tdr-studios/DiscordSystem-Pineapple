package de.dseelp.discordsystem.core.spring.profiles;

import javax.sql.DataSource;

public interface DataSourceConfiguration {
    DataSource getSource();
}
