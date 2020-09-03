<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>

<div class="c-sidebar" id="sidebar-menu">
	<ul class="c-sidebar-nav">
		<!-- dashboard -->
		<li class="c-sidebar-nav-item">
			<a class="c-sidebar-nav-link" href="${APP_PATH}/BackHome/BackHomePage">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-speedometer"></use>
				</svg>
				<span class="c-text">Dashboard</span>
			</a>
		</li>
		<!-- images -->
		<li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
			<a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-cursor"></use>
				</svg>
				<span class="c-text">ImagesAndVideo</span>
			</a>
			<ul class="c-child-menu">
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/ShareImageLabel/toImageLabelPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">image</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/ShareImageLabel/toImageLabelUploadPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">video</span>
					</a>
				</li>
			</ul>
		</li>
		<!-- videos -->
		<li class="c-sidebar-nav-item c-sidebar-nav-dropdown">
			<a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle">
				<svg class="c-sidebar-nav-icon">
					<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-cursor"></use>
				</svg>
				<span class="c-text">Videos</span>
			</a>
			<ul class="c-child-menu">
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackSuperCate/toMlbackSuperCatePage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">video</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackCategory/toMlbackCategoryPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Collections</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlbackProduct/toMlbackProductPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">All Products</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/MlfrontReview/toMlfrontReviewPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">Reviews</span>
					</a>
				</li>
			</ul>
		</li>
	</ul>
</div>