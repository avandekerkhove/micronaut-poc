package mn.workshop.rest;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;

@Controller("/customers")
public class CustomersController {

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }
}