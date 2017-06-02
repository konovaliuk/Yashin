<%--
  Created by IntelliJ IDEA.
  User: andrew_yashin
  Date: 5/30/17
  Time: 00:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Date</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css"/>"/>
</head>
<body>
<form action="/logout" method="post" class="logout">
    <input type="submit" name="command" value="Log out">
</form>

<form action="/route" method="get" class="formdate">
    <select name="from">
        <c:forEach items="${cityFrom}" var="fromItem">
            <option value="${fromItem.id}" ${from == fromItem.id ? 'selected' : ''}>${fromItem.name}</option>
        </c:forEach>
    </select>

    <select name="to">
        <c:forEach items="${cityTo}" var="toItem">
            <option value="${toItem.id}" ${to == toItem.id ? 'selected' : ''}>${toItem.name}</option>
        </c:forEach>
    </select>
    <br/>
    Departure date from:
    <input type="date" name="date" value="${d}" required/>
    Departure time from:
    <select name="time">
        <option name="0" value="0" ${time == 0 ? 'selected' : ''}>00:00</option>
        <option name="1" value="1" ${time == 1 ? 'selected' : ''}>01:00</option>
        <option name="2" value="2" ${time == 2 ? 'selected' : ''}>02:00</option>
        <option name="3" value="3" ${time == 3 ? 'selected' : ''}>03:00</option>
        <option name="4" value="4" ${time == 4 ? 'selected' : ''}>04:00</option>
        <option name="5" value="5" ${time == 5 ? 'selected' : ''}>05:00</option>
        <option name="6" value="6" ${time == 6 ? 'selected' : ''}>06:00</option>
        <option name="7" value="7" ${time == 7 ? 'selected' : ''}>07:00</option>
        <option name="8" value="8" ${time == 8 ? 'selected' : ''}>08:00</option>
        <option name="9" value="9" ${time == 9 ? 'selected' : ''}>09:00</option>
        <option name="10" value="10" ${time == 10 ? 'selected' : ''}>10:00</option>
        <option name="11" value="11" ${time == 11 ? 'selected' : ''}>11:00</option>
        <option name="12" value="12" ${time == 12 ? 'selected' : ''}>12:00</option>
        <option name="13" value="13" ${time == 13 ? 'selected' : ''}>13:00</option>
        <option name="14" value="14" ${time == 14 ? 'selected' : ''}>14:00</option>
        <option name="15" value="15" ${time == 15 ? 'selected' : ''}>15:00</option>
        <option name="16" value="16" ${time == 16 ? 'selected' : ''}>16:00</option>
        <option name="17" value="17" ${time == 17 ? 'selected' : ''}>17:00</option>
        <option name="18" value="18" ${time == 18 ? 'selected' : ''}>18:00</option>
        <option name="19" value="19" ${time == 19 ? 'selected' : ''}>19:00</option>
        <option name="20" value="20" ${time == 20 ? 'selected' : ''}>20:00</option>
        <option name="21" value="21" ${time == 21 ? 'selected' : ''}>21:00</option>
        <option name="22" value="22" ${time == 22 ? 'selected' : ''}>22:00</option>
        <option name="23" value="23" ${time == 23 ? 'selected' : ''}>23:00</option>
    </select>
    <br/>
    <input type="submit" name="command" value="Select"/>

    <c:if test="${not empty noTrain}">
        <br>No trains on this route
    </c:if>
    <c:if test="${not empty trains}">
        <div class="table">
            <table class="simple-little-table">
                <tr>
                    <th>Number</th>
                    <th>From / To</th>
                    <th>Departure</th>
                    <th>Arrival</th>
                    <th>Seats Available</th>
                </tr>
                <c:forEach items="${trains}" var="train">
                    <tr>
                        <td>${train.train_id}</td>
                        <td>${train.fromCity} / ${train.toCity}</td>
                        <td>${train.fromDate}</td>
                        <td>${train.toDate}</td>
                        <td>
                            <select name="train${train.train_id}">
                                <option value="none"></option>
                                <c:if test="${train.compartment_free gt 0}">
                                    <option value="C">C (${train.compartment_free}) = ${train.compartment_price}
                                        UAH
                                    </option>
                                </c:if>

                                <c:if test="${train.berth_free gt 0}">
                                    <option value="B">B (${train.berth_free}) = ${train.berth_price} UAH</option>
                                </c:if>

                                <c:if test="${train.deluxe_free gt 0}">
                                    <option value="L">L (${train.deluxe_free}) = ${train.deluxe_price} UAH</option>
                                </c:if>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <input class="form" type="submit" name="command" value="Make tickets">
    </c:if>
</form>
</body>
</html>
