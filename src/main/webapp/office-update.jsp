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
    <title>Office Update</title>
</head>
<body>
    <div class="container">
        <div class="row"><h2>Find By City or Country</h2></div>
        <div class="row">
            <form action="office-update" method="get">
                <label>Select By:</label>
              <select name="officeList" id="officeList">

                  <optgroup label="Country">
                 <c:forEach items="${officeList}" var="office">
                  <c:if test="${office != null}">

                  <option value="${office.country}">${office.country}</option>
                  </c:if>
                  </c:forEach>

                  <optgroup label="City">
                  <c:forEach items="${officeList}" var="office">
                  <c:if test="${office != null}">
                      <option value="${office.city}">${office.city}</option>
                      </c:if>
                      </c:forEach>
            </select>

                <input type="submit" value="FIND">
            </form>
        </div>
        <div class="row">
            <c:forEach items="${offices}" var="officect">
               ${officect.officeCode} - ${officect.country} - ${officect.city} - ${officect.territory} <br>

            </c:forEach>
        <hr>
<%--        <div class="row"><h2>Update Office</h2></div>--%>
<%--        <div class="row">--%>
<%--            <form action="office-update" method="post">--%>
<%--            <label>Input Country or City</label>--%>
<%--            <input type="text" name="uinput">--%>
<%--                <input type="submit" value="SUBMIT">--%>
<%--            </form>--%>
<%--        </div>--%>
<%--        <c:if test="${message != null}">--%>
<%--        <div class="row"><p> ${message}</p></div>--%>
<%--        </c:if>--%>
    </div>
</body>
</html>
