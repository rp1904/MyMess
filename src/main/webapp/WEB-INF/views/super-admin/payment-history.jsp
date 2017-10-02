<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Payment History</title>
  <%@include file="../comman-to-all/comman-css-files-links.jsp"%>
</head>
<body>
<div class="wrapper">

  <%@include file="superadmin-menu-sidemenu.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>Payment History</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Payment History</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
        
        <c:if test="${not empty error}">
	        <div class="alert alert-danger" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Error!</strong> ${error_msg}
			</div>
        </c:if>
          <div class="box">
            <div class="box-body">
            
              <table id="paymentHistory" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Log Id</th>
                  <th>Sr No</th>
                  <th>Mess Name</th>
                  <th>Transaction Id</th>
                  <th>Amount Paid</th>
                  <th>Instamojo Fees</th>
                  <th>Amount Received</th>
                  <th>Status</th>
                  <th>Date</th>
                </tr>
                </thead>
              </table>
              <hr>
<!--               <form id="make_payment_req_form" action="#" method="POST" class="form-horizontal"> -->
<!--               	<input type="hidden" id="paymentDetailList" name="paymentDetailList"> -->
<!--               	<div class="form-group col-offset-1 col-sm-6"> -->
<!-- 	              	<button id="make_payment_req_form_btn" class="btn btn-success">Submit</button> -->
<!--               	</div> -->
<!--               </form> -->
              
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
$(document).ready(function() {

    var paymentHistoryTable = $('#paymentHistory').DataTable({
      "processing": true,
      "ajax": "payment-history/list",
      "order": [[ -1, "desc" ]],
      "columns": [
                  { "data": "logId", "visible": false },
                  { "data": null, "orderable": false, "width": "7%"},
                  { "data": "messName" },
                  { "data": "transactionId" },
                  { "data": "amountPaid" },
                  { "data": "fees" },
                  { "data": "amountReceived" },
                  { "data": "status" },
                  { "data": "purchasedDateTime" }
              ]
    });
    
    paymentHistoryTable.on( 'order.dt search.dt', function () {
    	paymentHistoryTable.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        });
    }).draw();
  
  });
</script>


</body>
</html>
