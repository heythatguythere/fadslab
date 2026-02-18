package klh.edu.Lab2HibernateCRUD;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {
    public static void main(String[] args) {
        // Create SessionFactory [cite: 324-328]
        SessionFactory factory = new Configuration()
            .configure()
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();

        // 1. Insert Data [cite: 329-331]
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        session.persist(new Product(1,"Pen","Stationery",10,50)); // [cite: 333]
        session.persist(new Product(2,"Notebook","Stationery",50,30)); // [cite: 334]
        session.persist(new Product(3,"Mouse","Electronics",500,10)); // [cite: 335]
        
        tx.commit();
        session.close();

        // 2. Run HQL Operations [cite: 341-342]
        Session session2 = factory.openSession();
        
        // Sorting by price [cite: 343-345]
        List<Product> list1 = session2
            .createQuery("FROM Product ORDER BY price ASC", Product.class)
            .list();
            
        System.out.println("\nPrice Ascending:");
        list1.forEach(p -> System.out.println(p.getName() + " " + p.getPrice())); // [cite: 347-348]

        session2.close();
        factory.close();
    }
}