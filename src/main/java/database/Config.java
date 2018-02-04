package database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
public class Config
{
    public Connection connect() {
        final String url = "jdbc:postgresql://ec2-54-227-244-122.compute-1.amazonaws.com:5432/dcen7h1umbahmi?sslmode=require";
        final String user = "uvhwytdtelcrwd";
        final String password = "768a207bed4674cf88692c1c796cf918cbc8c9ed5ad686748c323ebd2a57a26c";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }


};