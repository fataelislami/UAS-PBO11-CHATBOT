package database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.activation.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config
{
    @Autowired
    Environment mEnv;

    @Bean
    public DataSource getDataSource()
    {
        String dbUrl=System.getenv("ec2-54-227-244-122.compute-1.amazonaws.com:5432");
        String username=System.getenv("uvhwytdtelcrwd");
        String password=System.getenv("768a207bed4674cf88692c1c796cf918cbc8c9ed5ad686748c323ebd2a57a26c");

        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(dbUrl);
        ds.setUsername(username);
        ds.setPassword(password);

        return (DataSource) ds;
    }

    @Bean(name="com.linecorp.channel_secret")
    public String getChannelSecret()
    {
        return mEnv.getProperty("com.linecorp.channel_secret");
    }

    @Bean(name="com.linecorp.channel_access_token")
    public String getChannelAccessToken()
    {
        return mEnv.getProperty("com.linecorp.channel_access_token");
    }

    @Bean
    public Dao getPersonDao()
    {
        return new DaoImpl(getDataSource());
    }
};