package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import sit.int202.classicmodels.entities.Employee;
import sit.int202.classicmodels.entities.Environment;

import java.util.List;
import java.util.Scanner;

public class TestPagging {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("EMPLOYEE.FIND_ALL");
        int start = 0;
        int pageSize = 5;
        query.setMaxResults(pageSize);
        Scanner sc = new Scanner(System.in);
        while (true){
            query.setFirstResult(start);
            List<Employee> employees = query.getResultList();
            if (employees.isEmpty()){
                break;
            }
            start += pageSize;
            displayEmps(employees);
            System.out.println("Press any key to Display more");
            sc.nextLine();
        }
        em.close();
    }

    private static void displayEmps(List<Employee> employeeList){
        for (Employee employee:employeeList){
            System.out.printf("%4d %-15s %-15s %-20s\n",
                    employee.getId(),employee.getFirstName(),
                    employee.getLastName(),employee.getJobTitle());
        }
        System.out.println("-----------------");
    }
}
