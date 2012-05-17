<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/style/site.css" media="screen" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript">
        function iconShow(o, id) {
            var val = document.getElementById(id).style.display;
            if (val && val == "none") {
                document.getElementById(id).style.display = "block";
                //$(id).show();
                $(o).text("-")
            }
            else {
            		document.getElementById(id).style.display = "none";
                $(o).text("+")
            }
        }
    </script>
</head>
<body>
    <div class="desktop">
    	<div>
        <div>
            <div style="float: left; width: 12px;">
                <a href="#" onclick="iconShow(this,'basepanel')">-</a></div>
            <div style="float: left; width: 98%">
                <hr>
            </div>
        </div>
        <div id="basepanel">
            <ul>
                <li>
                    <div>
                        <a href="userlist.jsp"></a>
                    </div>
                    <a href="userlist.jsp">User List</a></li>
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
            </ul>
        </div>
        </div>
        <div>
    		<div>
            <div style="float: left; width: 12px;">
                <a href="#" onclick="iconShow(this,'financepanel')">-</a></div>
            <div style="float: left; width: 98%">
                <hr>
            </div>
        </div>
         <div id="financepanel">
            <ul>                
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
                <li>
                    <div>
                        <a href="usercreate.html"></a>
                    </div>
                    <a href="usercreate.html">User Create</a></li>
            </ul>
        </div>
    		</div>
    </div>
</body>
</html>
