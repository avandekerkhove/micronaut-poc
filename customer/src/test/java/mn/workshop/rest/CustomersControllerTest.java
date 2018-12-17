package mn.workshop.rest;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.micronaut.configuration.mongo.reactive.MongoSettings;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.io.socket.SocketUtils;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import mn.workshop.crud.Customer;

public class CustomersControllerTest {

    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeClass
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class, 
                CollectionUtils.mapOf(
                        "consul.client.registration.enabled", false,
                        MongoSettings.MONGODB_URI, "mongodb://localhost:" + SocketUtils.findAvailableTcpPort())); 
        client = server
                .getApplicationContext()
                .createBean(HttpClient.class, server.getURL());  
    }

    @AfterClass
    public static void stopServer() {
        server.stop();
        client.stop();
    }
    
    @Test
    public void testGetAll() {
        assertEquals(HttpStatus.OK, client.toBlocking().exchange("/customers").status());
    }
    
    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer("apeupres", "jean-michel", 34);
        assertEquals(HttpStatus.CREATED, client.toBlocking().exchange(HttpRequest.POST("/customers", customer)).status());
    }
    
}
