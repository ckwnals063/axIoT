/*****************************************************
* Function 명 : isEmpty
* Function 기능설명 : 입력값이 null 또는 공백인지 체크하는 함수
* 작성자 : SYH
* 작성일 : 2016-09-08
******************************************************/
function isEmpty( toCheck ){
    if(toCheck == null) return true;
    if(typeof toCheck == "undefined") return true;
    var checkLtr = toCheck + "";
    var isSpace = true ;

    if ( ( checkLtr == "") || ( checkLtr == null ) )
        return( true );

    for ( var jnx = 0 ; isSpace && ( jnx < checkLtr.length ) ; jnx++)
    {
        if( checkLtr.substring( jnx , jnx+1 ) != " " )
        {
            isSpace = false ;
        }
    }
    return ( isSpace );
}

/*****************************************************
 * Function 명 : cf_changeDtaToCVo
 * Function 기능설명 : data 를 컨트롤러에서 CVo (List<VO>) 로 받을 수 있게끔 변환.
 *                     Vo 명이 Lst 로 끝나는건 자동으로 배열([]) 처리.
 *
 * ex) <Js>
 *     var dataTo = {
 *         'inspPlanTestVo'                : JSON.parse($.grdChangedData(ENV.ID_CATEGORY_GRID)),
 *         'inscPlanRsltRprtQryRsltVo'     : JSON.parse($.grdFormData("#inscPlanRsltRprtQryRsltVo")),
 *         'inscPlanRsltRprtPrlstBrkdLst'  : JSON.parse($.grdFormData("#inscPlanRsltRprtPrlstBrkdLst"))
 *     };
 *     //var data = cf_changeDtaToCVo(dataTo, {debug : true});
 *     var data = cf_changeDtaToCVo(dataTo);
 *
 *
 * param  : objData - JSON Object
 * return : newObj  - JSON Object (CVO type)
 *
 ******************************************************/
function changeDtaToCVo(objData, options) {
   function parseJSON(obj, path) {
       path = path || '';
       if (obj == undefined) {

       } else if (obj.constructor == Object) {
           for ( var key in obj) {
               var name = path + (path == '' ? key : '[' + key + ']');
               parseJSON(obj[key], name);
           }

       } else if (obj.constructor == Array) {
           for (var i = 0; i < obj.length; i++) {
               var index = '[' + i + ']';
               var name = path + index;
               parseJSON(obj[i], name);
           }

       } else {
           if (arr[path] == undefined) {
               arr[path] = obj;
           } else if (arr[path].constructor != Array) {
               arr[path] = [ arr[path], obj ];
           } else {
               arr[path].push(obj);
           }
       }
   }//parseJSON end

   var opts = {
       debug : false
   }
   $.extend(opts, options);

   var _debug = opts.debug;
   var arr = [];

   parseJSON(objData);

   if (_debug) {
       for ( var i in arr) {
           console.log("cf_changeDtaToCVo():src data:" + i + " / " + arr[i]);
       }
   }

   var newObj = {};
   var neweky;
   for ( var key in arr) {
       newkey = key;

       newkey = newkey.replace(/(\[[a-zA-Z0-9\_]+\])(\[[0-9]+\])/, "$2$1"); // a[b][c][0] --> a[b][0][c]
       newkey = newkey.replace(/\]/g, "").replace(/\[/g, "."); // a[0][b][0][c] --> a.0.b.0.c
       newkey = newkey.replace(/\.([0-9]+)/g, "[$1]"); // a.0.b.0.c --> a[0].b[0].c
       newkey = newkey.replace(/List\./g, "List[0]."); // ~List. --> ~List[0]
       if (_debug) {
           console.log("cf_changeDtaToCVo():key changed: " + key + " / "+ newkey);
       }

       if (newkey.match(/\[[0-9]+\]/) && newkey.indexOf("List[") == -1) {
           if (_debug) {
               console.log("cf_changeDtaToCVo():excluded becuase it is array: "+ newkey);
           }
           continue; // 값들은 List_Vo 인데 Vo명이 List[] 가 아닌것들은 제외.
       } else {
           newObj[newkey] = arr[key];
       }
   }
   delete arr;

   if (_debug) {
       console.log("cf_changeDtaToCVo():return data"+ JSON.stringify(newObj));
   }

   return newObj;

}

//Override
/*****************************************************
* Function 명 : phone
* Function 기능설명 : 전화번호 Formatting 된 문자열리턴 , AxisJ에 원래 있는걸 대표번호가 안되서 오버라이딩함
* Usage : "1588-1588".phone()
* 작성자 : SYH
* 작성일 : 2016-09-08
******************************************************/
String.prototype.phone = function (formatter, item, itemIndex, value, key, CH, CHidx) {
        if (this == "") return this;
        var _this = this.replace(/\D+/g, "");
        var myLocalNums = "";
        var num1 = "", num2 = "";
        var localNum = "031/032/033/041/042/043/044/051/052/053/054/055/061/062/063/064/010/011/016/017/019/070/080/060";
        if (_this.left(2) == "02") {
            myLocalNums = "02";
        }else {
            var localNums = localNum.split(/\//g);
            var tempNum = _this.left(3);
            AXUtil.each(localNums, function () {
                if (this == tempNum) {
                    myLocalNums = this;
                    return false;
                }
            });
        }

        if (myLocalNums == "") {
            //myLocalNums = "02";
            if (_this.length > 7) {
                num1 = _this.substr(0, 4);
                num2 = _this.substr(4);
            } else {
                num1 = _this.substr(0, 3);
                num2 = _this.substr(3);
            }
        } else {
            try {
                var snum = myLocalNums.length;
                if ((_this.length - snum) > 7) {
                    num1 = _this.substr(snum, 4);
                    num2 = _this.substr(snum + 4);
                } else {
                    num1 = _this.substr(snum, 3);
                    num2 = _this.substr(snum + 3);
                }
            } catch (e) {
                //trace(e);
            }
        }

        var returnString = myLocalNums;
        if (myLocalNums == "") {
            if (num1 != "") returnString += num1;
            if (num2 != "") returnString += "-" + num2;
        }else{
            if (num1 != "") returnString += "-" + num1;
            if (num2 != "") returnString += "-" + num2;
        }


        return returnString;
}


/*****************************************************
* Function 명 : ConvertSystemSourcetoHtml
* Function 기능설명 : 특수문자 치환
* Usage : ConvertSystemSourcetoHtml(this.item.rejectResn)
* 작성자 : SYH
* 작성일 : 2016-09-08
******************************************************/
function ConvertSystemSourcetoHtml(str){
    str = str.replace(/</g,"&lt;");
    str = str.replace(/>/g,"&gt;");
    str = str.replace(/\"/g,"&quot;");
    str = str.replace(/\'/g,"&#39;");
    str = str.replace(/\n/g,"<br />");

    return str;
}

