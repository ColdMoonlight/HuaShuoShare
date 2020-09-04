<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="labelModal" tabindex="-1" role="dialog" aria-labelledby="labelModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">创建图片归属标签...</h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label class="col-form-label" for="imageLabelAccname">名字：</label>
					<div class="controls">
						<input class="form-control" id="imageLabelAccname" type="text" placeholder="请输入标签名字..." />
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label" for="imageLabelHang">归属分类：</label>
					<div class="controls">
						<select class="form-control" id="imageLabelHang" />
							<option value="1">第一大类</option>
							<option value="2">第二大类</option>
							<option value="3">第三大类</option>
							<option value="4">第四大类</option>
							<option value="5">第五大类</option>
						</select>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary btn-cancel" type="button" data-dismiss="modal">取消</button>
				<button class="btn btn-danger btn-ok" type="button">保存</button>
			</div>
		</div>
	</div>
</div>
<script>
	$('#labelModal .btn-cancel').on('click', function () {
		$('#labelModal .btn-ok').off('click');
	});
</script>