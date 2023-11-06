package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import sit.int202.classicmodels.entities.Employee;
import sit.int202.classicmodels.entities.Environment;
import sit.int202.classicmodels.entities.Office;

import java.util.List;
import java.util.Scanner;

public class TestQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.UNIT_NAME);
        EntityManager em = emf.createEntityManager();
//        Query query = em.createNamedQuery("OFFICE.FIND_ALL");

//        List<Office> officeList = query.getResultList();
//        for (Office office: officeList) {
//            System.out.printf("%4s %-15s %-15s %-25s\n",
//                    office.getOfficeCode(),office.getCountry(),
//                    office.getCity(),office.getAddressLine1());
//        }
//        System.out.println("--------------------------------------------------");
//
//        Query queryByCountry = em.createNamedQuery("OFFICE.FIND_BY_COUNTRY");
//        System.out.println("Find Office by Country Start with: ");
//        String country = new Scanner(System.in).next();
//        queryByCountry.setParameter("countryParam", country + "%");
//
//        List<Office> officeListByCountry = queryByCountry.getResultList();
//        for (Office office : officeListByCountry) {
//            System.out.printf("%4s %-10s %-15s %-25s\n",
//                    office.getOfficeCode(), office.getCountry(),
//                    office.getCity(), office.getAddressLine1());
//        }

        System.out.println("--------------------------------------------------");
        List<Employee> employeeList = em.createNamedQuery("EMPLOYEE.FIND_ALL").getResultList();
        for (Employee employee:employeeList){
            System.out.printf("%4d %-15s %-15s %-20s - %s\n",
                    employee.getId(),employee.getFirstName(),
                    employee.getLastName(),employee.getJobTitle(),
                    employee.getOffice().getCity());
        }
        em.close();

    }
}
