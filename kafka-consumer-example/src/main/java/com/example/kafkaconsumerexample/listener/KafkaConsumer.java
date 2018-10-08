package com.example.kafkaconsumerexample.listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.datastax.driver.core.utils.UUIDs;
import com.example.kafkaconsumerexample.CustRepo;
import com.example.kafkaconsumerexample.model.Customer;
import com.example.kafkaconsumerexample.model.User;
@Service
public class KafkaConsumer {
	@Autowired
	private CustRepo repository;
	 @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
	    public void consume(String message) {

			this.repository.deleteAll();
			// save a couple of customers
			System.out.println("Consumed message starting: " + message);
			this.repository.save(new Customer(UUIDs.timeBased(), message, "Singh"));
			this.repository.save(new Customer(UUIDs.timeBased(), "Ramesh", message));

			// fetch all customers
			System.out.println("Customers found with findAll():");
			System.out.println("-------------------------------");
			for (Customer customer : this.repository.findAll()) {
				System.out.println(customer);
			}
			System.out.println();

			// fetch an individual customer
			System.out.println("Customer found with findByFirstName('Devendra'):");
			System.out.println("--------------------------------");
			System.out.println(this.repository.findByFirstName("Devendra"));

			System.out.println("Customers found with findByLastName('Singh'):");
			System.out.println("--------------------------------");
			for (Customer customer : this.repository.findByLastName("Singh")) {
				System.out.println(customer);
			}
	        System.out.println("Consumed message: " + message);
	    }


	    @KafkaListener(topics = "Kafka_Exa", groupId = "group_j",
	            containerFactory = "userKafkaListenerFactory")
	    public void consumeJson(User user) {
	    	//this.repository.deleteAll();
			// save a couple of customers
	    	   System.out.println("Consumed JSON Message: " + user);
			this.repository.save(new Customer(UUIDs.timeBased(), user.getName(), user.getDept()));

			// fetch all customers
			System.out.println("Customers found with findAll():");
			System.out.println("-------------------------------");
			for (Customer customer : this.repository.findAll()) {
				System.out.println(customer);
			}
			System.out.println();

			// fetch an individual customer
			System.out.println("Customer found with findByFirstName('Devendra'):");
			System.out.println("--------------------------------");
			System.out.println(this.repository.findByFirstName("Devendra"));

			System.out.println("Customers found with findByLastName('Singh'):");
			System.out.println("--------------------------------");
			for (Customer customer : this.repository.findByLastName("Singh")) {
				System.out.println(customer);
			}
	    }
}
