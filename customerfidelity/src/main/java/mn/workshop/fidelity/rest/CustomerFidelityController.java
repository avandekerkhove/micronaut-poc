package mn.workshop.fidelity.rest;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

@Controller("/customerfidelity")
public class CustomerFidelityController {

    private final CustomerServiceClient customerClient;
    
    public CustomerFidelityController(CustomerServiceClient customerClient) {
        this.customerClient = customerClient;
    }

    @Post("/{login}")
    public Single<MutableHttpResponse<Customer>> addFidelityNEW(String login) {
        return customerClient.getByLogin(login)
                .map(customer -> {
                    customer.setFidelityAccount("123456");
                    return HttpResponse.ok(customer);
                });
    }
    
}