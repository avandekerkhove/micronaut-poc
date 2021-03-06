package mn.workshop.rest;

import java.util.List;

import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import io.reactivex.Single;
import mn.workshop.crud.Customer;
import mn.workshop.crud.CustomersRepository;

@Controller("/customers")
@Validated
public class CustomersController {

    private final CustomersRepository repository;
    
    public CustomersController(CustomersRepository repository) {
        this.repository = repository;
    }
    
    @Get("/")
    public Single<List<Customer>> getAll() {
        return repository.getAll();
    }
    
    @Get("/{login}")
    public Single<Customer> getByLogin(String login) {
        return repository.getByLogin(login).toSingle();
    }
    
    @Post("/")
    public HttpResponse<Single<Customer>> save(@Valid @Body Customer customer) {
        return HttpResponse.created(repository.save(customer));
    }
}