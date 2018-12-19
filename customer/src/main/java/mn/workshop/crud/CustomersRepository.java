package mn.workshop.crud;

import java.util.List;

import javax.inject.Singleton;

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
    
    public Maybe<Customer> getByLogin(String login) {
        return Flowable.fromPublisher(
                getMongoCollection().find(Filters.eq("login", login)).limit(1))
                .firstElement();
    }
    
    public Single<Customer> save(Customer customer) {
        return getByLogin(customer.getLogin())
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