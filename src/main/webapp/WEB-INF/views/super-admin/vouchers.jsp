<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Vouchers</title>
  <%@include file="../comman-to-all/comman-css-files-links.jsp"%>
</head>
<body>
<div class="wrapper">

  <%@include file="superadmin-menu-sidemenu.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>Vouchers</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Vouchers</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
        
        <c:if test="${not empty type}">
	        <div class="alert alert-${type}" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>${message}</strong>
			</div>
        </c:if>
          
          <div class="box">
            <div class="box-body">
              <table id="voucherTable" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Voucher Id</th>
                  <th>Sr No</th>
                  <th>Voucher Name</th>
                  <th>Days</th>
                  <th>Amount</th>
                  <th>Discount</th>
                  <th>Action</th>
                </tr>
                </thead>
              </table>
            </div>
           
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

    var voucherTable = $('#voucherTable').DataTable({
      "processing": true,
      "ajax": "vouchers/list",
      "columns": [
                  { "data": "messPaymentVoucherId", "visible": false },
                  { "data": null, "orderable": false, "width": "10%"},
                  { "data": "name" },
                  { "data": "days" },
                  { "data": "amount" },
                  { "data": "discount" },
                  { "data": null, "orderable": false, "width": "10%"}
              ],
       "columnDefs": [ {
           "targets": -1,
           "data": null,
           "className": 'text-center',
           "defaultContent": "<i id='btn-1' data-toggle='tooltip' title='Edit Coupen' role='button' class='fa fa-edit gi-1_3x'></i>" 
       } ]
    });
    
    voucherTable.on( 'order.dt search.dt', function () {
    	voucherTable.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        });
    }).draw();
    
    $('#voucherTable tbody').on( 'click', 'i', function () {
        
    	var data = voucherTable.row( $(this).parents('tr') ).data();
		if(this.id === 'btn-1') {
			console.log(data);
			$('#myModalHorizontal').modal('toggle');

		}        
    });
  
  
});
</script>

</body>
</html>

<!-- Modal -->
<div class="modal fade" id="myModalHorizontal" tabindex="-1" role="dialog" 
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <button type="button" class="close" 
                   data-dismiss="modal">
                       <span aria-hidden="true">&times;</span>
                       <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    Add New Voucher
                </h4>
            </div>
            
            <!-- Modal Body -->
            <div class="modal-body">
                
                <form:form class="form-horizontal" role="form" modelAttribute="voucher" method="POST" action="#">
                  
                  <form:hidden path="messPaymentVoucherId"/>
                  
                  <div class="form-group">
                    <form:label  class="col-sm-3 control-label" path="name">Voucher Name</form:label>
                    <div class="col-sm-7">
                        <form:input path="name"  tabindex="1" class="form-control"/>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <form:label  class="col-sm-3 control-label" path="amount">Amount</form:label>
                    <div class="col-sm-7">
                        <form:input path="amount" tabindex="1" class="form-control"/>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <form:label  class="col-sm-3 control-label" path="days">Days</form:label>
                    <div class="col-sm-7">
                        <form:input path="days"  tabindex="1" class="form-control"/>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <form:label  class="col-sm-3 control-label" path="discount">Discount</form:label>
                    <div class="col-sm-7">
                        <form:input path="discount"  tabindex="1" class="form-control"/>
                    </div>
                  </div>
                  
                  
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button type="submit" class="btn btn-success">Add</button>
                    </div>
                  </div>
                </form:form>
            </div>
            
            <!-- Modal Footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                   Close
                </button>
             
</div>
</div>
</div>
</div>