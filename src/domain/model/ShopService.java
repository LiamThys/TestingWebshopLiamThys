package domain.model;

import domain.db.PersonDb;
import domain.db.PersonDbSql;
import domain.db.ProductDb;
import domain.db.ProductDbSql;

import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDb personDb;
    private ProductDb productDb;

    public ShopService(Properties properties) {
        personDb = new PersonDbSql(properties);
        productDb = new ProductDbSql(properties);
    }

    public Person getPerson(String personId) {
        return getPersonDb().get(personId);
    }
    public Product getProduct(int id){ return getProductDb().get(id); }

    public List<Person> getPersons() {
        return getPersonDb().getAll();
    }
    public List<Product> getProducts() { return getProductDb().getAll(); }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }
    public void addProduct(Product product) { getProductDb().add(product); }

    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }
    public void updateProduct(Product product) { getProductDb().update(product); }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }
    public void deleteProduct(int id) { getProductDb().delete(id);}

    private PersonDb getPersonDb() {
        return personDb;
    }
    private ProductDb getProductDb() { return productDb; }
}
