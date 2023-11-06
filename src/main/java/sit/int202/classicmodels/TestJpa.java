package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sit.int202.classicmodels.entities.Employee;
import sit.int202.classicmodels.entities.Environment;
import sit.int202.classicmodels.entities.Office;

public class TestJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        Office office = em.find(Office.class,"8");
        Employee emp = em.find(Employee.class,1002);
//        if (!emp.getFirstName().equalsIgnoreCase("somchai")){
//            em.getTransaction().begin();
//            emp.setFirstName("Somchai");
//            em.persist(emp);
//            em.getTransaction().commit();
//        }
        System.out.println(emp);
        if (office != null)
        System.out.printf("%s %s %s\n",office.getCity(),office.getAddressLine1(),office.getAddressLine2());
        else System.out.println("Not found");
//
//        Office newOffice = new Office();
//        newOffice.setOfficeCode("8");
//        newOffice.setAddressLine1(office.getAddressLine1());
//        newOffice.setAddressLine2(office.getAddressLine2());
//        newOffice.setCity(office.getCity());
//        newOffice.setPhone(office.getPhone());
//        newOffice.setPostalCode(office.getPostalCode());
//        newOffice.setTerritory(office.getTerritory());
//        newOffice.setState(office.getState());
//        newOffice.setCountry(office.getCountry());
//        office = em.find(Office.class,"8");
//        if (office != null) {
//            em.getTransaction().begin();
//            em.remove(office);
//            //em.persist(newOffice);
//            em.getTransaction().commit();
//        }
//        em.close();
    }
}