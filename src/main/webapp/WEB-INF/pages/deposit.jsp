<!DOCTYPE HTML>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html >




     <c:url value="/login?command=logout" var="logoutUrl"/>
    <c:url value="/wallet?command=towallet" var="walletUrl"/>
    <c:url value="/order?command=toorders" var="toorderUrl"/>
    <c:url value="/market?command=tomarket" var="marketUrl"/>
    <c:url value="/wallet?command=deposit" var="depositUrl"/>

<head>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        <%@ include file="/css/style.css" %>
    </style>
<title>Deposit</title>
</head>
<body>
<H2>${usertext} ${user.userName} ${deposit}</H2>


<div class="container"  style="background-color:#f1f1f1;  width: 100%; top: 5%; left: 0%">


    <FORM action="${marketUrl}" method="post" style="float: left; padding: 12px 20px; ">
        <BUTTON type="submit">${market}</BUTTON>
    </FORM>


    <FORM action="${walletUrl}" method="post" style="float: left;  padding: 12px 20px;"  >
        <BUTTON type="submit">${mywallet}</BUTTON>
    </FORM>

    <FORM action="${toorderUrl}" method="post" style="float: left;padding: 12px 20px; ">
        <BUTTON type="submit" >${myorders}</BUTTON>
    </FORM>

    <FORM action="${logoutUrl}" method="post" style="float: left; padding: 12px 20px; ">
        <BUTTON type="submit">${logout}</BUTTON>
    </FORM>


</div>
<div class="container"  style="background-color:#f1f1f1;  left: 35%;">
    <FORM action="${depositUrl}" method="post">

        <INPUT type="text" id="amount" name="amount" placeholder=${amount}>

        <BUTTON type="submit">${deposit} ${coin} </BUTTON>
    </FORM>
    <c:set var="depositerror">${transactionerror}</c:set>
    <H2 style="color: #222222">${transactionError[depositerror]}</H2>


</div>

 <footer style ="color : white;
                  position: absolute;
                  bottom: 0;
                  height: 58px;">
   ${buildVersion}   ${buildDate}
  </footer>


</body>
</html>