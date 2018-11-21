<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

    <link rel="shortcut icon" href="/img/axIoT-icon.ico" type="image/x-icon" />
    <link rel="icon" href="/img/axIoT-icon.ico" type="image/x-icon" />
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/img/axIoT-icon.png" />
    <link rel="apple-touch-icon-precomposed" href="/img/axIoT-icon.png" />
    <link rel="stylesheet" type="text/css" href="/css/axIoT.css" />
    <link rel="stylesheet" type="text/css" href="/js/axj/ui/cocker/AXJ.min.css" />
    <link rel="stylesheet" type="text/css" href="/js/axj/ui/axicon.min.css" />
    <link rel="stylesheet" href="/font/css/font-awesome.css" >

    <script type="text/javascript" src="/js/axj/ui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/axj/ui/AXJ.all.js"></script>
    <script type="text/javascript" src="/js/axj/ui/jquery.form.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/axIoT_jquery_extend.js"></script>
    <script type="text/javascript" src="/js/save_loginid.js"></script>


    <script type="text/javascript">
        // --------------------------------------------------------------------
        // AXJ 관련 상수값을 redefine 한다.
        // --------------------------------------------------------------------
        AXConfig.AXGrid.emptyListMSG = "<spring:message code='info.nodata.msg' />"; //해당데이터가 없습니다.
        // mask.setConfig({
        //     target: document.body, // 미리 선언했지만
        //     content: "",
        //     onStateChanged: function(){
        //         console.log(this);
        //     }
        // });
        mask.setContent("<div style='position:absolute; top:50%; left:50%; width:400px; height:100px; margin:-50px 0 0 -50px;'><h1><i class='fa fa-spinner fa-spin'></i></h1>");
    </script>