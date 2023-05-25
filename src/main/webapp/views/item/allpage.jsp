<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-8 text-left">
    <div class="container">
        <div class="row content">
            <div class="col-sm-6 text-left">
                <h3>Item All Page</h3>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>IMG</th>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>PRICE</th>
                        <th>REGDATE</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="obj" items="${cpage.getList()}">
                        <tr>
                            <td><img class="medium_img" src="/uimg/${obj.imgname}"></td>
                            <td><a href="/item/get?id=${obj.id}">${obj.id}</a></td>
                            <td>${obj.name}</td>
                            <td><fmt:formatNumber value="${obj.price}" pattern="###,###원"/></td>
                            <td><fmt:formatDate value="${obj.rdate}" pattern="dd-MM-yyyy"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="../page.jsp"/>
            </div>
        </div>
    </div>
</div>
<style>
    .medium_img {
        width: 80px;
        height: 80px;
    }
</style>