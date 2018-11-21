try {
    var sideMenu_data = [
        {
            label: '<i class="fa fa-windows"></i> 대시보드',
            url: '/com/openProjectMngSystemMain.do',
            target: '_self'
        },
        {
            label: '<i class="fa fa-sitemap"></i> 사이트맵',
            url: '#axIoT',
            target: '_self'
        }
    ];

    var topMenu = new AXTopDownMenu();
    // var mobileMenu = new AXMobileMenu();
    // var loginInfoModal = new AXMobileModal();
    var fcObj = {
        pageStart: function () {
            try {
                fcObj.bindEvent();
                fcObj.bindTopMenu();
                fcObj.bindSideMenu();
            } catch (e) {
                console.log('pageStart catch ---->');
                e.print();
            } finally {
                console.log('pageStart finally ---->');
            }
        },
        pageResize: function () {
            fcObj.setAsideMenu();
        },
        setAsideMenu: function () {
            if (!jQuery('.ax-aside-box').data('status')) {
                // if (axf.clientWidth() <= 1024 && axf.clientWidth() >= 767) {
                //     jQuery('.ax-aside-menu').addClass('on');
                //     jQuery('.ax-aside-box').hide();
                // } else if (axf.clientWidth() > 1024) {
                //     jQuery('.ax-aside-menu').removeClass('on');
                //     jQuery('.ax-aside').show();
                // }
            }
        },
        bindEvent: function () {
            fcObj.setAsideMenu();
            axdom('.AXCheckbox').find('input').bind('click', function () {
                if (this.checked) this.checked = true;
                 else this.checked = false;
                if (jQuery(this).parent().hasClass('checked')) jQuery(this).parent().removeClass('checked');
                 else jQuery(this).parent().addClass('checked');
            });
            axdom('.ax-aside-menu').bind('click', function () {
                var elem = jQuery('.ax-aside-box');
                elem.toggle();
                if (elem.css('display') == 'none') {
                    elem.data('status', 'open');
                    jQuery('.ax-content').addClass('expand');
                    jQuery('.ax-aside-menu').addClass('on');
                    jQuery('.ax-aside').width(40); //사이드 메뉴 hide시 컴포넌트 선택안되는 결함수정

                } else {
                    elem.data('status', 'close');
                    jQuery('.ax-content').removeClass('expand');
                    jQuery('.ax-aside-menu').removeClass('on');
                    jQuery('.ax-aside').width(200);//사이드 메뉴 hide시 컴포넌트 선택안되는 결함수정
                }
                axdom(window).resize();
            });

            //기본접기로 시작 SYH 변경
            $('.ax-aside-menu').trigger("click");

            //사이드 메뉴 hide시 컴포넌트 선택안되는 결함을 위해 아래 부분 추가
            //jQuery('.ax-aside').css("z-index", 1).css("position", "absolute");
            //jQuery('.ax-wrap').css("z-index", 2).css("position", "absolute");
        },
        bindTopMenu: function () {
            try {
                var topmenu = {}; // 메뉴생성 변수
                var data = new Array();
                topMenu.setConfig({
                    targetID: 'ax-top-menu',
                    parentMenu: {
                        className: 'parentMenu'
                    },
                    childMenu: {
                        className: 'childMenu',
                        hasChildClassName: 'expand', // script 방식에서만 지원 됩니다.
                        align: 'center',
                        valign: 'top',
                        margin: {
                            top: 0,
                            left: 0
                        },
                        arrowClassName: 'varrow2',
                        arrowMargin: {
                            top: 1,
                            left: 0
                        }
                    },
                    childsMenu: {
                        className: 'childsMenu',
                        hasChildClassName: 'expand',
                        align: 'left',
                        valign: 'top',
                        margin: {
                            top: - 4,
                            left: 0
                        },
                        arrowClassName: 'harrow',
                        arrowMargin: {
                            top: 13,
                            left: 1
                        }
                    },
                    onComplete: function () {
                        console.log('bindTopMenu onComplete Start >>> ');
                        if (window.page_menu_id) topMenu.setHighLightOriginID(window.page_menu_id);
                        console.log('bindTopMenu onComplete End >>> ');
                    }
                });
//                if (typeof (localStorage) == 'undefined' || localStorage.getItem('menuCache') == '' || localStorage.getItem('menuCache') == null) {
//                    $.ajax({
//                        url: '/com/selectTopMenu.do',
//                        async: false,
//                        dataType: 'json',
//                        type: 'POST',
//                        success: function (data) {
//                            console.log('bindTopMenu. ajax----------------------------------->');
//                            localStorage.setItem('menuCache', JSON.stringify(data));
//                            topmenu = eval(data.topmenu); //Json object 로 변환
//                            topMenu.setTree(topmenu); //메뉴구조 SET
//                        },
//                        error: function (e) {
//                            data = JSON.parse(e.responseText);
//                            dialog.push({
//                                type: 'Caution',
//                                body: data.message
//                            });
//                        }
//                    }); //ajax end
//                } else {
//                    console.log('bindTopMenu. menuCache cache----------------------------------->');
//                    //localStorage.removeItem("menuCache"); //cache 지우기
//                    var data = JSON.parse(localStorage.getItem('menuCache'));
//                    topmenu = eval(data.topmenu);
//                    topMenu.setTree(topmenu); //메뉴구조 SET
//                }
//                topmenu=[
//                     {label:"Bottom Menu", url:"http://www.axisj.com", 
//                    	 cn:[
//                      	     {label:"valign - bottom", url:"http://www.axisj.com"},
//                      	     {label:"margin - bootom", url:"http://www.axisj.com"},
//                             {label:"margin - top X", url:"http://www.axisj.com"}
//                         ]},
//                     {label:"Script Control Way", url:"http://www.axisj.com", 
//                         cn:[
//                             {label:"Script Way Use setTree", url:"abhttp://www.axisj.comc"},
//                             {label:"setHighLightMenu", url:"http://www.axisj.com", 
//                            	 cn:[
//                                        {label:"first : String", url:"http://www.axisj.com"},
//                                        {label:"second : Array", url:"http://www.axisj.com"},
//                                        {label:"third : setHighLightOriginID", url:"http://www.axisj.com"}
//                                    ]},
//                             {label:"myMenu2", url:"http://www.axisj.com"}
//                      ]},
//                      {label:"no Expand Menu", url:"http://www.axisj.combc"},
//                      {label:"no Expand Menu", url:"http://www.axisj.com"},
//                ];
//                topMenu.setTree(topmenu); //메뉴구조 SET
                
            } catch (e) {
                e.print();
            } finally {
                //topMenu.setTree(topmenu);
            }
        },
        bindSideMenu: function () {
            var po = [
            ],
            _target = axdom('#ax-aside-ul');
            for (var mi = 0; mi < sideMenu_data.length; mi++) {
                po.push('<li><a href="' + sideMenu_data[mi].url + '" target="' + sideMenu_data[mi].target + '">');
                po.push(sideMenu_data[mi].label);
                po.push('</a></li>');
            }
            _target.empty();
            _target.append(po.join(''));
        }
    }; //end fcObj
} catch (e) {
    console.log('menu catch ---->');
    e.print();
    console.error(e.description);
} finally {
    console.log('finally excute');
    jQuery(document).ready(fcObj.pageStart.delay(0.1));
    jQuery(window).resize(fcObj.pageResize);
}
