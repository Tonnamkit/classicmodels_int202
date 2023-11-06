package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import sit.int202.classicmodels.entities.Employee;
import sit.int202.classicmodels.entities.Environment;
import sit.int202.classicmodels.entities.Office;

import java.util.List;

public class TestEntitiyRelationship {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        List<Office> officeList = em.createNamedQuery("OFFICE.FIND_ALL").getResultList();
        for (Office office : officeList) {
            System.out.printf("%4s %-15s %-15s %-25s\n",
                    office.getOfficeCode(), office.getCountry(),
                    office.getCity(), office.getAddressLine1());
            System.out.println("---------------------------------------");
            for (Employee employee:office.getEmployeeList()){
                System.out.printf("%4d %-15s %-15s %-20s\n",
                        employee.getId(),employee.getFirstName(),
                        employee.getLastName(),employee.getJobTitle());
            }
            System.out.println("\n\n");
        }
        em.close();
    }
}
