<%--
  Created by IntelliJ IDEA.
  User: kbual
  Date: 11/6/2023
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Office Management</title>
</head>
<body>
    <div class="container">
        <div class="row"><h2>Office Management</h2></div>
        <hr>
            <div class="row">
                <h3>Find By City or Country</h3>
            </div>
            <div class="row">
                <form action="office-management" method="get">
                    <label><b>Select By:</b></label>
                        <select name="officeList" id="officeList">
                            <option value="all">ALL</option>
                                <optgroup label="Country">
                                    <c:forEach items="${uniqueCountry}" var="country">
                                            <c:if test="${country != null}">
                                                    <option value="${country}">${country}</option>
                                            </c:if>
                                    </c:forEach>
                                </optgroup>

                                <optgroup label="City">
                                    <c:forEach items="${uniqueCity}" var="city">
                                    <c:if test="${city != null}">
                                            <option value="${city}">${city}</option>
                                    </c:if>
                                    </c:forEach>
                                </optgroup>
                        </select>
                    <input type="submit" value="FIND">
                </form>
            </div>

            <div class="row">
                <div class="card-deck">
                    <c:forEach items="${offices}" var="office">
                        <form action="office-management" method="post">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="removeOffice" value="${office.officeCode}">
                                    <input type="submit" value="REMOVE">
                                    <h5 class="card-title">${office.officeCode}</h5>
                                    <p class="card-text">Country: ${office.country}</p>
                                    <p class="card-text">City: ${office.city}</p>
                                    <p class="card-text">Territory: ${office.territory}</p>
                                </div>
                            </div>
                        </form>
                    </c:forEach>
                </div>
                <hr>
            </div>



        <div class="row"><h2>Add Office</h2></div>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form action="office-management" method="post">
                    <input type="hidden" name="action" value="add">
                    <div class="form-group">
                        <label for="officeCode">Code:</label>
                        <input type="text" id="officeCode" name="officeCode" class="form-control">
                    </div>
                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="city">City:</label>
                                <input type="text" id="city" name="city" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="phone">Phone Number:</label>
                                <input type="text" id="phone" name="phone" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addressLine1">Address Line 1:</label>
                        <input type="text" id="addressLine1" name="addressLine1" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="addressLine2">Address Line 2:</label>
                        <input type="text" id="addressLine2" name="addressLine2" class="form-control">
                    </div>
                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="state">State:</label>
                                <input type="text" id="state" name="state" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="country">Country:</label>
                                <input type="text" id="country" name="country" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="postalCode">Postal Code:</label>
                                <input type="text" id="postalCode" name="postalCode" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="territory">Territory:</label>
                                <input type="text" id="territory" name="territory" class="form-control">
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">ADD</button>
                </form>
                <h5 class="mt-3">${alert}</h5>
            </div>
        </div>
        <hr>

    <%--        <div class="row"><h2>Update Office</h2></div>--%>
<%--        <div class="row">--%>
<%--            <form action="office-update" method="post">--%>
<%--            <label>Update Office Country or City</label>--%>
<%--            <input type="text" name="officeCode">--%>
<%--                <input type="submit" value="SUBMIT">--%>
<%--            </form>--%>
<%--        </div>--%>
<%--        <c:if test="${message != null}">--%>
<%--        <div class="row"><p> ${message}</p></div>--%>
<%--        </c:if>--%>
<%--        </div>--%>


    </div>
</body>
</html>
