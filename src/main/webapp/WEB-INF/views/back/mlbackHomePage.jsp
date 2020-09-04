<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>MEGALOOK ADMIN</title>
		<jsp:include page="common/backheader.jsp" flush="true"></jsp:include>
		<link rel="stylesheet" href="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.css">
		<style> .card-body { padding-left: 0; padding-right: 0; } </style>
	</head>

	<body class="c-app">
		<jsp:include page="layout/backheader.jsp" flush="true"></jsp:include>
		<jsp:include page="layout/backsidebar.jsp" flush="true"></jsp:include>
		<div class="c-wrapper">
			<div class="c-body">
				<div class="c-main">
					<div class="folder">
						<div class="folder-title">
							<div class="folder-left">
								<div class="folder-tab">
									<div class="folder-back">&lt; 返回上一级</div>
									<div class="folder-fresh">刷新</div>
								</div>
								<div class="folder-nav">
									<div class="folder-nav-item">我的网盘  &gt;</div>
									<div class="folder-nav-list"></div>
								</div>
							</div>
							<div class="folder-right">
								<div class="btn btn-primary folder-create">新建文件夹</div>
								<div class="btn btn-secondary folder-upload">上传</div>
							</div>
						</div>
						<div class="folder-body">
							<table class="folder-thead">
								<tr>
									<td>文件名</td>
									<td>修改时间</td>
									<td></td>
								</tr>
							</table>
							<div class="folder-tbody-container">
								<table class="folder-tbody"></table>
							</div>
							<div class="folder-pagination"></div>
						</div>
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

		<jsp:include page="modal/renameModal.jsp" flush="true"></jsp:include>
		<jsp:include page="modal/deleteModal.jsp" flush="true"></jsp:include>
		<script>
			function getCurrentParentData(callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareImageInfo/getShareImageInfoListByPid",
					type: "post",
					cache: false,
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify({
						'tbShareImageinfoParentid': currentParent.id
					}),
					success: function (data) {
						if (data.code == 100) {
							toastr.success(data.extend.resMsg);
							callback && callback(data.extend.shareImageInfoList);
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
			function renderCurrentCategory() {
				getCurrentParentData(function(data) {
					var html = '';
					data.forEach(function(item, idx) {
						html += '<tr class="folder-list-item ' + (item.tbShareImageinfoType == 0 ? 'folder' : 'file') +'" data-id="'+ item.tbShareImageinfoId +'" data-name="'+ item.tbShareImageinfoName +'">' +
							'<td><img class="folder-img" src="'+ (item.tbShareImageinfoType == 0 ? '${APP_PATH}/static/back/img/folder.png' : ('${APP_PATH}/' + item.tbShareImageinfoUrl)) +'"><span class="folder-name">'+ item.tbShareImageinfoName +'</span></td>' +
							'<td>'+ item.tbShareImageinfoCreatetime +'</td>' +
							'<td class="folder-operate">' +
								'<button class="btn btn-primary" id="folder-edit">重命名</div>' +
								'<button class="btn btn-danger" id="folder-delete">删除</div>' +
							'</td>' +
						'</tr>';
					});
					$('.folder-tbody').html(html);
				});
				// thead nav-list
				resetCurrentNav();
			}
			function resetCurrentNav() {
				var html = '';
				navList.forEach(function(item, idx) {
					html += '<div class="folder-nav-item">'+ item.name +' &gt;</div>';
				});
				$('.folder-nav-list').html(html);
			}
			function createFolder(callback) {
				var reqData = {
						'tbShareImageinfoParentid': currentParent.id,
						'tbShareImageinfoType': 0,
					};
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareImageInfo/initializaFileNameInfo",
					type: "post",
					cache: false,
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify(reqData),
					success: function (data) {
						if (data.code == 100) {
							toastr.success(data.extend.resMsg);
							callback && callback(data.extend.shareImageInfoReq);
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
			function uploadImageData(formData, callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ImageUpload/imageInfo",
					type: "post",
					data: formData,
					processData: false,
					contentType: false,
					cache: false,
					dataType: 'json',
					success: function (data) {
						if (data.code == 100) {
							callback && callback();
						} else {
							toastr.error('网络错误， 请稍后重试！');	
						}
					},
					error: function (err) {
						toastr.error(err);
					},
					complete: function () {
						$('.c-mask').addClass('hide')
					}
				});
			}
			function saveFolderData(reqData, callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareImageInfo/updateFileName",
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

			var currentParent = {
					"id": 0,
					"name": '我的网盘'
				};
			var navList = [];
			var folderItem = null;
			renderCurrentCategory();
			// create folder
			$('.folder-create').on('click', function(e) {
				createFolder(renderCurrentCategory);
			});
			$('#renameModal .btn-ok').on('click', function() {
				var folderName = $('#folderName').val().trim();
				if (!folderName) {
					toastr.warning('文件夹名字不能为空！！！');
					return;
				}
				saveFolderData({
					"tbShareImageinfoId": $('#folderId').val(),
					"tbShareImageinfoName": folderName
				}, function() {
					$('#renameModal').modal('hide');
					folderItem.data('name', folderName).find('.folder-name').text(folderName);
				});
			});
			// upload img
			$('.folder-upload').on('click', function() {
				var fileUrl = $('<input type="file" accept="image/png, image/jpeg, image/gif" />');
				fileUrl.trigger('click');
				fileUrl.on('change', function(e) {
					var $this = $(this);
					var file = $this[0].files[0];
					var formData = new FormData();

					if (!file) return false;
	
					$this.parent().find('.spinner').show();
					$this.val('');
	
					formData.append('image', file);
					formData.append('parentid', currentParent.id);
					formData.append('parentname', currentParent.name);
	
					uploadImageData(formData, renderCurrentCategory);
				});
			});
			
			// folder event
			$(document.body).on('click', '.folder-list-item.folder', function(e) {
				currentParent = {
					'id': $(this).data('id') || 0,
					'name': $(this).data('name') || '',
				};
				navList.push(currentParent);
				renderCurrentCategory();
			});
			// fresh category
			$('.folder-fresh').on('click', renderCurrentCategory);
			// edit folder
			$(document.body).on('click', '#folder-edit', function(e) {
				e.stopPropagation();
				folderItem = $(this).parents('.folder-list-item');
				$('#folderId').val(folderItem.data('id'));
				$('#folderName').val(folderItem.data('name'));

				$('#renameModal').modal('show');
			});
			// delete folder
			$(document.body).on('click', '#folder-delete', function(e) {
				e.stopPropagation();
				folderItem = $(this).parents('.folder-list-item');
				$('#folderId').val(folderItem.data('id'));
				$('#folderName').val(folderItem.data('name'));

				$('#deleteModal').modal('show');
			});
			// folder back
			$('.folder-back').on('click', function() {
				var lastCategory = null;
				if (!navList.length) {
					toastr.warning("已经在根目录了！！！");
					return ;
				}
				navList.pop();
				if (navList.length) {
					currentParent = navList[navList.length - 1];
				} else {
					currentParent = {
						"id": 0,
						"name": '我的网盘'
					}
				}
				renderCurrentCategory();
			})
		</script>
	</body>
</html>