package rest.entity;

/**
 * Created by vrg on 08/06/16.
 */
public class Customer extends EntityBase{

    private String name;
    private String email;

    public Customer() {
    }

    public Customer(Long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public Customer(Customer other) {
        super(other.getId());
        this.name = other.name;
        this.email = other.email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        markDirty();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        markDirty();
    }

    @Override
    protected void updateDigest(DigestBuilder digestBuilder) {
        digestBuilder
                .update(name)
                .update(email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
