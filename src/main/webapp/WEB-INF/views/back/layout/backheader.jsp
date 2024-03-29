<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="c-header">
	<div class="c-menu">
		<svg class="c-icon">
			<use xlink:href="${APP_PATH }/static/back/img/svg/free.svg#cil-list"></use>
		</svg>
	</div>
    <a class="c-logo" href="${APP_PATH}/BackHome/BackHomePage" title="megalook admin">Megalook Admin</a>
    <div class="c-header-nav">
		<div class="c-avatar" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
			<img class="c-avatar-img" alt="user@email.com">
		</div>
		<div class="dropdown-menu">
			<a class="dropdown-item">
				<svg class="c-icon">
					<use xlink:href="${APP_PATH }/static/back/img/svg/free.svg#cil-user"></use>
				</svg>
				<span>Profile</span>
			</a>
			<a class="dropdown-item">
				<svg class="c-icon">
					<use xlink:href="${APP_PATH }/static/back/img/svg/free.svg#cil-settings"></use>
				</svg>
				<span>Settings</span>
			</a>
			<a class="dropdown-item">
				<svg class="c-icon">
					<use xlink:href="${APP_PATH }/static/back/img/svg/free.svg#cil-lock-locked"></use>
				</svg>
				<span>Lock Account</span>
			</a>
			<a class="dropdown-item" href="${APP_PATH }/MlbackAdmin/exitIndex">
				<svg class="c-icon">
					<use xlink:href="${APP_PATH }/static/back/img/svg/free.svg#cil-account-logout"></use>
				</svg>
				<span>Logout</span>
			</a>
		</div>
    </div>
</div>
<div id="sidebar-menu" class="c-sidebar">
	<c:choose>
		<c:when test="${sessionScope.AdminUser.adminHomePageIdstr!=null}">		
		<ul class="c-sidebar-nav">
		<c:choose>
			<c:when test="${sessionScope.AdminUser.adminHomePageIdstr=='One'}">
			<li class="c-sidebar-nav-item">
				<a class="c-sidebar-nav-link" href="${APP_PATH}/BackHome/BackHomePageOne">
					<svg class="c-sidebar-nav-icon">
						<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-speedometer"></use>
					</svg>
					<span class="c-text">Dashboard</span>
				</a>
			</li>
			</c:when>
			<c:when test="${sessionScope.AdminUser.adminHomePageIdstr=='Two'}">
			<li class="c-sidebar-nav-item">
				<a class="c-sidebar-nav-link" href="${APP_PATH}/BackHome/BackHomePageTwo">
					<svg class="c-sidebar-nav-icon">
						<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-speedometer"></use>
					</svg>
					<span class="c-text">Dashboard</span>
				</a>
			</li>
			</c:when>
			<c:otherwise>
			<li class="c-sidebar-nav-item c-sidebar-nav-dropdown active">
				<a class="c-sidebar-nav-link c-sidebar-nav-dropdown-toggle">
					<svg class="c-sidebar-nav-icon">
						<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-speedometer"></use>
					</svg>
					<span class="c-text">Dashboard</span>
				</a>
				<ul class="c-child-menu" style="display: block;">
					<li class="c-sidebar-nav-item">
						<a class="c-sidebar-nav-link" href="${APP_PATH}/BackHome/BackHomePageOne">
							<svg class="c-sidebar-nav-icon">
								<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-cursor"></use>
							</svg>
							<span class="c-text">Dashboard (素材)</span>
						</a>
					</li>
					<li class="c-sidebar-nav-item">
						<a class="c-sidebar-nav-link" href="${APP_PATH}/BackHome/BackHomePageTwo">
							<svg class="c-sidebar-nav-icon">
								<use xlink:href="${APP_PATH}/static/back/img/svg/free.svg#cil-cursor"></use>
							</svg>
							<span class="c-text">Dashboard (电商各平台数据)</span>
						</a>
					</li>
				</ul>
			</li>
			</c:otherwise>
		</c:choose>
		</ul>
		</c:when>
	</c:choose>
	<div class="c-sidebar-other"></div>
</div>
	<div class="c-wrapper">
		<div class="c-body">