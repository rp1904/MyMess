<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Mess | All</title>
  <%@include file="../comman-to-all/comman-css-files-links.jsp"%>
</head>
<body>
<div class="wrapper">

  <%@include file="superadmin-menu-sidemenu.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>Mess</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Mess</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
        
        <c:if test="${not empty error}">
	        <div class="alert alert-danger" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Error!</strong> Invalid Mess Id !
			</div>
        </c:if>
          <div class="box">
            <div class="box-body">
              <table id="example" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Mess Id</th>
                  <th>Sr No</th>
                  <th>Name</th>
                  <th>Contact Number</th>
                  <th>Contact Email</th>
                  <th>Owner Name</th>
                  <th class="text-center">Action</th>
                </tr>
                </thead>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <form id="get_mess_members_form" action="mess-members" method="GET">
          		<input type="hidden" id='messId' name="messId"/>
          </form>
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
$(document).ready(function() {
// 	if(error === "invalid_id") {		
// 		$("#invalidid-error-alert").fadeTo(2000, 500).slideUp(500, function(){
// 		    $("#invalidid-error-alert").slideUp(2000);
// 		});
// 	}

	
    var messTable = $('#example').DataTable({
      "processing": true,
      "ajax": "mess/list",
      "columns": [
                  { "data": "messIdPk", "visible": false },
                  { "data": null, "orderable": false, "width": "10%"},
                  { "data": "messName" },
                  { "data": "messOwner.mobileNumber" },
                  { "data": "messOwner.email" },
                  { "data": "messOwner.userProfile.fullName" },
                  { "data": null, "orderable": false, "width": "10%"}
              ],
       "columnDefs": [ {
           "targets": -1,
           "data": null,
           "className": 'text-center',
           "defaultContent": "<i id='btn-3' data-toggle='tooltip' title='Members' role='button' class='fa fa-group gi-1_3x'></i>"
//         	   "<i id='btn-1' data-toggle='tooltip' title='Details' role='button' class='fa fa-info-circle gi-1_3x'></i>" + 
//                "<i id='btn-2' data-toggle='tooltip' title='Payouts' role='button' class='fa fa-rupee gi-1_3x'></i>" +
       } ]
    });
    
    messTable.on( 'order.dt search.dt', function () {
    	messTable.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        });
    }).draw();
    
    $('#example tbody').on( 'click', 'i', function () {
        
    	var data = messTable.row( $(this).parents('tr') ).data();
		if(this.id === 'btn-3') {
// 			window.location = 'mess-members?messId=' + data.messIdPk;
			$('#messId').val(data.messIdPk);
			$('#get_mess_members_form').submit();
		}        
    });
  
  });
</script>

</body>
</html>
