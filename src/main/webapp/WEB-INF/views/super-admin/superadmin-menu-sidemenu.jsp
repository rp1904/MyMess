
<%@ page import="com.byb.bhojan.util.ProjectConstant" %>

<style>
	.sidebar-username {
	    color: white;
	    font-size: 20px;
	    font-weight: 500;
	    text-align: center;
	    margin-bottom: -5%;
	}
</style>

  <header class="main-header">
    <!-- Logo -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>M</b>M</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b><%=ProjectConstant.PROJECT_NAME%></b> <small>SUPERADMIN</small></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <li id="logout">
	        <a href="#">
	            <i class="glyphicon glyphicon-log-out"></i> &nbsp; <span>Logout</span>
            </a>
          </li>
        </ul>
      </div>
    
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="sidebar-username">
          <p>Admin</p>
        </div>
      </div>
 
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
      
      	<li class="header"></li>
      	
      	<li><a href="mess"><i class="fa fa-home"></i> <span>Home</span></a></li>
        <li><a href="mess"><i class="fa fa-cutlery"></i> <span>Mess</span></a></li>
        <li><a href="payment-request"><i class="fa fa-inr"></i> <span>Payment Requests</span></a></li>
        <li><a href="payment"><i class="fa fa-history"></i> <span>Payment History</span></a></li>
        <li class="header"></li>
        <li><a href="payment"><i class="fa fa-gears"></i> <span>Admin Settings</span></a></li>
        <li class="header"></li>
        <li><a href="payment"><i class="fa fa-edit"></i> <span>Privacy Policy</span></a></li>
        <li><a href="payment"><i class="fa fa-info-circle"></i> <span>About Us</span></a></li>
        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

