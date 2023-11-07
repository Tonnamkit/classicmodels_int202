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

import static sit.int202.classicmodels.utill.Utill.isNullOrEmpty;

@WebServlet(name = "OfficeUpdateServlet", value = "/093/office-management")
public class OfficeUpdateServlet extends HttpServlet {
    private OfficeRepository officeRepository;
    @Override
    public void init() throws ServletException {
        officeRepository = new OfficeRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String action = request.getParameter("action");
        if (action.equals("add")){
            handleAdd(request,response);
        } else if (action.equals("update")) {
            handleUpdate(request,response);
        } else if (action.equals("delete")) {
            handleDelete(request,response);
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    private void handleAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String officeCode = request.getParameter("removeOffice");
        if (officeCode != null){
            Office office = officeRepository.find(officeCode);
            officeRepository.delete(office);
        }
        response.sendRedirect(request.getContextPath() + "/093/office-management?officeList=all");
    }
}
 
