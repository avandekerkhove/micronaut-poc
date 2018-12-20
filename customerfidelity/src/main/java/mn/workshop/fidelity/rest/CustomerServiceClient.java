package mn.workshop.fidelity.rest;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("customer")
public interface CustomerServiceClient {

    @Get("/customers/{login}")
    public Single<Customer> getByLogin(String login);
    
}
