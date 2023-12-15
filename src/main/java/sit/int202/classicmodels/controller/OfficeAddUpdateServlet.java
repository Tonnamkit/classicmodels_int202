package sit.int202.classicmodels.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.classicmodels.entities.Office;
import sit.int202.classicmodels.repositories.OfficeRepository;

import java.io.IOException;

import static sit.int202.classicmodels.utill.Utill.isNullOrEmpty;

@WebServlet(name = "OfficeAddUpdateServlet", value = "/093/office-form")
public class OfficeAddUpdateServlet extends HttpServlet {
    private OfficeRepository officeRepository;
    @Override
    public void init() throws ServletException {
        officeRepository = new OfficeRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("statusMsg", null);

        String add = request.getParameter("insert");
        if (add != null) {
            request.setAttribute("add", add);
        } else {
            String officeCode = request.getParameter("editOffice");
            Office office = officeRepository.find(officeCode);
            if (office != null) {
                setOfficeAttributes(request, office);
            }
        }

        request.getRequestDispatcher("/office-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            handleAdd(request, response);
        } else if ("update".equals(action)) {
            handleUpdate(request, response);
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String officeCode = request.getParameter("editOffice");
        String message;

        Office updateOffice = officeRepository.find(officeCode);
        if (updateOffice != null) {
            updateOffice.setOfficeCode(officeCode);
            updateOffice.setCity(request.getParameter("city"));
            updateOffice.setPhone(request.getParameter("phone"));
            updateOffice.setAddressLine1(request.getParameter("addressLine1"));
            updateOffice.setAddressLine2(request.getParameter("addressLine2"));
            updateOffice.setState(request.getParameter("state"));
            updateOffice.setCountry(request.getParameter("country"));
            updateOffice.setPostalCode(request.getParameter("postalCode"));
            updateOffice.setTerritory(request.getParameter("territory"));

            if (officeRepository.update(updateOffice)) {
                message = "Office Update Successfully";
            } else {
                message = "Fail To Update";
            }
        } else {
            message = "Office Not Found";
        }

        request.setAttribute("statusMsg", message);
        request.getRequestDispatcher("/office-form.jsp").forward(request, response);
    }

    private void handleAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String officeCode = request.getParameter("officeCode");
        String message;

        if (isNullOrEmpty(officeCode) || isNullOrEmpty(request.getParameter("city")) ||
                isNullOrEmpty(request.getParameter("phone")) || isNullOrEmpty(request.getParameter("addressLine1")) ||
                isNullOrEmpty(request.getParameter("country")) || isNullOrEmpty(request.getParameter("postalCode")) ||
                isNullOrEmpty(request.getParameter("territory"))) {
            message = "Missing Input";
        } else {
            Office newOffice = createOfficeFromRequest(request);

            if (officeRepository.insert(newOffice)) {
                message = "Add Successfully";
            } else {
                message = "Fail to Add Office";
            }
        }

        request.setAttribute("statusMsg", message);
        request.getRequestDispatcher("/office-form.jsp").forward(request, response);
    }

    private void setOfficeAttributes(HttpServletRequest request, Office office) {
        request.setAttribute("city", office.getCity());
        request.setAttribute("phone", office.getPhone());
        request.setAttribute("addressLine1", office.getAddressLine1());
        request.setAttribute("addressLine2", office.getAddressLine2());
        request.setAttribute("state", office.getState());
        request.setAttribute("country", office.getCountry());
        request.setAttribute("postalCode", office.getPostalCode());
        request.setAttribute("territory", office.getTerritory());
    }

    private Office createOfficeFromRequest(HttpServletRequest request) {
        Office newOffice = new Office();
        newOffice.setOfficeCode(request.getParameter("officeCode"));
        newOffice.setCity(request.getParameter("city"));
        newOffice.setPhone(request.getParameter("phone"));
        newOffice.setAddressLine1(request.getParameter("addressLine1"));
        newOffice.setAddressLine2(request.getParameter("addressLine2"));
        newOffice.setState(request.getParameter("state"));
        newOffice.setCountry(request.getParameter("country"));
        newOffice.setPostalCode(request.getParameter("postalCode"));
        newOffice.setTerritory(request.getParameter("territory"));
        return newOffice;
    }
}
 
