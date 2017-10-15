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
        
	        <div id="alert-div" class="alert fade out hidden" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong id="alert-msg"></strong>
			</div>
          
          <div class="box">
	        <div class="box-header">
	        	<button type="button" class="btn btn-warning pull-right" id="addNewVoucher">
	        		<i class="fa fa-plus-circle"></i>&nbsp;&nbsp; Add New Voucher
	        	</button>
	        </div>
            <div class="box-body">
              <table id="voucherTable" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Voucher Id</th>
                  <th>Sr No</th>
                  <th>Voucher Name</th>
                  <th>Days</th>
                  <th>Amount</th>
                  <th>Discount (%)</th>
                  <th>Amount To Pay</th>
                  <th>Action</th>
                </tr>
                </thead>
              </table>
            </div>
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
      "order": [[ 4, "asc" ]],
      "columns": [
                  { "data": "messPaymentVoucherId", "visible": false },
                  { "data": null, "orderable": false, "width": "10%"},
                  { "data": "name" },
                  { "data": "days" },
                  { "data": "amount" },
                  { "data": "discount" },
                  { "data": null ,
                	"render": function ( data, type, row, meta ) {
		                      return data.amount - (data.discount * 0.01 * data.amount);  
		                      }
                  },
                  { "data": null, "orderable": false, "width": "10%"}
              ],
       "columnDefs": [ {
           "targets": -1,
           "data": null,
           "className": 'text-center',
           "defaultContent": "<i data-toggle='tooltip' title='Edit Coupen' role='button' class='fa fa-edit gi-1_3x btn-1'></i>" +
           					 "<i data-toggle='tooltip' title='Delete Coupen' role='button' class='fa fa-trash gi-1_3x btn-2'></i>" 
       } ]
    });
    
    voucherTable.on( 'order.dt search.dt', function () {
    	voucherTable.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        });
    }).draw();
    
    $('#voucherTable tbody').on( 'click', 'i', function () {
        
    	var data = voucherTable.row( $(this).parents('tr') ).data();
		if($(this).hasClass('btn-1')) {
			
			if(data.messPaymentVoucherId === '1') {
				$("#amount").prop('readonly', true);
				$("#discount").prop('readonly', true);
			} else {
				$("#amount").prop('readonly', false);
				$("#discount").prop('readonly', false);
			}
			
			$('#voucherModal').modal('toggle');
			flushFormValuse();
			$("#voucherModalTitle").text("Edit Coupen");
			$("#voucherForm").attr("action", "vouchers-edit");
			$("#messPaymentVoucherId").val(data.messPaymentVoucherId);
			$("#name").val(data.name);
			$("#amount").val(data.amount);
			$("#days").val(data.days);
			$("#discount").val(data.discount);
			$("#voucherFormButton").text("Update");

		}    
		
		if($(this).hasClass('btn-2')) {
			if(data.messPaymentVoucherId === '1') {
				bootbox.alert("This voucher can not be deleted !");
				return false;
			}
			
			bootbox.confirm({
			    title: "Confirm Delete",
			    message: "Are you sure want to delete this voucher ? <br> This cannot be undone.",
			    buttons: {
			        cancel: {
			            label: '<i class="fa fa-times"></i> Cancel'
			        },
			        confirm: {
			            label: '<i class="fa fa-check"></i> Confirm'
			        }
			    },
			    callback: function (result) {
			        console.log('This was logged in the callback: ' + result);
			        if(result) {
			        	deleteVoucher(data.messPaymentVoucherId);
			        }
			    }
			});
		}
    });
  
    $("#addNewVoucher").on("click", function(){
    	$('#voucherModal').modal('toggle');
    	flushFormValuse();
		$("#voucherModalTitle").text("Add New Coupen");
		$("#voucherForm").attr("action", "vouchers");
		$("#voucherFormButton").text("Save");
		
    });
    
    function flushFormValuse() {
    	$("#voucherModalTitle").text("");
		$("#voucherForm").attr("action", "");
		$("#messPaymentVoucherId").val("");
		$("#name").val("");
		$("#amount").val("");
		$("#days").val("");
		$("#discount").val("");
		$("#voucherFormButton").text("");
    }
    
    $('#voucherForm').on('submit', function(e){
    	e.preventDefault();
        var $this = $(this);

        $.ajax({
            url: $this.prop('action'),
            method: 'POST',
            data: $this.serialize(),
        }).done(function(response){
        	
        	$('#voucherModal').modal('toggle');
        	voucherTable.ajax.reload();
        	showAutoCloseAlert(response.type, response.message);
        	
        }).error(function(err){
			consloe.log(err);
        });
    });
    
    function deleteVoucher(voucherId) {
    	$.ajax({
            url: "vouchers-delete",
            data: {voucherId: voucherId},
        }).done(function(response){
        	
        	voucherTable.ajax.reload();
        	showAutoCloseAlert(response.type, response.message);
        	
        }).error(function(err){
			consloe.log(err);
        });
    }
  
});
</script>

</body>
</html>

<!-- Add Modal -->
<div class="modal fade" id="voucherModal" tabindex="-1" role="dialog" aria-labelledby="voucher" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="voucherModalTitle"></h4>
            </div>
			
			<form:form id="voucherForm" class="form-horizontal" role="form" modelAttribute="voucher" action="vouchers">
	            
	            <!-- Modal Body -->
	            <div class="modal-body">
	                    <form:hidden path="messPaymentVoucherId" />
	
	                    <div class="form-group">
	                        <form:label class="col-sm-3 control-label" path="name">Voucher Name</form:label>
	                        <div class="col-sm-7">
	                            <form:input path="name" tabindex="1" class="form-control" />
	                        </div>
	                    </div>
	
	                    <div class="form-group">
	                        <form:label class="col-sm-3 control-label" path="amount">Amount</form:label>
	                        <div class="col-sm-7">
	                            <form:input path="amount" tabindex="1" class="form-control" />
	                        </div>
	                    </div>
	
	                    <div class="form-group">
	                        <form:label class="col-sm-3 control-label" path="days">Days</form:label>
	                        <div class="col-sm-7">
	                            <form:input path="days" tabindex="1" class="form-control" />
	                        </div>
	                    </div>
	
	                    <div class="form-group">
	                        <form:label class="col-sm-3 control-label" path="discount">Discount</form:label>
	                        <div class="col-sm-7">
	                            <form:input path="discount" tabindex="1" class="form-control" />
	                        </div>
	                    </div>
	                
	            </div>
	
	            <!-- Modal Footer -->
	            <div class="modal-footer">
		            <button type="submit" class="btn btn-success" id="voucherFormButton"></button>
		            <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
	            </div>
            
            </form:form>
            
        </div>
    </div>
</div>
