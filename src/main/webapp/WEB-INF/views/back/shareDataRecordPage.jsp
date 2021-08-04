<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>MEGALOOK ADMIN</title>
		<jsp:include page="common/backheader.jsp" flush="true"></jsp:include>
		<style> .card-body { padding-left: 0; padding-right: 0; } @media screen and (min-width: 1024px) { .c-main { display: flex; } } </style>
	</head>

	<body class="c-app">
		<jsp:include page="layout/backheader.jsp" flush="true"></jsp:include>
		<jsp:include page="layout/backsidebar.jsp" flush="true"></jsp:include>
		<div class="c-wrapper">
			<div class="c-body">
				<div class="c-main">
					<div class="c-init">
						<div class="c-option">
							<span class="c-option-title">Record</span>
							<button class="btn btn-primary btn-create">Create Record</button>
						</div>
						<div class="c-table">
							<div class="c-table-content">
								<div class="c-table-table table-responsive-sm">
									<table class="table">
										<thead>
											<tr>
												<th>id</th>
												<th>type</th>
												<th>detail</th>
												<th>desc</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
								<div id="table-pagination"></div>
							</div>
						</div>
					</div>
					<!-- edit or create -->
					<div class="c-create hide">
						<div class="c-option">
							<span class="c-option-title">Edit Record</span>
							<div class="group">
								<button class="btn btn-secondary btn-cancel">Cancel</button>
								<button class="btn btn-primary btn-save">Save Record</button>
							</div>
						</div>
						<div class="c-form row">
							<input id="datarecordId" hidden>
							<!-- left panel  -->
							<div class="left-panel col-lg-7 col-md-12">
								<div class="card">
									<div class="card-title">
										<div class="card-title-name">General</div>
									</div>
									<div class="card-body">
										<div class="form-group">
											<label class="col-form-label" for="datarecordType">Type</label>
											<div class="controls">
												<select class="form-control" id="datarecordType" />
													<option value="-1">请选择...</option>
													<option value="0">手机号</option>
													<option value="1">邮箱</option>
												</select>
											</div>
										</div>										
										<div class="form-group">
											<label class="col-form-label" for="datarecordTypedetail">Details</label>
											<div class="controls">
												<input class="form-control" id="datarecordTypedetail" type="text" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-form-label" for="datarecordExplain">Description</label>
											<div class="controls">
												<input class="form-control" id="datarecordExplain" type="text" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- mask -->
					<div class="c-mask">
						<div class="spinner-border"></div>
					</div>
				</div>
			</div>
			<jsp:include page="layout/backfooter.jsp" flush="true"></jsp:include>
		</div>

		<jsp:include page="common/backfooter.jsp" flush="true"></jsp:include>
		<script type="text/javascript">
		var isCreate = false;

		// init
		getRecordsData()
		// create collection
		$('.btn-create').on('click', function () {
			$('.c-create .c-option-title').text('Create Record');

			getRecordId(function(data) {
				// init formData
				resetFormData();
				$('#datarecordId').val(data.datarecordId);
				showCreateBlock();
				isCreate = true;
			});
		});
		// save collection
		$('.c-create .btn-save').on('click', function () {
			saveRecordData(getFormData(), function() {
				// redirect tab-active & then search-data
				if (isCreate) isCreate = false;

				getRecordsData();
				showInitBlock();
				$('#datarecordId').val('');
			});
		});
		// cancel collection save
		$('.c-create .btn-cancel').on('click', function () {
			if (isCreate) {
				isCreate = false;
				// delete null Record
				deleteRecordData({
					datarecordId: $('#datarecordId').val(),
				}, function() {
					console.log("cancel create Record");
				});
			}

			showInitBlock();
		});
		function showCreateBlock() {
			$('.c-init').addClass('hide');
			$('.c-create').removeClass('hide');			
		}
		function showInitBlock() {
			$('.c-init').removeClass('hide');
			$('.c-create').addClass('hide');
		}
		// handle formData
		// reset data
		function resetFormData() {
			$('#datarecordId').val('');
			$('#datarecordType').val('-1');
			$('#datarecordTypedetail').val('');
			$('#datarecordExplain').val('');
		}
		// getFormdData
		function getFormData() {
			var data = {};
			data.datarecordId = parseInt($('#datarecordId').val());
			data.datarecordType = parseInt($('#datarecordType').val());
			data.datarecordTypedetail = $('#datarecordTypedetail').val();
			data.datarecordExplain = $('#datarecordExplain').val();

			return data;
		}
		// initFormData
		function initFormData(data) {
			// init
			$('#datarecordId').val(data.datarecordId);
			$('#datarecordType').val(data.datarecordType || '-1');
			$('#datarecordTypedetail').val(data.datarecordTypedetail);
			$('#datarecordExplain').val(data.datarecordExplain);
		}
		// callback get id
		function getRecordId(callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH }/ShareDataRecord/initializaDataRecordInfo",
				type: "post",
				dataType: "json",
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback && callback(data.extend.ShareDataRecordRes)
						toastr.success(data.extend.resMsg);
					} else {
						showInitBlock();
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error(err);
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get all data
		function getRecordsData() {
			$('.c-mask').show();

			var formData = new FormData();
			formData.append('pn', getPageNum());

			$.ajax({
				url: "${APP_PATH}/ShareDataRecord/getShareDataRecordByPage",
				type: "post",
				processData: false,
				contentType: false,
				cache: false,
				data: formData,
				success: function (data) {
					if (data.code == 100) {
						renderTable(data.extend.dataRecordInfo.list);
						renderTablePagination(data.extend.dataRecordInfo);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Record, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback get one data
		function getOneRecordData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/MlbackActShowPro/getOneMlbackActShowProDetail",
				type: "post",
				data: JSON.stringify(reqData),
				dataType: 'json',
				contentType: 'application/json',
				success: function (data) {
					if (data.code == 100) {
						callback(data.extend.mlbackActShowProOne);
						toastr.success(data.extend.resMsg);
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function () {
					toastr.error('Failed to get Record, please refresh the page to get again！');
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// callback save
		function saveRecordData(reqData, callback) {
			$('.c-mask').show();
			$.ajax({
				url: "${APP_PATH}/ShareDataRecord/save",
				type: "post",
				cache: false,
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(reqData),
				success: function (data) {
					if (data.code == 100) {
						toastr.success(data.extend.resMsg);
						callback();
					} else {
						toastr.error(data.extend.resMsg);
					}
				},
				error: function (err) {
					toastr.error(err);
				},
				complete: function () {
					$('.c-mask').hide();
				}
			});
		}
		// init table-list
		function renderTable(data) {
			function getTypeName(type) {
				if (''+type == '0') {
					return '手机号';
				}
				if (''+type == '1') {
					return '邮箱';
				}
				return '--';
			}
			var htmlStr = '';
			for (var i = 0, len = data.length; i < len; i += 1) {
				htmlStr += '<tr><td>' + data[i].datarecordId + '</td>' +
					'<td>' + getTypeName(data[i].datarecordType) + '</td>' +
					'<td>' + data[i].datarecordTypedetail + '</td>' +
					'<td>' + data[i].datarecordExplain + '</td>' +
					'</tr>';
			}
			$('.c-table-table tbody').html(htmlStr);
		}
		</script>
	</body>
</html>