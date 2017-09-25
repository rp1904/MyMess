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
			  <strong>Error!</strong> ${error_msg}
			</div>
        </c:if>
          <div class="box">
            <div class="box-body">
            
              <table id="example" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Mess Id</th>
                  <th>Sr No</th>
                  <th>Mess Name</th>
                  <th>Amount</th>
                  <th>Status</th>
                  <th><input name="select_all" value="1" type="checkbox"></th>
                </tr>
                </thead>
              </table>
              <hr>
              <form id="make_payment_req_form" action="#" method="POST" class="form-horizontal">
              	<input type="hidden" id="paymentDetailList" name="paymentDetailList">
              	<div class="form-group col-sm-6">
                  <label for="datepicker" class="col-sm-6 control-label">Last Date For Payment <span class="text-danger">*</span></label>
                  <div class="col-sm-6">
                    <input type="text" class="form-control" id="datepicker">
                  </div>
                </div>
              	<div class="form-group col-offset-1 col-sm-6">
	              	<button id="make_payment_req_form_btn" class="btn btn-success">Submit</button>
              	</div>
              </form>
              
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
	var list = [];

	$('#datepicker').datepicker({
		startDate:'now',
		endDate:'+3d',
		autoclose: 1,
		todayHighlight: 1,
		forceParse: 0,
		});

		$('#datepicker').datepicker('setDate', new Date());
		$('#datepicker').datepicker('update');

    var rows_selected = [];
    var messTable = $('#example').DataTable({
        "processing": true,
        "ajax": "payment-request/list",
        "columns": [{
                "data": "messIdPk",
                "visible": false
            },
            {
                "data": null,
                "orderable": false,
                "width": "10%"
            },
            {
                "data": "messName"
            },
            {
                "data": "amount"
            },
            {
                "data": "status"
            },
            {
                "data": null
            }
        ],
        "columnDefs": [{
            'targets': -1,
            'searchable': false,
            'orderable': false,
            'width': '1%',
            'render': function(data, type, full, meta) {
                return '<input type="checkbox">';
            }
        }],
    });

    messTable.on('order.dt search.dt', function() {
        messTable.column(1, {
            search: 'applied',
            order: 'applied'
        }).nodes().each(function(cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();

    // Updates "Select all" control in a data table
    //
    function updateDataTableSelectAllCtrl(messTable) {
        var $table = messTable.table().node();
        var $chkbox_all = $('tbody input[type="checkbox"]', $table);
        var $chkbox_checked = $('tbody input[type="checkbox"]:checked', $table);
        var chkbox_select_all = $('thead input[name="select_all"]', $table).get(0);

        // If none of the checkboxes are checked
        if ($chkbox_checked.length === 0) {
            chkbox_select_all.checked = false;
            if ('indeterminate' in chkbox_select_all) {
                chkbox_select_all.indeterminate = false;
            }

            // If all of the checkboxes are checked
        } else if ($chkbox_checked.length === $chkbox_all.length) {
            chkbox_select_all.checked = true;
            if ('indeterminate' in chkbox_select_all) {
                chkbox_select_all.indeterminate = false;
            }

            // If some of the checkboxes are checked
        } else {
            chkbox_select_all.checked = true;
            if ('indeterminate' in chkbox_select_all) {
                chkbox_select_all.indeterminate = true;
            }
        }
    }

    // Handle click on checkbox
    $('#example tbody').on('click', 'input[type="checkbox"]', function(e) {

        var $row = $(this).closest('tr');

        // Get row data
        var data = messTable.row($row).data();

        // Get row ID
        var rowId = data.messIdPk;

        // Determine whether row ID is in the list of selected row IDs
        var index = $.inArray(rowId, rows_selected);

        // If checkbox is checked and row ID is not in list of selected row IDs
        if (this.checked && index === -1) {
            rows_selected.push(rowId);

            // Otherwise, if checkbox is not checked and row ID is in list of selected row IDs
        } else if (!this.checked && index !== -1) {
            rows_selected.splice(index, 1);
        }

        if (this.checked) {
            $row.addClass('selected');
        } else {
            $row.removeClass('selected');
        }

        // Update state of "Select all" control
        updateDataTableSelectAllCtrl(messTable);

        // Prevent click event from propagating to parent
        e.stopPropagation();
    });

    // Handle click on table cells with checkboxes
    //  $('#example').on('click', 'tbody td, thead th:first-child', function(e){
    //     $(this).parent().find('input[type="checkbox"]').trigger('click');
    //  });

    // Handle click on "Select all" control
    $('thead input[name="select_all"]', messTable.table().container()).on('click', function(e) {
        if (this.checked) {
            $('#example tbody input[type="checkbox"]:not(:checked)').trigger('click');
        } else {
            $('#example tbody input[type="checkbox"]:checked').trigger('click');
        }

        // Prevent click event from propagating to parent
        e.stopPropagation();
    });

    // Handle table draw event
    messTable.on('draw', function() {
        // Update state of "Select all" control
        updateDataTableSelectAllCtrl(messTable);
    });

    // Handle form submission event
    $('#make_payment_req_form_btn').on('click', function(e) {
        list = [];
        // Iterate over all selected checkboxes
        $.each(rows_selected, function(index, messIdPk) {
            // Create a hidden element
            var messAmount = $('#amount_' + messIdPk).val();

            list.push(messIdPk + "=" + messAmount);
        });

        console.log(list.length);
        
        if (list.length < 1) {
        	//Please select mess for payment
        	 bootbox_alert_small("Please select mess for payment !");
        	 return false;
        } else if ($('#datepicker').val().trim() === "") {
        	//Please select last date for payment
        	 bootbox_alert_small("Please select last date for payment !");
        	 return false;
        } else {
				bootbox.prompt({
				    title: "Please enter your yassword to continue !",
				    inputType: 'password',
				    size: 'small',
				    callback: function (result) {
				        console.log(result);
				        authenticate(result);
				    }
				});
            return false;
        }
    });

	  function authenticate(password) {
		  $.ajax({
			    url: baseUrl + '/unguarded/authenticate-password',
			    dataType: 'json',
			    type: 'post',
			    contentType: 'application/json',
			    data: JSON.stringify({ 
			    	"password": password
			    	}),
			    processData: false,
			    success: function( data, textStatus, jQxhr ){
			        console.log(data);
			        if (data.responseType === "Success") {
			        	$('#paymentDetailList').val(list);
			        	$('#make_payment_req_form').submit();
			        } else {
			        	bootbox_danger_small(data.message);
			        }
			    },
			    error: function( jqXhr, textStatus, errorThrown ){
			        console.log( errorThrown );
			    }
			});
	  }

});
</script>

</body>
</html>
