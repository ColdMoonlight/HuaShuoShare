<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="shareNeedModal" tabindex="-1" role="dialog" aria-labelledby="shareNeedModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">添加需求</h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group">
						<label class="col-form-label" for="tbShareDemandName">Title</label>
						<div class="controls">
							<input class="form-control" id="tbShareDemandName" placeholder="概要" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-form-label" for="tbShareDemandDetail">Description</label>
						<div class="controls">
							<textarea class="form-control" id="tbShareDemandDetail" placeholder="该字段为展示字段，请详细填写" rows="3"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary btn-cancel" type="button" data-dismiss="modal">取消</button>
				<button class="btn btn-danger btn-ok" type="button">保存</button>
			</div>
		</div>
	</div>
</div>