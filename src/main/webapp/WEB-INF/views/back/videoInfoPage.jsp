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
		<jsp:include page="modal/videoModal.jsp" flush="true"></jsp:include>
		
		<script src="${APP_PATH}/static/back/lib/magnific-popup/jquery.magnific-popup.min.js"></script>
		<script>
			function getCurrentParentData(callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareVideoInfo/getShareVideoInfoListByPid",
					type: "post",
					cache: false,
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify({
						'tbShareVideoinfoParentid': currentParent.id
					}),
					success: function (data) {
						if (data.code == 100) {
							toastr.success(data.msg);
							callback && callback(data.extend.shareVideoInfoList);
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
						var imgUrl = ('${APP_PATH}/' + item.tbShareVideoinfoVideoimgurl);
						html += '<div class="folder-tr folder-list-item ' + (item.tbShareVideoinfoType == 0 ? 'folder' : 'file') +'" data-id="'+ item.tbShareVideoinfoId +'" data-name="'+ item.tbShareVideoinfoName +'" data-file="'+ item.tbShareVideoinfoVideourl +'">' +
							'<div class="folder-td folder-content">' +
								'<div class="folder-img">' +
									'<img src="'+ (item.tbShareVideoinfoType == 0 ? '${APP_PATH}/static/back/img/folder.png' : imgUrl) +'" />' +
								'</div>' +
								'<span class="folder-name" title="'+ item.tbShareVideoinfoName +'">'+ item.tbShareVideoinfoName +'</span>' +
							'</div>' +
							'<div class="folder-td folder-time">'+ item.tbShareVideoinfoCreatetime +'</div>' +
							'<div class="folder-td folder-operate">' +
								'<button class="btn btn-primary hide folder-edit" title="重命名">重命名</button>' +
								'<button class="btn btn-danger hide folder-delete" title="删除">删除</button>' +
								(item.tbShareVideoinfoType == 1
									? '<button class="btn btn-info hide folder-download" title="下载">下载</button>'
									: '') +
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
						'tbShareVideoinfoParentid': currentParent.id,
						'tbShareVideoinfoType': 0,
					};
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareVideoInfo/initializaFileNameInfo",
					type: "post",
					cache: false,
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify(reqData),
					success: function (data) {
						if (data.code == 100) {
							toastr.success(data.extend.resMsg);
							callback && callback(data.extend.shareVideoInfoReq);
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
			function generateVideoPoster(file, callback) {
				var videoEl = document.createElement('video'),
		            mimeType = file.type;
		        videoEl.controls = true;
		        videoEl.width = 200;
		        videoEl.height = 200;
		        videoEl.src = URL.createObjectURL(file);
		        videoEl.currentTime = 1;
		        videoEl.addEventListener('canplay', function(e) {
		            var video = e.target;
		            var canvas = document.createElement('canvas'),
		                ctx = canvas.getContext('2d'),
		                width = video.videoWidth,
		                height = video.videoHeight,
		                videoRatio = width / height,
		                offsetLeft = 0,
		                offsetTop = 0,
		                outputWidth = 500,
		                outputHeight = 500;
		            
		            canvas.width = outputWidth;
		            canvas.height = outputHeight;
		            if (videoRatio > 1) {
		                width = outputWidth;
		                height = parseInt(outputWidth / videoRatio);
		                offsetTop = parseInt((outputHeight - height) / 2);
		            } else if (videoRatio == 1) {
		                width = outputWidth;
		                height = outputHeight;
		            } else {
		                height = outputHeight;
		                width = parseInt(outputHeight * videoRatio);
		                offsetLeft = parseInt((outputWidth - width) / 2);
		            }
		            ctx.drawImage(video, offsetLeft, offsetTop, width, height);
		            canvas.toBlob(function(blob) {
		            	callback(new File([blob], ((file.name).split('.')[0] + '.png'), { type: 'image/png'}));
		            }, 'image/png');
		        }, { once: true });
			}
			function uploadVideoData(videoFile, callback) {
				$('.c-mask').removeClass('hide');
				generateVideoPoster(videoFile, function(data) {
					// poster
					var posterFormData = new FormData();
					posterFormData.append('image', data);
					posterFormData.append('parentid', currentParent.id);
					posterFormData.append('parentname', currentParent.name);

	            	$.ajax({
	    				url: "${APP_PATH}/ShareVideoInfo/imageInfo",
	    				type: "post",
	    				data: posterFormData,
	    				processData: false,
	    				contentType: false,
	    				cache: false,
	    				dataType: 'json',
	    				async: false,
	    				success: function (data) {
	    					if (data.code == 100) {
	    						var videoId = data.extend.shareVideoInfo ? data.extend.shareVideoInfo.tbShareVideoinfoId : null;

	    		            	// video
	    		    			var videoFormData = new FormData();
	    		    			videoFormData.append('file', videoFile);
	    		    			videoFormData.append('videoId', videoId);

	    		    			videoId && $.ajax({
	    		    				url: "${APP_PATH}/ShareVideoInfo/uploadVideo",
	    		    				type: "post",
	    		    				data: videoFormData,
	    		    				processData: false,
	    		    				contentType: false,
	    		    				cache: false,
	    		    				dataType: 'json',
	    		    				success: function (data) {
	    		    					if (data.code == 100) {
	    		    						console.log('video上传成功');
	    		    						renderCurrentCategory();
	    		    					} else {
	    		    						toastr.error('网络错误， 请稍后重试！');	
	    		    					}
	    		    				},
	    		    				error: function (err) {
	    		    					toastr.error(err);
	    		    				},
	    		    				complete: function () {
	    		    					$('.c-mask').addClass('hide');
	    		    				}
	    		    			});
	    						console.log('video图片上传成功');
	    					} else {
	    						toastr.error('网络错误， 请稍后重试！');	
	    					}
	    				},
	    				error: function (err) {
	    					toastr.error(err);
	    				}
	    			});
				});
			}
			function saveFolderData(reqData, callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareVideoInfo/updateFileName",
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
					'tbShareVideoinfoId':id,
				};
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareVideoInfo/delete",
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
			// upload video
			$('.folder-upload').on('click', function() {
				var fileUrl = $('<input type="file" accept="video/*" />');
				
				fileUrl.trigger('click');
				fileUrl.on('change', function(e) {
					var $this = $(this);
					var file = $this[0].files[0];

					uploadVideoData(file);
					$this.val('');	
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
			// video preview
			$(document.body).on('click', '.folder-img', function(e) {
				e.stopPropagation();
				folderItem = $(this).parents('.folder-list-item');

				console.log(folderItem.data('file'));
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
					$(this).removeClass('active').attr('title', '列表展示');
					$('.folder-body').removeClass('active');
				} else {
					$(this).addClass('active').attr('title', '大图展示');
					$('.folder-body').addClass('active');
				}
			});
		</script>
	</body>
</html>