

<%@page import="com.byb.bhojan.util.ProjectConstant"%>
<footer class="main-footer text-center">
	<!--     <div class="pull-right text-small"> -->
	<!--       <b>Version</b> 2.3.6 -->
	<!--     </div> -->
	<strong>Copyright &copy; 2017-2018 &nbsp;&nbsp;<a href="#"><%=ProjectConstant.PROJECT_NAME%></a></strong>
	<br> All rights reserved.
</footer>

<!-- Control Sidebar -->
<aside class="control-sidebar control-sidebar-dark">
	<!-- Create the tabs -->
	<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
		<li><a href="#control-sidebar-home-tab" data-toggle="tab"><i
				class="fa fa-home"></i></a></li>
		<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
				class="fa fa-gears"></i></a></li>
	</ul>
	<!-- Tab panes -->
	<div class="tab-content">
		<!-- Home tab content -->
		<div class="tab-pane" id="control-sidebar-home-tab">
			<h3 class="control-sidebar-heading">Recent Activity</h3>
			<ul class="control-sidebar-menu">
				<li><a href="javascript:void(0)"> <i
						class="menu-icon fa fa-birthday-cake bg-red"></i>

						<div class="menu-info">
							<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

							<p>Will be 23 on April 24th</p>
						</div>
				</a></li>
				<li><a href="javascript:void(0)"> <i
						class="menu-icon fa fa-user bg-yellow"></i>

						<div class="menu-info">
							<h4 class="control-sidebar-subheading">Frodo Updated His
								Profile</h4>

							<p>New phone +1(800)555-1234</p>
						</div>
				</a></li>
				<li><a href="javascript:void(0)"> <i
						class="menu-icon fa fa-envelope-o bg-light-blue"></i>

						<div class="menu-info">
							<h4 class="control-sidebar-subheading">Nora Joined Mailing
								List</h4>

							<p>nora@example.com</p>
						</div>
				</a></li>
				<li><a href="javascript:void(0)"> <i
						class="menu-icon fa fa-file-code-o bg-green"></i>

						<div class="menu-info">
							<h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>

							<p>Execution time 5 seconds</p>
						</div>
				</a></li>
			</ul>
			<!-- /.control-sidebar-menu -->

			<h3 class="control-sidebar-heading">Tasks Progress</h3>
			<ul class="control-sidebar-menu">
				<li><a href="javascript:void(0)">
						<h4 class="control-sidebar-subheading">
							Custom Template Design <span
								class="label label-danger pull-right">70%</span>
						</h4>

						<div class="progress progress-xxs">
							<div class="progress-bar progress-bar-danger" style="width: 70%"></div>
						</div>
				</a></li>
				<li><a href="javascript:void(0)">
						<h4 class="control-sidebar-subheading">
							Update Resume <span class="label label-success pull-right">95%</span>
						</h4>

						<div class="progress progress-xxs">
							<div class="progress-bar progress-bar-success" style="width: 95%"></div>
						</div>
				</a></li>
				<li><a href="javascript:void(0)">
						<h4 class="control-sidebar-subheading">
							Laravel Integration <span class="label label-warning pull-right">50%</span>
						</h4>

						<div class="progress progress-xxs">
							<div class="progress-bar progress-bar-warning" style="width: 50%"></div>
						</div>
				</a></li>
				<li><a href="javascript:void(0)">
						<h4 class="control-sidebar-subheading">
							Back End Framework <span class="label label-primary pull-right">68%</span>
						</h4>

						<div class="progress progress-xxs">
							<div class="progress-bar progress-bar-primary" style="width: 68%"></div>
						</div>
				</a></li>
			</ul>
			<!-- /.control-sidebar-menu -->

		</div>
		<!-- /.tab-pane -->
		<!-- Stats tab content -->
		<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
			Content</div>
		<!-- /.tab-pane -->
		<!-- Settings tab content -->
		<div class="tab-pane" id="control-sidebar-settings-tab">
			<form method="post">
				<h3 class="control-sidebar-heading">General Settings</h3>

				<div class="form-group">
					<label class="control-sidebar-subheading"> Report panel
						usage <input type="checkbox" class="pull-right" checked>
					</label>

					<p>Some information about this general settings option</p>
				</div>
				<!-- /.form-group -->

				<div class="form-group">
					<label class="control-sidebar-subheading"> Allow mail
						redirect <input type="checkbox" class="pull-right" checked>
					</label>

					<p>Other sets of options are available</p>
				</div>
				<!-- /.form-group -->

				<div class="form-group">
					<label class="control-sidebar-subheading"> Expose author
						name in posts <input type="checkbox" class="pull-right" checked>
					</label>

					<p>Allow the user to show his name in blog posts</p>
				</div>
				<!-- /.form-group -->

				<h3 class="control-sidebar-heading">Chat Settings</h3>

				<div class="form-group">
					<label class="control-sidebar-subheading"> Show me as
						online <input type="checkbox" class="pull-right" checked>
					</label>
				</div>
				<!-- /.form-group -->

				<div class="form-group">
					<label class="control-sidebar-subheading"> Turn off
						notifications <input type="checkbox" class="pull-right">
					</label>
				</div>
				<!-- /.form-group -->

				<div class="form-group">
					<label class="control-sidebar-subheading"> Delete chat
						history <a href="javascript:void(0)" class="text-red pull-right"><i
							class="fa fa-trash-o"></i></a>
					</label>
				</div>
				<!-- /.form-group -->
			</form>
		</div>
		<!-- /.tab-pane -->
	</div>
</aside>
<!-- /.control-sidebar -->
<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
<div class="control-sidebar-bg"></div>

<!-- jQuery 2.2.3 -->
<script src="../../resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="../../resources/bootstrap/js/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.6 -->
<script src="../../resources/bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="../../resources/plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="../../resources/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script
	src="../../resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
	src="../../resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="../../resources/plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="../../resources/plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="../../resources/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script
	src="../../resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script
	src="../../resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../../resources/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../resources/dist/js/app.min.js"></script>
<!-- DataTables -->
<script
	src="../../resources/plugins/datatables/jquery.dataTables.min.js"></script>
<script
	src="../../resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="../../resources/plugins/bootbox/bootbox-4.4.0.min.js"></script>

<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!-- <script src="../../resources/dist/js/pages/dashboard.js"></script> -->
<!-- AdminLTE for demo purposes -->
<!-- <script src="../../resources/dist/js/demo.js"></script> -->


<script>

var getUrl = window.location;
var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];

var herf = window.location.href;
var url = new URL(herf);
var error = url.searchParams.get("error");

	$(document).ready(function() {
		
		console.log("Redirect Error: " + error);
		
		$('body').addClass('hold-transition skin-blue sidebar-mini');
		
		 $("#logout").on("click", function(){
		    $("#logout-modal").modal('show');
		  });

		  $("#modal-btn-yes").on("click", function(){
			  window.location = 'login?logout';
		  });
		  
		  $("#modal-btn-no").on("click", function(){
		    $("#logout-modal").modal('hide');
		  });
		  
		// Will also work for relative and absolute hrefs
		  $('ul.nav a').filter(function() {
			  console.log("URL: " + url);
			  console.log("this.href: " + this.href.split('#')[0]);
			  console.log("parent(): " + $(this).parent().get( 0 ));
		      return this.href.split('#')[0] == url;
		  }).parent().addClass('active');
		
		  var loc = window.location.href;   //loc = name of jsp page
		  var nav_li_id = loc.split(/[/ ]+/).pop();	
		  console.log("ID: " + nav_li_id);
		  $('#'+nav_li_id).parent().parent().addClass('active').siblings().removeClass('active');
		  $('#'+nav_li_id).addClass('active').siblings().removeClass('active');
	});
	
	function bootbox_alert_small(msg) {
		bootbox.alert({
			message: msg,
			className: 'bootbox_default b_alert',
			size: 'small'
		});
	}
	
	function bootbox_danger_small(msg) {
		bootbox.alert({
			message: msg,
			className: 'b_danger',
			size: 'small'
		});
	}
	
	function showAutoCloseAlert(type, message) {
		$("#alert-div").addClass("alert-" + type);
		$("#alert-msg").text(message);
		$("#alert-div").fadeTo(5000,100).slideUp(500, function(){
		    $("#alert-div").slideUp(500);
		});

	}
	
</script>


<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true"
	id="logout-modal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Are you sure want to logout ?</h4>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" id="modal-btn-yes">Yes</button>
				<button type="button" class="btn btn-success" id="modal-btn-no">No</button>
			</div>
		</div>
	</div>
</div>