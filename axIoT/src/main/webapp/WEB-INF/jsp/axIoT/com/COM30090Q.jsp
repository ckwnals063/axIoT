<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/jsp/axIoT/inc/taglib.jsp" %> 

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta property="og:image" content="../img/axIoT-icon.png" />
    <meta property="og:site_name" content="Axis of Javascript - axisj.com" />
    <meta property="og:description" id="meta_description" content="Javascript UI Library based on JQuery" />
    <title>MI-BAND</title>

<%@ include file="/WEB-INF/jsp/axIoT/inc/script.jsp" %>
    <script type="text/javascript">
    var screen_id = "COM30010Q";    //스크린ID
    
    var fnObj = {
        pageStart: function(){
            this.grid.bind();
            this.bindEvent();
            
        },
        bindEvent: function(){
            var _this = this;
            
            
            $("#dateTo").bindTwinDateTime({
                align: "right", valign: "top", separator: "/", startTargetID: "dateFrom", onChange: function () {
                    //toast.push(Object.toJSON(this));
                }
            });
            
            //------------------------------------
            //조회버튼
            //------------------------------------
            axdom("#btnSearchList").bind("click", function(){
            	
                grid.setList({
                      dataType: "json",
                      debug: true,
                      ajaxUrl:"/com/selectClassMngDetail.do",
                      ajaxPars:$("#frmSearch").serialize(),
                      onLoad:function(){
                    	  grid.setStatus(this.list.length);
                      }
                 });
            });
            
          
            
          
        },
      	//--------------------------------------
        // 항목관리 grid
        //--------------------------------------
        grid: {
            target: new AXGrid(),
            get: function(){ return this.target },
            bind: function(){
                window.grid = fnObj.grid.target;
                this.target.setConfig({
                    targetID : "page-grid-box",
                    theme : "AXGrid",
                    fitToWidth  : true,
                    height:"550",
                    colGroup : [
                        {key:"no", label:"번호", width:"20", align:"center", formatter:function(){
                            return (this.index + 1);
                        }},
                        {key:"userName", label:"사용자이름", align:"center", width:"50"},
                        {key:"time", label:"등록일", align:"center", width:"100"},
                        {key:"beatRate", label:"심박수", align:"center", width:"40"}
                    ],             
                    body : {
                        onclick: function(){
                        	
                        }
                    },
                    page:{
                        paging:false,
                        pageNo:1,
                        pageSize:100,
                        status:{formatter: null}
                    },
                });
            },
     }
 };
 
    $(document).ready(function(){
        //------------------------------------
        // axisj 처리
        //------------------------------------
        fnObj.pageStart();
       
        grid.setList({
            dataType: "json",
            debug: true,
            ajaxUrl:"/com/selectClassMngDetail.do",
            ajaxPars:$("#frmSearch").serialize(),
            onLoad:function(){
          	  grid.setStatus(this.list.length);
            }
       });
    });
  
    </script>
</head>
<body>
<div id="AXPage">
<!-- s ax-header -->
 <%@ include file="/WEB-INF/jsp/axIoT/inc/header.jsp" %> 
<!-- e ax-header -->

    <div class="ax-body">
        <div class="ax-wrap">
          <!--title-->
            <div class="ax-layer ax-title">
                <div class="ax-col-12 ax-content location">
				    <a href="/com/openaxIoTMngSystemMain.do" class="home"><i class="fa fa-home"></i></a>
					<a href="#" class="prev">MIBAND</a>
					<div class="now">
                    <h2>Group_1</h2>
                    <p class="desc">SAMPLE PAGE</p>
                </div>
                </div>
            </div>
        	<!--//title-->
        	
           
            <div class="ax-layer">
                <div class="ax-col-12 ax-content">
                    <!-- s.CXPage -->
                    <div id="CXPage">
                        <div class="ax-layer">
                            <div class="ax-col-12">
                                <div class="ax-unit">
                                    <div class="ax-box">
                                        <!-- s.page-search-box -->
                                        <div class="ax-search" id="page-search-box">
                                        
                                         <form onsubmit="return false;" method="post" name="frmSearch" id="frmSearch">
                                            <table cellpadding="0" cellspacing="0" class="axIoTFrmTable">
                                                <colgroup>
                                                    <col width="100" />
                                                    <col />
                                                    <col width="100" />
                                                    <col />
                                                    <col width="100" />
                                                    <col />
                                                    <col width="100" />
                                                    <col />
                                                </colgroup>
                                                <tbody>
<!--                                                     <tr class="gray"> -->
															<tr>
                                                    			 <th>
		                                                            <div class="tdRel">사용자이름</div>
		                                                        </th>
		                                                        <td colspan="7">
		                                                            <div class="tdRel">
		                                                            	<input type="text" class="AXInput" id="hostIp" name="hostIp"/>
		                                                            </div>
		                                                        </td>
		                                                    </tr>
		                                                    <tr>
		                                                    	<th>
		                                                            <div class="tdRel">날짜</div>
		                                                        </th>
		                                                        <td colspan="3">
		                                                            <div class="tdRel">
		                                                            	<input type="text" class="AXInput" id="dateFrom" name="dateFrom"/>
		                                                            	&nbsp;~&nbsp; 
		                                                            	<input type="text" class="AXInput" id="dateTo" name="dateTo"/>
		                                                            </div>
		                                                        </td>
                                                    			 <th>
		                                                            <div class="tdRel">심박수</div>
		                                                        </th>
		                                                        <td colspan="3">
		                                                            <div class="tdRel">
		                                                            	<input type="text" class="AXInput" id="rateFrom" name="rateFrom"/>
		                                                            	&nbsp;~&nbsp; 
		                                                            	<input type="text" class="AXInput" id="rateTo" name="rateTo"/>
		                                                            </div>
		                                                        </td>
		                                                    </tr>
                                                </tbody>
                                            </table>
                                            </form>
                                        </div>
                                        <!-- e.page-search-box -->
										
                                        <div class="ax-button-group">
                                            <div class="left">
                                            </div>
                                            <div class="right">
                                            <button type="button" class="AXButton Blue" id="btnReset"><i class="axi axi-settings-backup-restore"></i>&nbsp;초기화</button>
                                                <button type="button" class="AXButton Green" id="btnSearchList"><i class="axi axi-search"></i>검색</button>
                                            </div>
                                            <div class="ax-clear"></div>
                                        </div>
										<div class="ax-grid" id="page-grid-box"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="ax-clear"></div>
                        </div>
                    </div>
                    <!-- e.CXPage -->

                </div>
                <div class="ax-clear"></div>
            </div>
        </div>
    </div>
     <!-- e ax-body -->

    <!-- e : 좌측 퀵 메뉴-->  
    <%@ include file="/WEB-INF/jsp/axIoT/inc/footer.jsp" %>
</div>
</body>
</html>
