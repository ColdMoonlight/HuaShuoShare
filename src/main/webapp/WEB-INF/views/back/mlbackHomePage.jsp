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
			<div class="c-body">
				<div class="c-main">
					<div id="tree"></div>
				</div>
				<!-- c-mask -->
				<div class="c-mask hide">
					<div class="spinner-border"></div>
				</div>
			</div>
			<jsp:include page="layout/backfooter.jsp" flush="true"></jsp:include>
		</div>

		<jsp:include page="common/backfooter.jsp" flush="true"></jsp:include>
		<script>
		function getCatelogData(callback) {
			$('.c-mask').removeClass('hide');
			$.ajax({
				url: "${APP_PATH}/ShareImageInfo/getShareImageInfoListAll",
				type: "post",
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
		
		// handle origin data
		function findAndInsert(data, item) {
	          var len = data.length;
	          if (!len) return;
	          
	          for (var i = 0; i < len; i += 1) {
	            var cItem = data[i];
	            if (cItem.id == item.tbShareImageinfoParentid) {
	              cItem.children.push({
	                id: item.tbShareImageinfoId,
	                name: item.tbShareImageinfoName,
	                children: []
	              });
	              break;
	            }

	            if (cItem.children && cItem.children.length) {
	              findAndInsert(cItem.children, item);
	            }
	          }
	        }
	        function generateStructureData(data) {
	          var desData = [];
	          var nData = [];

	          data.forEach(function(item) {
	            if (item.tbShareImageinfoParentid == 0) {
	              desData.push({
	                id: item.tbShareImageinfoId,
	                name: item.tbShareImageinfoName,
	                children: []
	              });
	            } else {
	              nData.push(item);
	            }
	          });

	          desData.length && nData.length && nData.forEach(function(item) {
	            findAndInsert(desData, item);
	          });
	          return desData;
	        }
	        
	        // generate tree dom
	        function generateTree($el, data) {
	            data.length && data.forEach(function(item) {
	                var $item = $('<div class="tree-item">'+ item.name +'</div>');
	                if(item.children && item.children.length) {
	                    $item.addClass('arrow');
	                    generateTree($item, item.children);
	                }
	                $el.append($item);
	            });
	        }
	        
	        getCatelogData(function(data) {
	        	var nData = generateStructureData(data);

		        generateTree($('#tree'), nData);
	        });
	        
			// tree dom event
	        $(document.body).on('click', '.tree-item.arrow', function(e) {
	            var $item = $(this);
	            var flag = $item.hasClass('active');
	            e.stopPropagation();
	            flag ? $item.removeClass('active') : $item.addClass('active')
	            $item.children().each(function(idx, sitem) {
	                var $sitem = $(sitem);
	                flag ? $sitem.removeClass('show') : $sitem.addClass('show');
	            });
	        });
		</script>
	</body>
</html>