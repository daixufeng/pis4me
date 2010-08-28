

function atCalendarControl() {
    var calendar = this;
    this.calendarPad = null;
    this.prevMonth = null;
    this.nextMonth = null;
    this.prevYear = null;
    this.nextYear = null;
    this.goToday = null;
    this.calendarClose = null;
    this.calendarAbout = null;
    this.head = null;
    this.body = null;
    this.today = [];
    this.currentDate = [];
    this.sltDate;
    this.target;
    this.source;

    /************** 加入日曆底板及陰影 *********************/
    this.addCalendarPad = function() {
    document.write("<div id='divCalendarpad' style='position:absolute;top:100px;left:0px;width:255px;height:167px;display:none;'>");
    document.write("<iframe frameborder=0 height='168px' width='255px'></iframe>");
    document.write("<div style='position:absolute;top:4px;left:4px;width:248px;height:164px;background-color:#336699;'></div>");
        document.write("</div>");
        calendar.calendarPad = document.all.divCalendarpad;
    }
    /************** 加入日曆面板 *********************/
    this.addCalendarBoard = function() {
        var BOARD = this;
        var divBoard = document.createElement("div");
        calendar.calendarPad.insertAdjacentElement("beforeEnd", divBoard);
        divBoard.style.cssText = "position:absolute;top:0px;left:0px;width:250px;height:166px;border:1px outset;background-color:buttonface;";

        var tbBoard = document.createElement("table");
        divBoard.insertAdjacentElement("beforeEnd", tbBoard);
        tbBoard.style.cssText = "position:absolute;top:0px;left:0px;width:100%;height:10px;font-size:9pt;";
        tbBoard.cellPadding = 0;
        tbBoard.cellSpacing = 1;
        tbBoard.bgColor = "#333333";

        /************** 設置各功能按鈕的功能 *********************/
        /*********** Calendar About Button ***************/
        trRow = tbBoard.insertRow(0);
        calendar.calendarAbout = calendar.insertTbCell(trRow, 0, "-", "center");
        calendar.calendarAbout.onclick = function() { calendar.about(); }
        /*********** Calendar Head ***************/
        tbCell = trRow.insertCell(1);
        tbCell.colSpan = 5;
        tbCell.bgColor = "#99CCFF";
        tbCell.align = "center";
        tbCell.style.cssText = "cursor:default";
        calendar.head = tbCell;
        /*********** Calendar Close Button ***************/
        tbCell = trRow.insertCell(2);
        calendar.calendarClose = calendar.insertTbCell(trRow, 2, "X", "center");
        calendar.calendarClose.title = "关闭";
        calendar.calendarClose.onclick = function() { calendar.hide(); }

        /*********** Calendar PrevYear Button ***************/
        trRow = tbBoard.insertRow(1);
        calendar.prevYear = calendar.insertTbCell(trRow, 0, "<<", "center");
        calendar.prevYear.title = "上一年";
        calendar.prevYear.onmousedown = function() {
            calendar.currentDate[0]--;

            var strMonth = (eval(calendar.currentDate[1]) < 10) ? ("0" + eval(calendar.currentDate[1])) : ("" + eval(calendar.currentDate[1]));
            var strDay = (eval(calendar.currentDate[2]) < 10) ? ("0" + eval(calendar.currentDate[2])) : ("" + eval(calendar.currentDate[2]));
            //calendar.head.innerText = calendar.currentDate[0]+"-"+strMonth+"-"+strDay;       
            calendar.head.innerText = calendar.currentDate[0] + strMonth + strDay;
            calendar.show(calendar.target, calendar.currentDate[0] + "-" + strMonth + "-" + strDay, calendar.source);
        }
        /*********** Calendar PrevMonth Button ***************/
        calendar.prevMonth = calendar.insertTbCell(trRow, 1, "<", "center");
        calendar.prevMonth.title = "上一月";
        calendar.prevMonth.onmousedown = function() {
            calendar.currentDate[1]--;
            if (calendar.currentDate[1] == 0) {
                calendar.currentDate[1] = 12;
                calendar.currentDate[0]--;
            }

            var strMonth = (eval(calendar.currentDate[1]) < 10) ? ("0" + eval(calendar.currentDate[1])) : ("" + eval(calendar.currentDate[1]));
            var strDay = (eval(calendar.currentDate[2]) < 10) ? ("0" + eval(calendar.currentDate[2])) : ("" + eval(calendar.currentDate[2]));
            //calendar.head.innerText = calendar.currentDate[0]+"-"+strMonth+"-"+strDay;            
            calendar.head.innerText = calendar.currentDate[0] + strMonth + strDay;
            calendar.show(calendar.target, calendar.currentDate[0] + "-" + strMonth + "-" + strDay, calendar.source);
        }
        /*********** Calendar Today Button ***************/
        calendar.goToday = calendar.insertTbCell(trRow, 2, "今天", "center", 3);
        calendar.goToday.title = "今天";
        calendar.goToday.onclick = function() {
            var strMonth = (eval(calendar.today[1]) < 10) ? ("0" + eval(calendar.today[1])) : ("" + eval(calendar.today[1]));
            var strDay = (eval(calendar.today[2]) < 10) ? ("0" + eval(calendar.today[2])) : ("" + eval(calendar.today[2]));

            //calendar.head.innerText = calendar.today[0]+"-"+strMonth+"-"+strDay;            
            calendar.head.innerText = calendar.today[0] + strMonth + strDay;
            calendar.show(calendar.target, calendar.today[0] + "-" + strMonth + "-" + strDay, calendar.source);

            //set today value for target
            //calendar.sltDate=calendar.today[0]+"-"+strMonth+"-"+strDay;    
            //calendar.target.value=calendar.sltDate;
            //calendar.target.value=calendar.today[0]+strMonth+strDay;    
            //calendar.hide();        
        }
        /*********** Calendar NextMonth Button ***************/
        calendar.nextMonth = calendar.insertTbCell(trRow, 3, ">", "center");
        calendar.nextMonth.title = "下一月";
        calendar.nextMonth.onmousedown = function() {
            calendar.currentDate[1]++;
            if (calendar.currentDate[1] == 13) {
                calendar.currentDate[1] = 1;
                calendar.currentDate[0]++;
            }

            var strMonth = (eval(calendar.currentDate[1]) < 10) ? ("0" + eval(calendar.currentDate[1])) : ("" + eval(calendar.currentDate[1]));
            var strDay = (eval(calendar.currentDate[2]) < 10) ? ("0" + eval(calendar.currentDate[2])) : ("" + eval(calendar.currentDate[2]));
            //calendar.head.innerText = calendar.currentDate[0]+"-"+strMonth+"-"+strDay;            
            calendar.head.innerText = calendar.currentDate[0] + strMonth + strDay;
            calendar.show(calendar.target, calendar.currentDate[0] + "-" + strMonth + "-" + strDay, calendar.source);
        }
        /*********** Calendar NextYear Button ***************/
        calendar.nextYear = calendar.insertTbCell(trRow, 4, ">>", "center");
        calendar.nextYear.title = "下一年";
        calendar.nextYear.onmousedown = function() {
            calendar.currentDate[0]++;

            var strMonth = (eval(calendar.currentDate[1]) < 10) ? ("0" + eval(calendar.currentDate[1])) : ("" + eval(calendar.currentDate[1]));
            var strDay = (eval(calendar.currentDate[2]) < 10) ? ("0" + eval(calendar.currentDate[2])) : ("" + eval(calendar.currentDate[2]));
            //calendar.head.innerText = calendar.currentDate[0]+"-"+strMonth+"-"+strDay;
            calendar.head.innerText = calendar.currentDate[0] + strMonth + strDay;
            calendar.show(calendar.target, calendar.currentDate[0] + "-" + strMonth + "-" + strDay, calendar.source);
        }

        trRow = tbBoard.insertRow(2);
        var cnDateName = new Array("周日", "周一", "周二", "周三", "周四", "周五", "周六");
        for (var i = 0; i < 7; i++) {
            tbCell = trRow.insertCell(i)
            tbCell.innerText = cnDateName[i];
            tbCell.align = "center";
            tbCell.width = 35;
            tbCell.style.cssText = "cursor:default;border:1px solid #99CCCC;background-color:#99CCCC;";
        }

        /*********** Calendar Body ***************/
        trRow = tbBoard.insertRow(3);
        tbCell = trRow.insertCell(0);
        tbCell.colSpan = 7;
        tbCell.height = "97px";
        tbCell.vAlign = "top";
        tbCell.bgColor = "#F0F0F0";
        var tbBody = document.createElement("table");
        tbCell.insertAdjacentElement("beforeEnd", tbBody);
        tbBody.style.cssText = "position:relative;top:0px;left:0px;width:245px;height:103px;font-size:9pt;"
        tbBody.cellPadding = 0;
        tbBody.cellSpacing = 1;
        calendar.body = tbBody;
    }
    /************** 加入功能按鈕公共樣式 *********************/
    this.insertTbCell = function(trRow, cellIndex, TXT, trAlign, tbColSpan) {
        var tbCell = trRow.insertCell(cellIndex);
        if (tbColSpan != undefined) tbCell.colSpan = tbColSpan;

        var btnCell = document.createElement("<input type='button' value='"+TXT+"'/>");
        tbCell.insertAdjacentElement("beforeEnd", btnCell);
        btnCell.value = TXT;
        //btnCell.type = "button";
        //btnCell.setAttribute("type", "button");
        btnCell.style.cssText = "width:100%;height:20px;border:1px outset;background-color:buttonface;";
        btnCell.onmouseover = function() {
            btnCell.style.cssText = "width:100%;height:20px;border:1px outset;background-color:#F0F0F0;";
        }
        btnCell.onmouseout = function() {
            btnCell.style.cssText = "width:100%;height:20px;border:1px outset;background-color:buttonface;";
        }
        // btnCell.onmousedown=function(){
        //  btnCell.style.cssText="width:100%;border:1 inset;background-color:#F0F0F0;";
        // }
        btnCell.onmouseup = function() {
            btnCell.style.cssText = "width:100%;height:20px;border:1px outset;background-color:#F0F0F0;";
        }
        btnCell.onclick = function() {
            btnCell.blur();
        }
        return btnCell;
    }
    this.setDefaultDate = function() {
        var dftDate = new Date();
        calendar.today[0] = dftDate.getYear();
        calendar.today[1] = dftDate.getMonth() + 1;
        calendar.today[2] = dftDate.getDate();
    }

    /****************** Show Calendar *********************/
    this.show = function(targetObject, defaultDate, sourceObject) {
        if (targetObject == undefined) {
            //    alert("未設置目標物件. \n方法: ATCALENDAR.show(obj 目標物件, string 預設日期, obj 點擊物件);\n\n目標物件:接受日期返回值的物件.\n預設日期:格式為\"yyyymmdd\",預設為當日日期.\n點擊物件:點擊這個物件彈出calendar,預設為目標物件.\n");
            return false;
        }
        else calendar.target = targetObject;
        if (sourceObject == undefined) calendar.source = calendar.target;
        else calendar.source = sourceObject;

        var firstDay;
        var Cells = new Array();
        if (defaultDate == undefined || defaultDate == "") {
            var theDate = new Array();

            var strMonth = (eval(calendar.today[1]) < 10) ? ("0" + eval(calendar.today[1])) : ("" + eval(calendar.today[1]));
            var strDay = (eval(calendar.today[2]) < 10) ? ("0" + eval(calendar.today[2])) : ("" + eval(calendar.today[2]));
            //calendar.head.innerText = calendar.today[0]+"-"+strMonth+"-"+strDay;
            calendar.head.innerText = calendar.today[0] + strMonth + strDay;
            theDate[0] = calendar.today[0]; theDate[1] = calendar.today[1]; theDate[2] = calendar.today[2];
        }
        else {
            var reg = /^\d{4}-\d{1,2}-\d{2}$/

            if (!defaultDate.match(reg)) {
                alert("預設日期的格式不正確\n\n預設日期可接受格式為:'yyyymmdd'");
                return;
            }

            var theDate = defaultDate.split("-");

            var strMonth = (eval(theDate[1]) < 10) ? ("0" + eval(theDate[1])) : ("" + eval(theDate[1]));
            var strDay = (eval(theDate[2]) < 10) ? ("0" + eval(theDate[2])) : ("" + eval(theDate[2]));

            //calendar.head.innerText = defaultDate;
            calendar.head.innerText = theDate[0] + strMonth + strDay;
        }
        calendar.currentDate[0] = theDate[0];
        calendar.currentDate[1] = theDate[1];
        calendar.currentDate[2] = theDate[2];
        theFirstDay = calendar.getFirstDay(theDate[0], theDate[1]);
        theMonthLen = theFirstDay + calendar.getMonthLen(theDate[0], theDate[1]);
        //calendar.setEventKey();

        calendar.calendarPad.style.display = "";
        var theRows = Math.ceil((theMonthLen) / 7);
        //清除舊的日曆;
        while (calendar.body.rows.length > 0) {
            calendar.body.deleteRow(0)
        }
        //建立新的日曆;
        var n = 0; day = 0;
        for (i = 0; i < theRows; i++) {
            theRow = calendar.body.insertRow(i);
            for (j = 0; j < 7; j++) {
                n++;
                if (n > theFirstDay && n <= theMonthLen) {
                    day = n - theFirstDay;
                    calendar.insertBodyCell(theRow, j, day);
                }
                else {
                    var theCell = theRow.insertCell(j);
                    theCell.style.cssText = "background-color:#F0F0F0;cursor:default;";
                }
            }
        }

        //****************調整日曆位置**************//
        var offsetPos = calendar.getAbsolutePos(calendar.source); //計算物件的位置;
        if ((document.body.offsetHeight - (offsetPos.y + calendar.source.offsetHeight - document.body.scrollTop)) < calendar.calendarPad.style.pixelHeight) {
            var calTop = offsetPos.y - calendar.calendarPad.style.pixelHeight;
        }
        else {
            var calTop = offsetPos.y + calendar.source.offsetHeight;
        }
        if ((document.body.offsetWidth - (offsetPos.x + calendar.source.offsetWidth - document.body.scrollLeft)) > calendar.calendarPad.style.pixelWidth) {
            var calLeft = offsetPos.x;
        }
        else {
            var calLeft = calendar.source.offsetLeft + calendar.source.offsetWidth;
        }
        //alert(offsetPos.x);
        calendar.calendarPad.style.pixelLeft = calLeft;
        calendar.calendarPad.style.pixelTop = calTop;
    }
    /****************** 計算物件的位置 *************************/
    this.getAbsolutePos = function(el) {
        var r = { x: el.offsetLeft, y: el.offsetTop };
        if (el.offsetParent) {
            var tmp = calendar.getAbsolutePos(el.offsetParent);
            r.x += tmp.x;
            r.y += tmp.y;
        }
        return r;
    };
    //************* 插入日期單元格 **************/
    this.insertBodyCell = function(theRow, j, day, targetObject) {
        var theCell = theRow.insertCell(j);
        if (j == 0) var theBgColor = "#FF9999";
        else var theBgColor = "#FFFFFF";
        if (day == calendar.currentDate[2]) var theBgColor = "#CCCCCC";
        if (day == calendar.today[2]) var theBgColor = "#99FFCC";
        theCell.bgColor = theBgColor;
        theCell.innerText = day;
        theCell.align = "center";
        theCell.width = 35;
        theCell.style.cssText = "border:1px solid #CCCCCC;cursor:hand;";
        theCell.onmouseover = function() {
            theCell.bgColor = "#BCD0DE";
            theCell.style.cssText = "border:1px outset;cursor:hand;";
        }
        theCell.onmouseout = function() {
            theCell.bgColor = theBgColor;
            theCell.style.cssText = "border:1px solid #CCCCCC;cursor:hand;";
        }
        theCell.onmousedown = function() {
            theCell.bgColor = "#BCD0DE";
            theCell.style.cssText = "border:1px inset;cursor:hand;";
        }
        theCell.onclick = function() {
            var strMonth = (eval(calendar.currentDate[1]) < 10) ? ("0" + eval(calendar.currentDate[1])) : ("" + eval(calendar.currentDate[1]));
            var strDay = (eval(day) < 10) ? ("0" + eval(day)) : ("" + eval(day));
            calendar.sltDate = calendar.currentDate[0] + "-" + strMonth + "-" + strDay;

            //calendar.target.value=calendar.sltDate;
            calendar.target.value = calendar.currentDate[0] + strMonth + strDay;
            calendar.hide();
        }
    }
    /************** 取得月份的第一天為星期幾 *********************/
    this.getFirstDay = function(theYear, theMonth) {
        var firstDate = new Date(theYear, theMonth - 1, 1);
        return firstDate.getDay();
    }
    /************** 取得月份共有幾天 *********************/
    this.getMonthLen = function(theYear, theMonth) {
        theMonth--;
        var oneDay = 1000 * 60 * 60 * 24;
        var thisMonth = new Date(theYear, theMonth, 1);
        var nextMonth = new Date(theYear, theMonth + 1, 1);
        var len = Math.ceil((nextMonth.getTime() - thisMonth.getTime()) / oneDay);
        return len;
    }
    /************** 隱藏日曆 *********************/
    this.hide = function() {
        //calendar.clearEventKey();
        calendar.calendarPad.style.display = "none";
    }
    /************** 從這裡開始 *********************/
    this.setup = function(defaultDate) {
        calendar.addCalendarPad();
        calendar.addCalendarBoard();
        calendar.setDefaultDate();
    }
    /************** 關於AgetimeCalendar *********************/
    this.about = function() {
        /*//alert("Agetime Calendar V1.0\n\nwww.agetime.com\n");
        popLeft = calendar.calendarPad.style.pixelLeft+4;
        popTop = calendar.calendarPad.style.pixelTop+25;
        var popup = window.createPopup();
        var popupBody = popup.document.body;
        popupBody.style.cssText="border:solid 2 outset;font-size:9pt;background-color:#F0F0F0;";
        var popHtml = "<span style='color:#336699;font-size:12pt;'><U>關於 AgetimeCalendar</U></span><BR><BR>";
        popHtml+="版本: v1.0<BR>日期: 2004-03-13";
        popupBody.innerHTML=popHtml;
        popup.show(popLeft,popTop,240,136,document.body); */
        /*   
        var strAbout = "About AgetimeCalendar\n\n";
        strAbout+="-\t: 關於\n";
        strAbout+="x\t: 隱藏\n";
        strAbout+="<<\t: 上一年\n";
        strAbout+="<\t: 上一月\n";
        strAbout+="今日\t: 返回當天日期\n";
        strAbout+=">\t: 下一月\n";
        strAbout+="<<\t: 下一年\n";
        strAbout+="\nAgetimeCalendar\nVersion:v1.0\nDesigned By:暫停打印 2004-03-16  afos_koo@hotmail.com \n";
        alert(strAbout);
        */
    }

    calendar.setup();
}
function ss() {
    var o = new atCalendarControl();
    
}

