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
					<a class="c-sidebar-nav-link" href="${APP_PATH}/ShareImageInfo/toImageInfoPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">image</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/ShareVideoInfo/toVideoInfoPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">video</span>
					</a>
				</li>
				<li class="c-sidebar-nav-item">
					<a class="c-sidebar-nav-link" href="${APP_PATH}/EmailUpDown/DownloadPage">
						<span class="c-sidebar-nav-icon"></span>
						<span class="c-text">download</span>
					</a>
				</li>
				<!-- download -->
			</ul>
		</li>
		<!-- videos -->
	</ul>
</div>