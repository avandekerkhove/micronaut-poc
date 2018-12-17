package mn.workshop.crud;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Singleton
public class CustomersRepository {

    private static final String MONGO_DBS = "customers-repository";
    private static final String MONGO_COLLECTION = "customers";
    
    private final MongoClient mongoClient;

    public CustomersRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
    
    public Single<List<Customer>> getAll() {
        return Flowable.fromPublisher(
                getMongoCollection()
                    .find())
                .toList();
    }
    
    public Maybe<Customer> find(String name, String firstName, Integer age) {
        var filters = new ArrayList<Bson>();
        if (name != null) {
            filters.add(Filters.eq("name", name));
        }
        if (firstName != null) {
            filters.add(Filters.eq("firstName", firstName));
        }
        if (age != null) {
            filters.add(Filters.eq("age", age));
        }
        
        return Flowable.fromPublisher(
                getMongoCollection().find(Filters.and(filters)).limit(1))
                .firstElement();
    }
    
    public Single<Customer> save(Customer customer) {
        return find(customer.getName(), customer.getFirstName(), customer.getAge())
                .switchIfEmpty(
                        Single.fromPublisher(getMongoCollection().insertOne(customer)).map(success -> customer)
                );
    }
    
    private MongoCollection<Customer> getMongoCollection() {
        return mongoClient
                .getDatabase(MONGO_DBS)
                .getCollection(MONGO_COLLECTION, Customer.class);
    }
    
}