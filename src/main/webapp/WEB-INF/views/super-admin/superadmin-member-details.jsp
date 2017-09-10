<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Member Details</title>
  <%@include file="../comman-to-all/comman-css-files-links.jsp"%>
</head>
<body>
<div class="wrapper">

  <%@include file="superadmin-menu-sidemenu.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>${memberMealCoupen.member.userProfile.fullName}'s<small> <i class='fa fa-chevron-right mlr_10px'></i> Details</small></h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Mess</li>
        <li class="active">Members</li>
        <li class="active">Member Details</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">

          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="../../resources/dist/img/user.png" alt="User profile picture">

              <h3 class="profile-username text-center">${memberMealCoupen.member.userProfile.fullName}</h3>

              <p class="text-muted text-center">${mess.messName}</p>

              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Email</b> <a class="pull-right">${memberMealCoupen.member.email}</a>
                </li>
                <li class="list-group-item">
                  <b>Mobile Number</b> <a class="pull-right">${memberMealCoupen.member.mobileNumber}</a>
                </li>
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
          
          <div class="box box-primary">
            <div class="box-body box-profile">

              <h3 class="profile-username text-center"> Meal Coupen Info </h3>

              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Status</b> <a class="pull-right"><span class="label label-${memberMealCoupen.status == 'ACTIVE' ? 'success' : 'danger'}">${memberMealCoupen.status}</span></a>
                </li>
                <li class="list-group-item">
                  <b>Amount</b> <a class="pull-right"><i class="fa fa-inr"></i> ${memberMealCoupen.mealCoupen.amount}</a>
                </li>
                <li class="list-group-item">
                  <b>Purchased Date</b> <a class="pull-right"><fmt:formatDate pattern = "dd-MMM-yyyy" value = "${memberMealCoupen.mealCoupen.createdUpdated.createdAt}" /></a>
                </li>
                <li class="list-group-item">
                  <b>Expires On </b> <a class="pull-right"><fmt:formatDate pattern = "dd-MMM-yyyy" value = "${memberMealCoupen.expiryDate}" /></a>
                </li>
                <li class="list-group-item">
                  <b>Total Meals</b> <a class="pull-right">${memberMealCoupen.noOfMeals}</a>
                </li>
                <li class="list-group-item">
                  <b>Redeemed Meals </b> <a class="pull-right">${memberMealCoupen.noOfMeals - memberMealCoupen.remainingMealCount}</a>
                </li>
                <li class="list-group-item">
                  <b>Available Meals </b> <a class="pull-right">${memberMealCoupen.remainingMealCount}</a>
                </li>
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
          
          <div class="box box-primary">
            <div class="box-body box-profile">

              <h3 class="profile-username text-center"> About Mess </h3>

              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Name</b> <a class="pull-right">${mess.messName}</a>
                </li>
                <li class="list-group-item">
                  <b>Owner</b> <a class="pull-right">${mess.messOwner.userProfile.fullName}</a>
                </li>
                <li class="list-group-item">
                  <b>Contact Email</b> <a class="pull-right">${mess.messOwner.email}</a>
                </li>
                <li class="list-group-item">
                  <b>Contact Number</b> <a class="pull-right">${mess.messOwner.mobileNumber}</a>
                </li>
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
 <%@include file="../comman-to-all/comman-js-files-links.jsp"%>
 
</div>
<!-- ./wrapper -->
<script>
  $(function () {
    var messMemberTable = $('#example').DataTable({
      "processing": true,
      "ajax": "mess-members/list?messId=" + $('#messId').val(),
      "columns": [
                  { "data": "userIdPk", "visible": false },
                  { "data": null, "orderable": false, "width": "10%"},
                  { "data": "userProfile.fullName" },
                  { "data": "mobileNumber" },
                  { "data": "email" },
                  { "data": null, "orderable": false, "width": "10%"}
              ],
       "columnDefs": [ {
           "targets": -1,
           "data": null,
           "className": 'text-center',
           "defaultContent": "<i id='btn-1' data-toggle='tooltip' title='Details' role='button' class='fa fa-info-circle gi-1_3x'></i>"
       } ]
    });
    
    messMemberTable.on( 'order.dt search.dt', function () {
    	messMemberTable.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        });
    }).draw();
    
    $('#example tbody').on( 'click', 'i', function () {
        var data = messMemberTable.row( $(this).parents('tr') ).data();
        
		if(this.id == 'btn-1') {
			window.location = 'member-details?memberId=' + data.userIdPk;
		}        
    } );
  
  });
</script>

</body>
</html>
