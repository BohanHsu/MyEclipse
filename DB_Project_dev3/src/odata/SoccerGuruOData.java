package odata;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.odata4j.producer.jpa.JPAProducer;
import org.odata4j.producer.resources.DefaultODataProducerProvider;

public class SoccerGuruOData {
	public static JPAProducer createProducer() {
		String persistenceUnitName = "SoccerGuruServiceHibernate";
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(persistenceUnitName);
		String namespace = "SoccerGuru";
		JPAProducer producer = new JPAProducer(entityManagerFactory, namespace,
				50);
		DatabaseUtils.fillDatabase(namespace.toLowerCase(), "/META-INF/persistence.xml");
		return producer;
	}

	public static void main(String[] args) {

		System.out.println("start");
		
		DefaultODataProducerProvider.setInstance(createProducer());
		
		System.out.println("start the engine");
		
		new ODataServerFactory(JaxRsImplementation.JERSEY)
				.hostODataServer("http://localhost:8888/SoccerGuru.svc/");
		System.out.println("end");
	}
}
