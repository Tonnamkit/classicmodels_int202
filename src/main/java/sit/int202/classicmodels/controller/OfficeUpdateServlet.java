package sit.int202.classicmodels.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.classicmodels.entities.Office;
import sit.int202.classicmodels.repositories.OfficeRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "OfficeUpdateServlet", value = "/093/office-management")
public class OfficeUpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OfficeRepository officeRepository = new OfficeRepository();
        List<Office> officeList = officeRepository.findAll();
        Set<String> uniqueSetCountry = new HashSet<>();
        Set<String> uniqueSetCity = new HashSet<>();
        if (officeList != null){
            for (Office office: officeList) {
                uniqueSetCountry.add(office.getCountry());
                uniqueSetCity.add(office.getCity());
            }
        }
        request.setAttribute("uniqueCountry",uniqueSetCountry);
        request.setAttribute("uniqueCity",uniqueSetCity);
        request.setAttribute("officeList",officeList);
        String findVal = request.getParameter("officeList");
        if (findVal != null){
            if(!findVal.equals("all")){
                List<Office> offices = officeRepository.findByCityOrCountry(findVal);
                request.setAttribute("offices",offices);
            }else {
                request.setAttribute("offices", officeList);
            }
        }
        request.getRequestDispatcher("/office-management.jsp").forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OfficeRepository officeRepository = new OfficeRepository();
        String countryOrCity = request.getParameter("uinput");
        if (countryOrCity != null) {
            List<Office> officeList = officeRepository.findByCityOrCountry(countryOrCity);
            String message;
            if (officeList != null && !officeList.isEmpty()){
                for (Office office: officeList) {
                    office.setCountry(countryOrCity);
                    officeRepository.insert(office);
                }
                message = "Offices Update Successfully.";
            }else {
                message = "No Offices Found.";
            }
            request.setAttribute("message",message);
        }
        officeRepository.close();
        request.getRequestDispatcher("/office-management.jsp").forward(request,response);
    }
}
 
