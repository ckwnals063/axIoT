<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    
    <meta property="og:image" content="/img/axIoT-icon.png" />
    <meta property="og:site_name" content="Axis of Javascript - axisj.com" />
    <meta property="og:description" id="meta_description" content="Javascript UI Library based on JQuery" />
    <title>axIoT :::IoT-SDMS:::</title>

    <%@ include file="/WEB-INF/jsp/axIoT/inc/loginscript.jsp" %>
    <script type="text/javascript" src="/js/rsa/jsbn.js"></script>
    <script type="text/javascript" src="/js/rsa/rsa.js"></script>
    <script type="text/javascript" src="/js/rsa/prng4.js"></script>
    <script type="text/javascript" src="/js/rsa/rng.js"></script>
    <!--  -->
    <!-- ---------------------------------------------------------------------- -->
    <!-- 업무 스크립트 시작                                                     -->
    <!-- ---------------------------------------------------------------------- -->
      
    <script type="text/javascript">
    var pageID = "AXValidator";
    
    AXConfig.AXValidator.validateErrMessage.required = "{label}는 필수입력 사항입니다";  
    var myValidator = new AXValidator(); // Validator 선언
    
    var fnObj = {
        pageStart: function(){
        	this.Validator();
        
        	$(".AXCheckbox").find("input").bind("click", function(){
                if(this.checked)this.checked = true;else this.checked = false;
                if($(this).parent().hasClass("checked")) $(this).parent().removeClass("checked");else $(this).parent().addClass("checked");
            });
      
        },
        	
        Validator: function(){
        
        myValidator.setConfig({
            targetFormName : "frmLogin"
        });
        	
        myValidator.add({
            id: "userId",
            label: "ID",
            config: { required: true, maxbyte:50}
            }); 	
        
        myValidator.add({
            id: "pw",
            label: "password",
            config: { required: true, maxbyte:50}
            });  
        
        }, //end Validator
    
    }
    
    //--------------------------------------
    // 텍스트 박스 엔터시 조회
    //--------------------------------------
    $("input[type=text]").keydown(function(e){
        if(e.keyCode == 13){
            $("#btnSearchList").trigger('click');
        }
    });
    
    
    $(document).ready(function(){
    	fnObj.pageStart();
    	
    	getid();                              //아이디 기억하기 시 아이디 가져옴
    	localStorage.removeItem("menuCache"); //메뉴 cache 삭제(로그인 후 재생성) 
    	
    	if($("#message").val() !='' || '${message}' == null){
    		dialog.push({type:"Info", body:$("#message").val()});
    	}
    	
    	
        //--------------------------------------
        // 텍스트 박스 엔터시 조회
        //--------------------------------------
        $("input[type=password]").keydown(function(e){
            if(e.keyCode == 13){
                $("#btnlogin").trigger('click');

            }
        });
        
        //--------------------------------------
        // 아이디 기억하기 체크 시 
        //--------------------------------------
        $("input[type=checkbox]").click(function(){
        	saveid();
        });
        
        
    	//--------------------------------------
        // 로그인버튼 클릭
        //--------------------------------------
        $("#btnlogin").on("click", function(){
            
        	var validateResult = myValidator.validate();
            if(!validateResult) {
            var msg = myValidator.getErrorMessage();
            toast.push(msg);
            myValidator.getErrorElement().focus();
            return false; 
            } else {
        	
        	var username = $("#userId").val();
            var password = $("#pw").val();
            
            if (!username || !password) {
                dialog.push({type:"Info", body:"ID/비밀번호를 입력해주세요."});
            }

            // POST 로그인 폼에 값을 설정하고 발행(submit) 한다.
            var securedLoginForm = document.getElementById("securedLoginForm");
            
            $("#securedUsername").val(username);
            $("#securedPassword").val(password);
            
            securedLoginForm.submit();
            }
        	
        });
    });
  
    </script>
</head>

<body>
	<div id="AXPage" class="login">
		<div class="ax-body">
			<div class="ax-wrap">

<!-- 				<div class="ax-layer ax-title"> -->
				<div class="ax-layer ax-title">
<!-- 					<p style="text-align: center;"> -->
<!-- 					    <img src="/img/logo.png"/> -->
<!-- 					</p> -->
					<h1>
						IoT-SDMS<span>&nbsp;로그인</span>
					</h1>
				</div>
				
				<div class="ax-layer">
					<div class="ax-box">

						<form name="frmLogin" id="frmLogin" method="post">

							<div class="ax-input">
								<span class="name">E-mail</span> <input type="text" name=userId
									id="userId" class="AXInput W200 av-required" placeholder="id"
									maxlength="12">
							</div>
							<div class="ax-input">
								<span class="name">Password</span> <input type="password"
									name="pw" id="pw" class="AXInput W200 av-required"
									placeholder="password" maxlength="20">
							</div>
							<%--                                 <input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus}" /> --%>
							<%--                                 <input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent}" /> --%>

						</form>
						<form id="securedLoginForm" name="securedLoginForm"
							action="/com/processLogin.do" method="post"
							style="display: none;">
							<input type="hidden" name="securedUsername" id="securedUsername"
								value="" /> <input type="hidden" name="securedPassword"
								id="securedPassword" value="" /> <input type="hidden"
								name="message" id="message" value="${message}" />
						</form>

						<div class="ax-login">
							<button class="AXButtonLarge Red" id="btnlogin">로그인</button>
						</div>
						<div class="ax-funcs">
							<label class="AXCheckbox"> <input type="checkbox"
								name="chkuser_id" id="chkuser_id" /><span>아이디 기억하기</span>
							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="ax-footer">
				<div class="ax-wrap">
					<copyright>©2018 K,L,C CO.,LTD. ALL RIGHTS
					RESERVED.</copyright>
				</div>
			</div>
		</div>
	</div>
</body>
</html>