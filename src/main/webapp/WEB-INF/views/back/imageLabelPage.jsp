<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>MEGALOOK ADMIN</title>
		<jsp:include page="common/backheader.jsp" flush="true"></jsp:include>
		<style> .card-body { padding-left: 0; padding-right: 0; } </style>
	</head>

	<body class="c-app">
		<jsp:include page="layout/backheader.jsp" flush="true"></jsp:include>
		<jsp:include page="layout/backsidebar.jsp" flush="true"></jsp:include>
		<div class="c-wrapper">
			<div class="c-main">
				<div class="c-main-header">
					<div class="c-title-name">图片标签</div>
					<div class="btn btn-primary" id="create-label">创建标签</div>
				</div>
				<div class="c-main-body">
					<div class="label-category">
						<div class="label-category-name">第一大类：</div>
						<div class="label-category-value"></div>
					</div>
					<div class="label-category">
						<div class="label-category-name">第二大类：</div>
						<div class="label-category-value"></div>
					</div>
					<div class="label-category">
						<div class="label-category-name">第三大类：</div>
						<div class="label-category-value"></div>
					</div>
					<div class="label-category">
						<div class="label-category-name">第四大类：</div>
						<div class="label-category-value"></div>
					</div>
					<div class="label-category">
						<div class="label-category-name">第五大类：</div>
						<div class="label-category-value"></div>
					</div>
				</div>
				<!-- c-mask -->
				<div class="c-mask hide">
					<div class="spinner-border"></div>
				</div>
			</div>
			<jsp:include page="layout/backfooter.jsp" flush="true"></jsp:include>
		</div>
		<jsp:include page="common/backfooter.jsp" flush="true"></jsp:include>
		<jsp:include page="modal/labelModal.jsp" flush="true"></jsp:include>
		<script>
			function getAllLabelData(callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareImageLabel/getShareImageLabelAll",
					type: "post",
					success: function (data) {
						if (data.code == 100) {
							toastr.success(data.msg);
							callback && callback(data.extend.list);
						} else {
							toastr.error(data.msg);
						}
					},
					error: function (err) {
						toastr.error(err);
					},
					complete: function () {
						$('.c-mask').addClass('hide');
					}
				});
			}
			function saveLabelData(reqData, callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareImageLabel/save",
					type: "post",
					cache: false,
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify(reqData),
					success: function (data) {
						if (data.code == 100) {
							toastr.success(data.extend.resMsg);
							callback && callback();
						} else {
							toastr.error(data.extend.resMsg);
						}
					},
					error: function (err) {
						toastr.error(err);
					},
					complete: function () {
						$('.c-mask').addClass('hide');
					}
				});
			}

			function createLabelItem(data) {
				return $('<div class="label-item" data-name="'+ data.imageLabelAccname +'" data-id="'+ data.imageLabelId +'">'+ data.imageLabelAccname +'</div>');
			}

			function initLabelData() {
				getAllLabelData(function(data) {
					data.forEach(function(item, idx) {
						$('.label-category .label-category-value')
							.eq(parseInt(item.imageLabelHang) - 1)
							.append(createLabelItem(item));
					});
				});
			}

			function hasLabel(data) {
				var flag = false;
				$('.label-category .label-category-value')
					.eq(parseInt(data.imageLabelHang) - 1)
					.find('.label-item')
					.each(function(idx, item) {
						if (data.imageLabelAccname == $(item).data('name')) {
							flag = true;
						}
					});
				return flag;
			}

			initLabelData();
			$('#create-label').on('click', function() {
				$('#labelModal').modal('show');
			});
			$('#labelModal .btn-ok').on('click', function() {
				var labelName = $('#imageLabelAccname').val().trim();
				var reqData = {
					'imageLabelAccname': labelName,
				    'imageLabelHang': $('#imageLabelHang').val() || 1
				};
				if (!labelName) {
					toastr.error('请输入有效的标签名！！！');
				} else {
					// check label exsit or not
					if (hasLabel(reqData)) {
						toastr.warning('该标签在所属的分类下已经存在！！！');
					} else {
						saveLabelData(reqData, function() {
							$('#labelModal').modal('hide');
							// reset labelData;
							$('.label-category .label-category-value').html('');
							initLabelData();
						});						
					}
				}
			});
		</script>
	</body>
</html>