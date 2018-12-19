package mn.workshop.crud;

public class Customer {
    
    private String login;
    private String name;
    private String firstName;
    private int age;
    
    public Customer() {}
    
    public Customer(
            String login,
            String name, 
            String firstName, 
            int age) {
        this.login = login;
        this.name = name;
        this.firstName = firstName;
        this.age = age;
    }

    public String getId() {
        return login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
