package services;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service; import entities.Customer;
import repositories.CustomerRepository;
@Service("metier") 
public class Metier { public static void main(String[] args) { 
	ConfigurableApplicationContext context = SpringApplication.run(Config.class); 
	CustomerRepository repository = context.getBean(CustomerRepository.class);

//ajouter des clients
repository.save(new Customer("AAA", "BBB"));
repository.save(new Customer("CCC", "DDD"));
repository.save(new Customer("EEE", "FFF"));
// afficher tous les clients 
Iterable <Customer > customers = repository.findAll();
System.out.println("Customers found with findAll():"); 
System.out.println("-------------------------------"); 
for (Customer customer : customers) {
System.out.println(customer); 
}
System.out.println(); 
// chercher les clients avec le nom "BBB"
List<Customer > bauers = repository.findByLastName("BBB");
System.out.println("Customer found with findByLastName(’BBB’):"); 
System.out.println("--------------------------------------------"); 
for (Customer bauer : bauers) {
	System.out.println(bauer); 
	} context.close(); 
}
}
