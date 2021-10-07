<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>图片</title>
		<jsp:include page="common/backheader.jsp" flush="true"></jsp:include>
		<link rel="stylesheet" href="${APP_PATH}/static/back/lib/magnific-popup/magnific-popup.css">
		<style> .card-body { padding-left: 0; padding-right: 0; } </style>
	</head>

	<body class="c-app">
		<jsp:include page="layout/backheader.jsp" flush="true"></jsp:include>
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
						<div class="btn btn-success hide folder-move-ok">确认移动到当前目录</div>
					</div>
					<div class="folder-right">
						<div class="folder-layout" title="列表展示"></div>
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
			<!-- c-mask -->
			<div class="c-mask hide">
				<div class="spinner-border"></div>
			</div>
		</div>
		<jsp:include page="layout/backfooter.jsp" flush="true"></jsp:include>

		<jsp:include page="common/backfooter.jsp" flush="true"></jsp:include>
		<jsp:include page="common/backsidebar.jsp"></jsp:include>

		<jsp:include page="modal/renameModal.jsp" flush="true"></jsp:include>
		<jsp:include page="modal/deleteModal.jsp" flush="true"></jsp:include>
		
		<script src="${APP_PATH}/static/back/lib/magnific-popup/jquery.magnific-popup.min.js"></script>
		<script>
			// ajax get current list
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
			// callback render current list
			function renderCurrentCategory() {
				getCurrentParentData(function(data) {
					var html = '';
					data.forEach(function(item, idx) {
						var imgUrl = ('${APP_PATH}/' + item.tbShareImageinfoUrl);
						html += '<div class="folder-tr folder-list-item ' + (item.tbShareImageinfoType == 0 ? 'folder' : 'file') +'" data-id="'+ item.tbShareImageinfoId +'" data-name="'+ item.tbShareImageinfoName +'" data-type="'+ item.tbShareImageinfoType +'" data-file="'+ imgUrl +'">' +
							'<div class="folder-td folder-content" href="'+ imgUrl +'">' +
								'<div class="folder-img">' +
									'<img src="'+ (item.tbShareImageinfoType == 0 ? '${APP_PATH}/static/back/img/folder.png' : imgUrl) +'" data-original-src-width="2000" data-original-src-height="2000" />' +
								'</div>' +
								'<span class="folder-name" title="'+ item.tbShareImageinfoName +'">'+ item.tbShareImageinfoName +'</span>' +
							'</div>' +
							'<div class="folder-td folder-time">'+ item.tbShareImageinfoCreatetime +'</div>' +
							'<div class="folder-td folder-operate">' +
								'<button class="btn btn-success hide folder-move" title="移动">移动</button>' +
								(item.tbShareImageinfoType == 0 ?
									'<button class="btn btn-primary hide folder-edit" title="重命名">重命名</button>'
									: '') +
								'<button class="btn btn-danger hide folder-delete" title="删除">删除</button>' +
								(item.tbShareImageinfoType == 1
									? '<button class="btn btn-info hide folder-download" title="下载">下载</button>'
									: '') +
							'</div>' +
						'</div>';
					});
					$('.folder-tbody').html(html);
					// photo Popup
					$('.folder-tbody').find('.folder-list-item.file .folder-content').magnificPopup({
						type: 'image',
						closeOnContentClick: true,
						closeBtnInside: false,
						fixedContentPos: true,
						mainClass: 'mfp-no-margins mfp-with-zoom',
						gallery: {
							enabled: true,
							navigateByImgClick: true,
						},
						image: {
							verticalFit: true
						},
						zoom: {
							enabled: true,
							duration: 300
						}
				    });
					// setting role
					setRole();
				});
				// thead nav-list
				resetCurrentNav();
			}
			// callback setting user role
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
					$('.folder-move').removeClass('hide');
				}
			}
			// callback reset curent nav
			function resetCurrentNav() {
				var html = '';
				navList.forEach(function(item, idx) {
					html += '<div class="folder-nav-item">'+ item.name +' &gt;</div>';
				});
				$('.folder-nav-list').html(html);
			}
			// ajax create folder
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
			// ajax upload img
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
			// callback batches upload imgs
			function batchUploadImageData(files) {
				function cursive(file) {
					function againUpload() {
						toastr.success(file.name + ' 上传成功！');
						if (!files.length) {
							renderCurrentCategory();
							len > 1 && toastr.success('批量上传图片成功！');
						} else {
							cursive(files[0]);
						}
					}
					var formData = new FormData();
					formData.append('image', file);
					formData.append('parentid', currentParent.id);
					formData.append('parentname', currentParent.name);
					files.shift();
					
					if (file.size >= 10485760) {
						toastr.warning(file.name + '超出最大上传10MB的限制!');
						againUpload();
					} else {
						uploadImageData(formData, againUpload);						
					}
				}
				var len = 0;
				files = Array.prototype.slice.apply(files);
				len = files.length;
				len && cursive(files[0]);
			}
			// ajax rename folder-name
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
			// ajax move folder
			function moveFolderData(reqData, callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareImageInfo/removeFileLocal",
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
			// ajax delete folder
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
			// ajax download img log
			function postDownloadImgLog(reqData) {
				$.ajax({
					url: "${APP_PATH}/CaclResources/caclDownloadImg",
					type: "post",
					contentType: 'application/json',
					data: JSON.stringify(reqData),
					success: function (data) {}
				});
			}
			// callback download img
			function download(url, fileName) {
				var anchor = document.createElement('a');
                if ('download' in anchor) {
                    anchor.href = url;
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
			// handle link params
			function handleHrefParam() {
				var curParam = window.localStorage.getItem('cur');
				var listParam = window.localStorage.getItem('list');
				if(curParam && listParam) {
					currentParent = JSON.parse(curParam);
					navList = JSON.parse(listParam);
				}
			}

			var currentParent = {
					"id": 0,
					"name": '我的网盘'
				};
			var navList = [];
			var folderItem = null;
			var adminPower = '${sessionScope.AdminUser.adminPower}';

			// initial
			handleHrefParam();
			renderCurrentCategory();
			// create folder
			$('.folder-create').on('click', function(e) {
				checkSession(function() {
					createFolder(renderCurrentCategory);
				});
			});
			// upload img
			$('.folder-upload').on('click', function() {
				var fileUrl = $('<input type="file" accept="image/png, image/jpeg, image/gif" multiple />');

				checkSession(function() {
					fileUrl.trigger('click');					
				});

				fileUrl.on('change', function(e) {
					var $this = $(this);
					var files = $this[0].files;
					if (!files.length) {
						return false;
					} else {
						batchUploadImageData(files);
						$this.val('');					
					}	
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
				var $this = $(this);
				checkSession(function() {
					folderItem = $this.parents('.folder-list-item');
					$('#folderId').val(folderItem.data('id')).data('type', folderItem.data('type'));
					$('#folderName').val(folderItem.data('name'));
	
					$('#renameModal').modal('show');
				});
			});
			$('#renameModal .btn-ok').on('click', function() {
				var folderName = $('#folderName').val().trim();
				if (!folderName) {
					toastr.warning('文件夹名字不能为空！！！');
					return;
				}
				saveFolderData({
					"tbShareImageinfoId": $('#folderId').val(),
					"tbShareImageinfoName": folderName,
					"tbShareImageinfoType": $('#folderId').data('type')
				}, function() {
					$('#renameModal').modal('hide');
					folderItem.data('name', folderName).find('.folder-name').text(folderName).attr('title', folderName);
				});
			});
			// delete folder
			$(document.body).on('click', '.folder-delete', function(e) {
				e.stopPropagation();
				var $this = $(this);
				checkSession(function() {
					folderItem = $this.parents('.folder-list-item');
					$('#folderId').val(folderItem.data('id'));
					$('#folderName').val(folderItem.data('name'));
	
					$('#deleteModal').modal('show');
				});
			});
			$('#deleteModal .btn-ok').on('click', function() {
				deleteFolderData(folderItem.data('id'), function() {
					$('#deleteModal').modal('hide');
					renderCurrentCategory();
				});
			});
			// folder move
			$(document.body).on('click', '.folder-move', function(e) {
				e.stopPropagation();
				var $this = $(this);
				checkSession(function() {
					folderItem = $this.parents('.folder-list-item');
					$('.folder-move-ok').data('id', folderItem.data('id')).data('name', folderItem.data('name')).data('type', folderItem.data('type')).removeClass('hide');
				});
			});
			$('.folder-move-ok').on('click', function() {
				var $this = $(this);
				moveFolderData({
					"tbShareImageinfoId": $this.data('id'),
					"tbShareImageinfoParentid": currentParent.id,
					"tbShareImageinfoName": $this.data('name'),
					"tbShareImageinfoType": $this.data('type')
				}, function() {
					$('.folder-move-ok').addClass('hide');
					renderCurrentCategory();
				});
			});
			// download file
			$(document.body).on('click', '.folder-download', function(e) {
				e.stopPropagation();
				var $this = $(this);
				checkSession(function() {
					folderItem = $this.parents('.folder-list-item');
					var fileName = folderItem.data('name');
					// log
					postDownloadImgLog({
						 "operationRecordDataType": 1,
						 "operationRecordDataName": fileName
					});
					// event
					download(folderItem.data('file'), fileName);
				});
			});
			// folder back
			$('.folder-back').on('click', function() {
				checkSession(function() {
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
			});
			// folder layout toggle
			$('.folder-layout').on('click', function() {
				if ($(this).hasClass('active')) {
					$(this).removeClass('active').attr('title', '列表展示');
					$('.folder-body').removeClass('active');
				} else {
					$(this).addClass('active').attr('title', '大图展示');
					$('.folder-body').addClass('active');
				}
			});
			// clear home-jump log
			$(window).on('beforeunload', function() {
				window.localStorage.removeItem('cur');
				window.localStorage.removeItem('list');
			});
		</script>
	</body>
</html>