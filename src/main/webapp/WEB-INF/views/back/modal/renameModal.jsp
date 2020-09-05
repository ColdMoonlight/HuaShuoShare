<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="modal fade" id="renameModal" tabindex="-1" role="dialog" aria-labelledby="renameModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-center modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">重命名文件夹/文件...</h4>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<input hidden id="folderId">
				<div class="form-group">
					<!-- <label class="col-form-label" for="folderName">名字：</label> -->
					<div class="controls">
						<input class="form-control" id="folderName" type="text" placeholder="请输入文件夹或文件名字..." />
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