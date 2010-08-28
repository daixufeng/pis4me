<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="CalenderTest.aspx.cs" Inherits="MyWork.CalenderTest" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>

    <script type="text/javascript">
        Calender = function() {
            this.dom = document.createElement("DIV");
        }

        Calender.constructor = function() {

    }

    var render = function(curentDate) { 
    
    }
        Calender.prototype.show = function(targetObject) {
        
        this.style.display = "block";
    }

    function cal_Click() {
        var cal = new Calender();
        cal.show();
    }
    </script>

</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:TextBox ID="txtBirthDay" runat="server"></asp:TextBox>
        <img alt="buttom" src="Images/cal.gif" style="width: 18px; height: 18px; cursor: hand;"
            onmousedown="cal_Click()" />
    </div>
    </form>
</body>
</html>
