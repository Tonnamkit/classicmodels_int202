package sit.int202.classicmodels.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.classicmodels.entities.Office;
import sit.int202.classicmodels.repositories.OfficeRepository;

import java.io.IOException;

@WebServlet(name = "OfficeAddServlet", value = "/093/office-add")
public class OfficeAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OfficeRepository officeRepository = new OfficeRepository();
        String officeCode = request.getParameter("officeCode");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String postalCode = request.getParameter("postalCode");
        String territory = request.getParameter("territory");
        if (isNullOrEmpty(officeCode) || isNullOrEmpty(city) || isNullOrEmpty(phone) ||
                isNullOrEmpty(addressLine1) || isNullOrEmpty(country) || isNullOrEmpty(postalCode) ||
                isNullOrEmpty(territory)) {
            request.setAttribute("alert", "Missing Input");
        }else{
            Office newOffice = new Office();
            newOffice.setOfficeCode(officeCode);
            newOffice.setCity(city);
            newOffice.setPhone(phone);
            newOffice.setAddressLine1(addressLine1);
            newOffice.setAddressLine2(addressLine2);
            newOffice.setState(state);
            newOffice.setCountry(country);
            newOffice.setPostalCode(postalCode);
            newOffice.setTerritory(territory);

            if (officeRepository.insert(newOffice)){
                request.setAttribute("alert", "Add Successfully");
            }else{
                request.setAttribute("alert", "Fail to Add Office");
            }
        }
        request.getRequestDispatcher("/office-management.jsp").forward(request,response);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
 
