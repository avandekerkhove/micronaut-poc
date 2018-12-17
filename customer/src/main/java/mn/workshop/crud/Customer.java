package mn.workshop.crud;

import java.io.Serializable;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4252169903131580577L;
    
    private String name;
    private String firstName;
    private int age;
    
    public Customer() {}
    
    @JsonCreator
    @BsonCreator
    public Customer(
            @JsonProperty("name")
            @BsonProperty("name") String name, 
            @JsonProperty("firstName") 
            @BsonProperty("firstName") String firstName, 
            @JsonProperty("age")
            @BsonProperty("age") int age) {
        this.name = name;
        this.firstName = firstName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
