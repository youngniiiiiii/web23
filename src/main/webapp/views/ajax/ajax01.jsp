<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--자바스크립트내에서도 jsp 값을 끄집어 낼수 있따--%>
<script>
    let ajax01 = {
        init: function () {

            setInterval(function () {
                $.ajax({
                    url: '/getservertime',
                    success: function (data) {
                        ajax01.display(data);
                    },
                    error: function () {
                        alert('Error....');
                    }
                });
            }, 1000);
        },
        display: function (data) {
            $('#server_time').text(data);
        }
    };

    $(function () {
        ajax01.init();
    })

</script>

<div class="col-sm-8 text-left">
    <div class="container">
        <h3>AJAX01 Page</h3>
        <h4>Server Time</h4>
        <h5 id="server_time"></h5>
    </div>
</div>
