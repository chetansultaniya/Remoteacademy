<!DOCTYPE html>
<html lang="en">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL"
value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">

<title>Remote Academy</title>

<link rel="stylesheet" href="${baseURL}/dist/css/glyphicon.css">
<link rel="stylesheet" href="${baseURL}/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${baseURL}/dist/css/style.css">
<script type="text/javascript" src="${baseURL}/dist/js/jquery.js"></script>
<script type="text/javascript" src="${baseURL}/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${baseURL}/dist/js/Remote.js"></script>
</head>

<body>
