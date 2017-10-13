<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
  <link rel="icon" href="../../resources/dist/img/favicon.png" type="image/gif" sizes="16x16">

  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="../../resources/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../../resources/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="../../resources/dist/css/skins/_all-skins.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="../../resources/plugins/iCheck/flat/blue.css">
  <!-- Morris chart -->
  <link rel="stylesheet" href="../../resources/plugins/morris/morris.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="../../resources/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <!-- Date Picker -->
  <link rel="stylesheet" href="../../resources/plugins/datepicker/datepicker3.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="../../resources/plugins/daterangepicker/daterangepicker.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link rel="stylesheet" href="../../resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="../../resources/plugins/datatables/dataTables.bootstrap.css">

  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  
  <style>
  
   	body { 
	  font-family: 'Montserrat', sans-serif;
	}
	
  	.modal-content {
	    -webkit-border-radius: 5px;
	    -moz-border-radius: 5px;
	    border-radius: 5px; 
	    text-align: center;
	}  
		
	.modal-footer {
		text-align: center;
	}
	
	.modal-footer .btn {
		min-width: 100px;
	}

	.b_danger .modal-content {
		color: #fff !important;
		background-color: #dd4b39  !important;
	}
	
	.b_alert .modal-content {
		color: #fff !important;
		background-color: #f39c12 !important;
	}
	
	.b_alert .btn, .b_danger .btn {
		background: transparent;
		border-color: white;
	}
	
	.b_alert .btn:hover, .b_alert .btn:active, .b_alert .btn:hover {
		background-color: #db8b0b !important;
		border-color: #e4dede;
	}
	
	.b_danger .btn:hover, .b_danger .btn:active, .b_danger .btn:hover {
		background-color: #d33724 !important;
	}
	
	.b_success .btn:hover, .b_success .btn:active, .b_success .btn:hover {
		background-color: #008d4c !important;
	}
	
	.bootbox .model-footer {
		border-top-color: 1px solid #c87f0a !important;
	}
	
	.gi-1_3x { 
		font-size: 1.3em; 
		margin: 0 6px;
	}
	
	.mlr_10px { 
		margin: 0 10px;
	}
	
	.error {
		color:red;
	}
  </style>

  