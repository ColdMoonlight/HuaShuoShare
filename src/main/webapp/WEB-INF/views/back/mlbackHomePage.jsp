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
							<div class="folder-center">
								<div class="btn btn-primary hide folder-create">新建文件夹</div>
								<div class="btn btn-secondary hide folder-upload">上传</div>
							</div>
							<div class="folder-right">
								<div class="folder-layout"></div>
							</div>
						</div>
						<div class="folder-body">
							<div class="folder-thead">
								<div class="folder-tr">
									<div class="folder-td">文件名</div>
									<div class="folder-td">修改时间</div>
									<div class="folder-td"></div>
								</div>
							</div>
							<div class="folder-tbody-container">
								<div class="folder-tbody"></div>
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
							toastr.success(data.msg);
							callback && callback(data.extend.shareImageInfoList);
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
			function renderCurrentCategory() {
				getCurrentParentData(function(data) {
					var html = '';
					data.forEach(function(item, idx) {
						html += '<div class="folder-tr folder-list-item ' + (item.tbShareImageinfoType == 0 ? 'folder' : 'file') +'" data-id="'+ item.tbShareImageinfoId +'" data-name="'+ item.tbShareImageinfoName +'" data-file="'+ item.tbShareImageinfoUrl +'">' +
							'<div class="folder-td folder-content">' +
								'<div class="folder-img" style="background-image: url('+ (item.tbShareImageinfoType == 0 ? '${APP_PATH}/static/back/img/folder.png' : ('${APP_PATH}/' + item.tbShareImageinfoUrl)) +');"></div>' +
								'<span class="folder-name">'+ item.tbShareImageinfoName +'</span>' +
							'</div>' +
							'<div class="folder-td folder-time">'+ item.tbShareImageinfoCreatetime +'</div>' +
							'<div class="folder-td folder-operate">' +
								'<button class="btn btn-primary hide folder-edit">重命名</button>' +
								'<button class="btn btn-danger hide folder-delete">删除</button>' +
								(item.tbShareImageinfoType == 1 ? '<button class="btn btn-info hide folder-download">下载</button>' : '') +
							'</div>' +
						'</div>';
					});
					$('.folder-tbody').html(html);
					// setting role
					setRole();
				});
				// thead nav-list
				resetCurrentNav();
			}
			function setRole() {
				if (adminPower == '0') {
					$('.folder-download').removeClass('hide');
				}
				
				if (adminPower == '1') {
					$('.folder-upload').removeClass('hide');
					$('.folder-download').removeClass('hide');
				}
				
				if (adminPower == '2') {
					$('.folder-upload').removeClass('hide');
					$('.folder-download').removeClass('hide');
					$('.folder-edit').removeClass('hide');
					$('.folder-delete').removeClass('hide');
					$('.folder-create').removeClass('hide');
				}
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
			
			function deleteFolderData(id, callback) {
				var reqData = {
					'tbShareImageinfoId':id,
				};
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareImageInfo/delete",
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
			
			function download(url, fileName) {
				var anchor = document.createElement('a');
                if ('download' in anchor) {
                    anchor.href = ('${APP_PATH}/' + url);
                    anchor.download = fileName;
                    anchor.style.display = 'none';

                    document.body.appendChild(anchor);

                    anchor.addEventListener('click', function(e) {
                        e.stopPropagation();
                        this.removeEventListener('click', arguments.callee);
                    });

                    setTimeout(function() {
                        anchor.click();
                        document.body.removeChild(anchor);
                    }, 50);
                } else {
                	toastr.warning('该浏览器暂不支持下载，请使用现代浏览器！');
                }
			}

			var currentParent = {
					"id": 0,
					"name": '我的网盘'
				};
			var navList = [];
			var folderItem = null;
			var adminPower = '${sessionScope.AdminUser.adminPower}';
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
			$(document.body).on('click', '.folder-edit', function(e) {
				e.stopPropagation();
				folderItem = $(this).parents('.folder-list-item');
				$('#folderId').val(folderItem.data('id'));
				$('#folderName').val(folderItem.data('name'));

				$('#renameModal').modal('show');
			});
			// delete folder
			$(document.body).on('click', '.folder-delete', function(e) {
				e.stopPropagation();
				folderItem = $(this).parents('.folder-list-item');
				$('#folderId').val(folderItem.data('id'));
				$('#folderName').val(folderItem.data('name'));

				$('#deleteModal').modal('show');
			});
			$('#deleteModal .btn-ok').on('click', function() {
				deleteFolderData(folderItem.data('id'), function() {
					$('#deleteModal').modal('hide');
					renderCurrentCategory();
				});
			});
			// download file
			$(document.body).on('click', '.folder-download', function(e) {
				e.stopPropagation();
				folderItem = $(this).parents('.folder-list-item');

				download(folderItem.data('file'), folderItem.data('name'));
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
			});
			// folder layout toggle
			$('.folder-layout').on('click', function() {
				if ($(this).hasClass('active')) {
					$(this).removeClass('active');
					$('.folder-body').removeClass('active');
				} else {
					$(this).addClass('active');
					$('.folder-body').addClass('active');
				}
			});
		</script>
	</body>
</html>