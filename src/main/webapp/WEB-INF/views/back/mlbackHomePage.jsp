<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH" , request.getContextPath()); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>MEGALOOK ADMIN</title>
		<jsp:include page="common/backheader.jsp" flush="true"></jsp:include>
		<link rel="stylesheet" href="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.css">
		<style> .card-body { padding-left: 0; padding-right: 0; } @media screen and (min-width: 1024px) { .c-main { display: flex; } } </style>
	</head>

	<body class="c-app">
		<jsp:include page="layout/backheader.jsp" flush="true"></jsp:include>
		<div class="c-main">
			<div class="dashboard-left">
				<div class="tree-box">
					<div class="tree-block">
						 <h3>Image</h3>
						<div class="tree" data-type="image"></div>
					</div>
					<div class="tree-block">
						 <h3>Video</h3>
						<div class="tree" data-type="video"></div>
					</div>
				</div>
			</div>
			<div class="dashboard-right">
				<div class="flex-grid">
					<div class="dashboard-msg">
						<h3 class="title">公告</h3>
						<div class="btn-modal" id="btn-add-msg">我来通知一下</div>
						<div class="body">
							<div class="init-loading"></div>
							<div class="msg-log-list"></div>
							<div class="text-right" style="position: absolute; bottom: 1rem; right: 1rem; font-style: italic;">仅显示最近<b>&nbsp;&nbsp;5&nbsp;</b>条记录</div>
						</div>
					</div>
					<div class="dashboard-need">
						<h3 class="title">需求</h3>
						<div class="btn-modal" id="btn-add-need">我有意见要提</div>
						<div class="body">
							<div class="init-loading"></div>
							<div class="need-list"></div>
							<div class="text-right" style="position: absolute; bottom: 1rem; right: 1rem; font-style: italic;">仅显示最近<b>&nbsp;&nbsp;5&nbsp;</b>条记录</div>
						</div>
					</div>
				</div>
				<div class="dashboard-anlysis">
					<div class="dashboard-time">
						<input hidden id="search-start-time" />
						<input hidden id="search-end-time" />
						<input class="form-control daterangetimepicker" id="search-time" type="text" placeholder="@exmaple 2020-01-01 00:00:00 - 2020-01-01 23:59:59">
					</div>

					<div class="dashboard-cal">
						<!-- cal left -->
						<div class="dashboard-handle">
							<div class="dashboard-cal-item">
								<h4>文件夹</h4>
								<div class="dashboard-cal-childs">									
									<div class="dashboard-cal-child-item">
										<span class="title">新建：</span>
										<span class="text">--</span>
									</div>

									<div class="dashboard-cal-child-item">
										<span class="title">更新：</span>
										<span class="text">--</span>
									</div>

									<div class="dashboard-cal-child-item">
										<span class="title">移动：</span>
										<span class="text">--</span>
									</div>

									<div class="dashboard-cal-child-item">
										<span class="title">删除：</span>
										<span class="text">--</span>
									</div>								
								</div>
							</div>

							<div class="dashboard-cal-item">
								<h4>图片</h4>
								<div class="dashboard-cal-childs">								
									<div class="dashboard-cal-child-item">
										<span class="title">上传：</span>
										<span class="text">--</span>
									</div>

									<div class="dashboard-cal-child-item">
										<span class="title">下载：</span>
										<span class="text">--</span>
									</div>

									<!-- <div class="dashboard-cal-child-item">
										<span class="title">更新：</span>
										<span class="text">--</span>
									</div> -->

									<div class="dashboard-cal-child-item">
										<span class="title">移动：</span>
										<span class="text">--</span>
									</div>

									<div class="dashboard-cal-child-item">
										<span class="title">删除：</span>
										<span class="text">--</span>
									</div>
								</div>
							</div>

							<div class="dashboard-cal-item">
								<h4>视频</h4>
								<div class="dashboard-cal-childs">
									<div class="dashboard-cal-child-item">
										<span class="title">上传：</span>
										<span class="text">--</span>
									</div>

									<div class="dashboard-cal-child-item">
										<span class="title">下载：</span>
										<span class="text">--</span>
									</div>

									<!-- <div class="dashboard-cal-child-item">
										<span class="title">更新：</span>
										<span class="text">--</span>
									</div> -->
									
									<div class="dashboard-cal-child-item">
										<span class="title">移动：</span>
										<span class="text">--</span>
									</div>

									<div class="dashboard-cal-child-item">
										<span class="title">删除：</span>
										<span class="text">--</span>
									</div>
								</div>
							</div>
						</div>
						<!-- cal-right -->
						<div class="dashboard-visit">
							<h4>用户访问(人/次)</h4>
							<div class="dashboard-visit-count">--</div>
						</div>
					</div>
				</div>
				<!-- user-log list -->
				<div class="user-log">
					<h3 class="title">操作记录</h3>
					<div class="body">
						<ul class="user-log-list"></ul>
						<div class="user-log-tip text-right hide" style="font-style: italic; padding: 0 1rem;">最多可查看<b>100</b>条记录，更多记录请联系技术人员</br>
						</div>
					</div>
				</div>
			
			</div>
			<!-- c-mask -->
			<div class="c-mask hide">
				<div class="spinner-border"></div>
			</div>
		</div>
		<jsp:include page="layout/backfooter.jsp" flush="true"></jsp:include>

		<jsp:include page="modal/shareTipModal.jsp" flush="true"></jsp:include>
		<jsp:include page="modal/shareNeedModal.jsp" flush="true"></jsp:include>

		<jsp:include page="common/backfooter.jsp" flush="true"></jsp:include>
		<jsp:include page="common/backsidebar.jsp"></jsp:include>
		<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/moment.min.js"></script>
		<script type="text/javascript" src="${APP_PATH}/static/back/lib/datetimepicker/daterangepicker.js"></script>
		<script>
			// get catelog data
			function getCatelogData(type, callback) {
				$('.c-mask').removeClass('hide');
				var url = '';

				if (type == 'image') {
					url = '${APP_PATH}/ShareImageInfo/getShareImageInfoListAll';
				} else if (type == 'video') {
					url = '${APP_PATH}/ShareVideoInfo/getShareVideoInfoListAll';
				}

				// url error
				if (!url) {
					toastr.warning('网络问题，请稍后重试。。。');
					return ;
				}

				$.ajax({
					url: url,
					type: "post",
					async: false,
					success: function (data) {
						if (data.code == 100) {
							toastr.success(data.msg);
							if (callback) {
								if (type == 'video') {
									callback(data.extend.shareVideoInfoList);
								}
								
								if (type == 'image') {									
									callback(data.extend.shareImageInfoList);
								}
							}
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
			
			// get type data for tree
			function getTypeData(type, data) {
				var pId = 0, id = 0, name = '';

				if (type == 'image') {
					pId = data.tbShareImageinfoParentid;
					id = data.tbShareImageinfoId;
					name = data.tbShareImageinfoName;
				}
				
				if (type == 'video') {
					pId = data.tbShareVideoinfoParentid;
					id = data.tbShareVideoinfoId;
					name = data.tbShareVideoinfoName;
				}
				
				return {
					pId: pId,
					id: id,
					name: name
				}
			}

			// insert data recursively
			function findAndInsert(data, item) {
				var len = data.length;
				if (!len) return;
				
				for (var i = 0; i < len; i += 1) {
					var cItem = data[i];
					if (cItem.id == item.pId) {
					  cItem.children.push({
					    id: item.id,
					    name: item.name,
					    children: []
					  });
					  break;
					}

					if (cItem.children && cItem.children.length) {
						findAndInsert(cItem.children, item);
					}
				}
	        }
			
			// handle origin data
	        function generateStructureData(type, data) {
				var desData = [];
				var nData = [];

				data.forEach(function(item) {					
					var nItem = getTypeData(type, item);
					if (!nItem.pId) {
						desData.push({
							id: nItem.id,
							name: nItem.name,
							children: []
						});
					} else {
						nData.push(item);
					}
				});

				desData.length && nData.length && nData.forEach(function(item) {
					findAndInsert(desData, getTypeData(type, item));
				});
				return desData;
	        }
	        
	        // generate tree dom
	        function generateTree($el, data) {
	            data.length && data.forEach(function(item) {
	                var $item = $('<div class="tree-item" data-id="'+ item.id+'" data-name="'+ item.name +'">'+ item.name +'<span class="link">查看</span></div>');
	                if(item.children && item.children.length) {
	                    $item.addClass('arrow');
	                    generateTree($item, item.children);
	                }
	            	$el.append($item);
	            });
	        }
	        
	        // generate trees list
	        function generateTreeList(callback) {
	        	$('.tree').each(function(idx, item) {
		        	var $item = $(item);
		        	var type = $item.data('type');
			        getCatelogData(type, function(data) {
			        	var nData = generateStructureData(type, data);

				        generateTree($item, nData);
				        
				        callback && callback();
			        });
		        });
		        
			    // reset tree
	        	setTimeout(function() {
			        $('.tree>.tree-item').addClass('active');
			        $('.tree>.tree-item>.tree-item').addClass('show');	        		
	        	}, 0);
	        }
	        
	        // get user handle-log
	        function getUserLog(callback) {
	        	$.ajax({
					url: "${APP_PATH}/ShareOperationRecord/getBackHomeOperationRecord",
					type: "post",
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify({
						"operationRecordCreatetime": $('#search-start-time').val(),
					    "operationRecordMotifyTime": $('#search-end-time').val()
					}),
					async: false,
					success: function (data) {
						if (data.code == 100) {
							callback && callback(data.extend.mlfrontPayInfoList);
						} else {
							toastr.error(data.extend.resMsg);
						}
					},
					error: function () {
						toastr.error('获取数据失败，请稍后刷新页面重试！！');
					}
				});
	        }
	        
	        // generate user log
	        function generateUserLog() {
	        	function getFileType(type) {
	        		if (type == 0) {
	        			return '文件夹';
	        		} else if (type == 1) {
	        			return '图片';
	        		} else if (type == 2) {
	        			return '视频';
	        		} else {
	        			return '文件夹';
	        		}
	        	}
	        	getUserLog(function(data) {
	        		var html = '';
	        		if (data.length) {
	        			data.forEach(function(item) {
	        				html += '<li class="user-log-item">' +
									item.operationRecordAdminName +
									'<b> ' + item.operationRecordDesc +
									'</b> 了  <b>' + getFileType(item.operationRecordDataType) +
									' </b> ' + item.operationRecordDataName + ' ' + item.operationRecordCreatetime +
								'</li>';
	        			});
	        			$('.user-log-tip').removeClass('hide');
	        		} else {
	        			html = '<i>该时间范围内没有任何操作。。。</i>';
	        			$('.user-log-tip').addClass('hide');
	        		}

        			$('.user-log-list').html(html);
	        	});
	        }
	        
	        // ajax get user log cals data
	        function getCalUserLogData(callback) {
	        	$.ajax({
					url: "${APP_PATH}/CaclResources/caclNumByDateAndDesc",
					type: "post",
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify({
						"operationRecordCreatetime": $('#search-start-time').val(),
					    "operationRecordMotifyTime": $('#search-end-time').val()
					}),
					success: function (data) {
						if (data.code == 100) {
							var resData = data.extend;
							callback && callback([
								[resData.fileAddNum, resData.fileUpdateNum, resData.fileRemoveNum, resData.fileDelNum],
								[resData.imgUploadNum, resData.imgDownloadNum, resData.imgRemoveNum, resData.imgDelNum],
								[resData.videoUploadNum, resData.videoDownloadNum, resData.videoRemoveNum, resData.videoDelNum],
							], {
								people: resData.peopleNum,
								count: resData.allOperateNum
							});
						} else {
							toastr.error(data.extend.resMsg);
						}
					},
					error: function () {
						toastr.error('获取数据失败，请稍后刷新页面重试！！');
					}
				});
	        }
	        
	        // render all user-log
	        function renderAlluserLogData() {
	        	getCalUserLogData(function(data, pData) {
	        		$('.dashboard-visit-count').text(pData.people + '/' + pData.count);
		        	for (var i = 0; i <= 2; i += 1) {
		        		for (var j = 0; j <= 5; j += 1) {
			    	        $('.dashboard-cal-item').eq(i).find('.dashboard-cal-child-item').eq(j).find('.text').text(data[i][j]);	        				
		        		}
		        	}    	
	        	});
	        }
	        
	        // combine search for dashboard
	        function searchTimeEvent(startTime, endTime) {
	        	$('#search-start-time').val(startTime);
				$('#search-end-time').val(endTime);
				renderAlluserLogData();
				generateUserLog();
	        }
	        
	        // 获取最新公告信息
	        function getAllTips() {
	        	function renderAllTips(data) {
	        		var htmlStr = '';
	        		if (data.length) {
	        			data.forEach(function(item, idx) {
	        				htmlStr += '<div class="db-log-item"><b> * </b>' + item.tbShareUpdateId + ' - ' + item.tbShareUpdateDetail + '</div>';
	        			});
	        		} else {
	        			htmlStr = '<p style="font-size: .875rem; color: #3c3c3c; margin-top: 3rem; font-style: italic;">这里空空如也，没有任何记录...</p>';
	        		}
	        		$('.msg-log-list').html(htmlStr);
	        	}
				$.ajax({
					url: "${APP_PATH}/ShareUpdate/getShareUpdateListAll",
					type: "post",
					dataType: "json",
					success: function (data) {
						if (data.code == 100) {
							renderAllTips(data.extend.shareUpdateList);
						}
						console.log(data.extend.resMsg);
					},
					error: function (err) {
						toastr.error(err);
					},
					complete: function() {
						$('.dashboard-msg .init-loading').addClass('hide');
					}
				});
	        }

	        // 保存一条公告
	        function saveOneTip(reqData, callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareUpdate/initializaUpdateInfo",
					type: "post",
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

	        // 获取最新需求信息
	        function getAllNeeds() {
	        	function renderAllNeeds(data) {
	        		var htmlStr = '';
	        		if (data.length) {
	        			data.forEach(function(item, idx) {
	        				htmlStr += '<div class="db-log-item"><b> * </b>' + item.tbShareDemandId + ' - ' + item.tbShareDemandDetail + '</div>';
	        			});
	        		} else {
	        			htmlStr = '<p style="font-size: .875rem; color: #3c3c3c; margin-top: 3rem; font-style: italic;">这里空空如也，没有任何记录...</p>';
	        		}
	        		$('.need-list').html(htmlStr);
	        	}
				$.ajax({
					url: "${APP_PATH}/ShareDemand/getShareDemandListAll",
					type: "post",
					dataType: "json",
					success: function (data) {
						if (data.code == 100) {
							renderAllNeeds(data.extend.ShareDemandList);
						}
						console.log(data.extend.resMsg);
					},
					error: function (err) {
						toastr.error(err);
					},
					complete: function() {
						$('.dashboard-need .init-loading').addClass('hide');
					}
				});
	        }

	        // 保存一条需求
	        function saveOneNeed(reqData, callback) {
				$('.c-mask').removeClass('hide');
				$.ajax({
					url: "${APP_PATH}/ShareDemand/initializaDemandInfo",
					type: "post",
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

	        // inital tree dom
	        var date = new Date();
			var ymd = date.getFullYear() + '-' + (date.getMonth() + 1 > 9 ? date.getMonth() + 1 : '0' + (date.getMonth() + 1)) + '-' + (date.getDate() > 9 ? date.getDate() : '0' + date.getDate());

	        generateTreeList();
	        getAllTips();
	        getAllNeeds();

	        searchTimeEvent(ymd + ' 00:00:00', ymd + ' 23:59:59');	        
			bindDateRangeEvent(searchTimeEvent);

			// tree arrow-dom event
	        $(document.body).on('click', '.tree-item', function(e) {
	            e.stopPropagation();
	            var $item = $(this);
	            if ($item.hasClass('arrow')) {
		            var flag = $item.hasClass('active');
		            flag ? $item.removeClass('active') : $item.addClass('active')

		            $item.children().each(function(idx, sitem) {
		            	var $sitem = $(sitem);
		            	$sitem.hasClass('tree-item') && (flag ? $sitem.removeClass('show') : $sitem.addClass('show'));
		            });	
	            }
	        });

			// tree text-dom event
			$(document.body).on('click', '.tree-item .link', function(e) {
				e.stopPropagation();
				function getListData($el) {
					var list = [];
					for (var item = $el; ; item = item.parent()) {
						if (item.hasClass('tree')) break;
						list.unshift({
							'id': item.data('id'),
							'name': item.data('name')
						});
					}
					return list;
				}
				var $root = $(this).parents('.tree');
				var type = $root.data('type') || 'image';
				var $curItem = $(this).parent();
				var currData = {
						'id': $curItem.data('id'),
						'name': $curItem.data('name')
					};

				window.localStorage.setItem('cur', JSON.stringify(currData));
				window.localStorage.setItem('list', JSON.stringify(getListData($curItem)));
				
				if (type == 'image') {
					window.location.href = '${APP_PATH}/ShareImageInfo/toImageInfoPage';	
				}
				
				if (type == 'video') {
					window.location.href = '${APP_PATH}/ShareVideoInfo/toVideoInfoPage';	
				}
			});

			// 添加公告
			$('#btn-add-msg').on('click', function() {
				$('#shareTipModal').modal('show');
				$('#tbShareUpdateName').val('');
				$('#tbShareUpdateDetail').val('');
			});

			$('#shareTipModal .btn-ok').on('click', function() {
				var tipTitle = $('#tbShareUpdateName').val().trim();
				var tipDesc = $('#tbShareUpdateDetail').val().trim();

				if (!tipTitle) {
					toastr.warning('公告标题不能为空！！！');
					return;
				}

				if (!tipDesc) {
					toastr.warning('公告描述（详情）不能为空！！！');
					return;
				}

				// save
				saveOneTip({
					"tbShareUpdateName": tipTitle,
				    "tbShareUpdateDetail": tipDesc
				}, function() {
					$('#shareTipModal').modal('hide');
					$('.dashboard-msg .init-loading').removeClass('hide');
					getAllTips();
				});
			});

			// 添加需求
			$('#btn-add-need').on('click', function() {
				$('#shareNeedModal').modal('show');
				$('#tbShareDemandName').val('');
				$('#tbShareDemandDetail').val('');
			});

			$('#shareNeedModal .btn-ok').on('click', function() {
				var tipTitle = $('#tbShareDemandName').val().trim();
				var tipDesc = $('#tbShareDemandDetail').val().trim();

				if (!tipTitle) {
					toastr.warning('公告标题不能为空！！！');
					return;
				}

				if (!tipDesc) {
					toastr.warning('公告描述（详情）不能为空！！！');
					return;
				}

				// save
				saveOneNeed({
					"tbShareDemandName": tipTitle,
				    "tbShareDemandDetail": tipDesc
				}, function() {
					$('#shareNeedModal').modal('hide');
					$('.dashboard-need .init-loading').removeClass('hide');
					getAllNeeds();
				});
			});
		</script>
	</body>
</html>