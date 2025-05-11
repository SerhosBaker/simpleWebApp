package ru.smarkov.demo.configuration.jooq;

/*@Configuration
//@ImportAutoConfiguration(JooqAutoConfiguration.class)
@ComponentScan({"ru.smarkov.demo.jooq_3b.tables"})
@PropertySource("classpath:application.properties")*/
public class JooqConfiguration {
/*    private static final String POSTGRES_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(POSTGRES_DRIVER_CLASS_NAME);

        dataSource.setUrl(environment.getRequiredProperty("db.url"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));

        return dataSource;
    }*/
}
