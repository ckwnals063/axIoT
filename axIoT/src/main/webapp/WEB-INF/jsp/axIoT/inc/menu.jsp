<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%> 
<!-- <div class="menuFIX"> -->
<div class="menuWrap">
<ul class="gnb">
<c:if test="${axIoT.userId ne null}"> 
<c:set var="k" value="0"/>
<c:forEach var="Topmenu" items="${menuList}">
	<li>
	<a href="${Topmenu.progrmCours}">${Topmenu.menuNm}</a>
	<div>
		<c:forEach var="subMenu" items="${submenu[k]}">
			<a href="${subMenu.progrmCours}">${subMenu.menuNm}</a>
		</c:forEach>
	</div>
	</li>
	<c:set var="k" value="${k+1}"/>
</c:forEach>
</c:if>
<c:if test="${axIoT.userId eq null}"> 
	<li>
		<a href="/com/opencomClassMng.do">MI-BAND</a>
	</li>
	<li>
		<a href="/com/opencomClassMng.do">Galaxy-watch</a>
	</li>
	<li>
		<a href="/com/opencomClassMng.do">IoT_1</a>
	</li>
	<li>
		<a href="/com/opencomClassMng.do">IoT_2</a>
	</li>
</c:if>
</ul>
<c:if test="${axIoT.userId ne null}">   
<div  class="snb">  
</div>
</c:if>
</div>
<!-- </div> -->
