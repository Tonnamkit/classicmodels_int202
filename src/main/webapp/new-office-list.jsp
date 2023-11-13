<%--
  Created by IntelliJ IDEA.
  User: kbual
  Date: 11/6/2023
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row bg-primary">
        <h2>Classic Model Offices ::</h2>
    </div>
    <div class="row">
        <c:forEach items="${offices}" var="office">
            <div class="col-2 border border-secondary p-2 m-2
               ${office.officeCode == selectedOffice.officeCode ? 'bg-warning' : ''}"><div>
                <a href="javascript:loadOffice(`${office.officeCode}`)">
                        ${office.city}</a>, ${office.country}</div>
                <div> ${office.phone}</div>
            </div>
        </c:forEach>
    </div>
    <br>
        <div class="row bg-light">
            <b>Employees ::</b>
        </div>
        <div class="row">
            <c:forEach items="${selectedOffice.employeeList}" var="employee">
                <div class="col-2 pl-2 m-2 border border-secondary rounded-pill">
                    <div> ${employee.firstName}
                            ${employee.lastName} - ${employee.jobTitle}
                    </div>
                </div>
            </c:forEach>
        </div>
</div>

