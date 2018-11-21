<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%> 
<script type="text/javascript">
function show(objID){ document.getElementById(objID).style.display=''; }
function hide(objID){ document.getElementById(objID).style.display='none'; }
</script>

	<div class="ax-header">
        <div class="ax-wrap">
            <div class="ax-layer"> 
                        <div class="ax-logo">
                            <a href="#"></a>  
                        </div>
                    
                <div class="ax-col-4 topUtil">  
                        <ul class="ax-loginfo" id="ax-loginfo">
                        <c:if test="${axIoT.userId ne null}"> 
                            <li class="profile"><i class="fa fa-user"></i><span class="userNm">${axIoT.userName}</span>님 </li>
                            <li class="btns"><a href="/com/processLogout.do" class="AXButton"><i class="fa fa-power-off"></i> 로그아웃</a></li>
                        </c:if>
                        <c:if test="${axIoT.userId eq null}"> 
                        	<li class="btns"><a href="/com/processLogout.do" class="AXButton"><i class="fa fa-power-off"></i> 로그인</a></li>
                       	</c:if>
                        </ul>
                        <div class="mx-loginfo"><a id="mx-loginfo-handle" class="mx-menu-button"><i class="fa fa-bars"></i></a></div>
                </div> 
				</div>
        </div>
	
	    <div class="menuFIX" id="menuFIX">
	    <%@ include file="/WEB-INF/jsp/axIoT/inc/menu.jsp" %>
	    </div>
    </div> 
