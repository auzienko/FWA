<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>FWA / Profile</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<H1>FWA / Profile</H1>
<table style="height: 288px; width: 60%; border-collapse: collapse;" border="0" align="center"
       style="margin: 0px auto;">
    <tbody>
    <tr style="height: 270px;">
        <td style="width: 50%; height: 270px;">
            <table style="height: 100%; width: 100%; border-collapse: collapse;" border="0" cellspacing="0"
                   cellpadding="0">
                <tbody>
                <tr>
                    <td style="width: 100%;">
                        <img src="${userAvatar}" alt="" width="300"/>
                    </td>
                </tr>
                <tr>
                    <td style="width: 100%;">
                        <form action="images" enctype="multipart/form-data"
                              method="post">
                            <input type="file" name="newAvatar" accept="image/*">
                            <button type="submit">Upload</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
        <td style="width: 50%; height: 270px;">
            <table style="height: 100%; width: 100%; border-collapse: collapse;" border="0" cellspacing="0"
                   cellpadding="0">
                <tbody>
                <tr>
                    <td style="width: 100%;">

                        <h1><c:out value="${sessionScope.firstName}"/> <c:out value="${sessionScope.lastName}"/></h1>
                        <p><c:out value="${sessionScope.email}"/></p>
                    </td>
                </tr>
                <tr>
                    <td style="width: 100%;">
                        <table style="width: 100%; border-collapse: collapse;" border="1">
                            <thead>
                            <tr>
                                <td style="width: 33.3333%;">Date</td>
                                <td style="width: 33.3333%;">Time</td>
                                <td style="width: 33.3333%;">IP</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${userAuthHistory}">
                                <tr>
                                    <td style="width: 33.3333%;">${item.toDateTimeString("MMMM dd, yyyy")}</td>
                                    <td style="width: 33.3333%;">${item.toDateTimeString("HH:mm")}</td>
                                    <td style="width: 33.3333%;">&nbsp;${item.getIp()}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
<table style="border-collapse: collapse; width: 60%; height: 54px;" border="1" align="center" style="margin: 0px auto;">
    <thead>
    <tr style="height: 18px;">
        <td style="width: 33.3333%; height: 18px;">File name</td>
        <td style="width: 33.3333%; height: 18px;">Size</td>
        <td style="width: 33.3333%; height: 18px;">MIME</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${userAvatarsHistory}">
        <tr>
            <td style="width: 33.3333%;"><a href="./images/${item.getId()}---${item.getFileName()}" target="_blank">${item.getFileName()}</a></td>
            <td style="width: 33.3333%;">${item.sizeToString()}</td>
            <td style="width: 33.3333%;">&nbsp;${item.getMime()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>