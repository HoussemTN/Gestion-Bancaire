package services;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement 
@EnableJpaRepositories(basePackages = { "repositories" }) 
@Configuration 
public class Config { 
// la source de donnees MySQL 
@Bean 
public DataSource dataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://localhost:3306/banque");
	dataSource.setUsername("root");
	dataSource.setPassword("");
	return dataSource;
}

//le provider JPA 
@Bean public JpaVendorAdapter jpaVendorAdapter(){ 
	HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter(); 
	hibernateJpaVendorAdapter.setShowSql(false);
	hibernateJpaVendorAdapter.setGenerateDdl(true);
	hibernateJpaVendorAdapter.setDatabase(Database.MYSQL); 
	return hibernateJpaVendorAdapter; }

//EntityManagerFactory 
@Bean public EntityManagerFactory entityManagerFactory(JpaVendorAdapter jpaVendorAdapter , DataSource dataSource) {
	LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean(); 
	factory.setJpaVendorAdapter(jpaVendorAdapter); 
	factory.setPackagesToScan("entities"); 
	factory.setDataSource(dataSource);
	factory.afterPropertiesSet(); 
	return factory.getObject();
	}
//Transaction manager 
@Bean public PlatformTransactionManager transactionManager(EntityManagerFactory c) { 
	JpaTransactionManager txManager = new JpaTransactionManager(); 
	txManager.setEntityManagerFactory(c); 
	return txManager; 
	}

}
