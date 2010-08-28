<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="StaffEdit.aspx.cs" Inherits="MyWork.StaffEdit" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <link id="Link1" href="~/CSS/dlgtable.css" rel="stylesheet" type="text/css" runat="server" />
    <link id="Link2" href="~/CSS/style.css" rel="stylesheet" type="text/css" runat="server" />

    <script type="text/javascript" src="js/Calendar.js"></script>

    <script type="text/javascript">
        var $ = function(id) {
            return document.getElementById(id);
        }
        //日期控件
        var calender = new atCalendarControl();
        
        function cal_Click() {
            calender.show($get("txtBirthDay"), '', $get("txtBirthDay"));
        }

        function btnCancel_Click() {
            window.returnValue = false;
            window.close();
        }

        function btnSave_Click() {
            if ($("txtStaffNo").value == "") {
                alert("请输入工号！");
                return false;
            }
            if($("txtStaffName").value == ""){
                alert("请输入姓名！");
                return false;
            }
            if ($("txtEmail").value != "") {
                var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                if (!reg.test($("txtEmail").value)) {
                    alert("点子邮件格式不正确！");
                    return false;
                }
            }      
        }
    </script>

</head>
<body style="width: 670px; height: 400px; overflow: hidden; margin:10px">
    <form id="form1" runat="server">
    <div>
        <asp:ScriptManager ID="ScriptMgr" runat="server"/>
        <asp:HiddenField ID="txtStaffId" runat="server" />
    </div>
    <asp:UpdatePanel runat="server" UpdateMode="Conditional">
        <ContentTemplate>
            <table class="title" style="width: 600px">
                <tr>
                    <td align="center" style="font-size: 22px;">
                        人员信息
                    </td>
                </tr>
            </table>
            <table class="toolbar" style="width: 600px">
                <tr>
                    <td>
                        <asp:Button ID="btnSave" runat="server" Text="保存" CssClass="button" 
                             OnClientClick="return btnSave_Click()" OnClick="btnSave_Click" />
                        <input type="button" id="btnCancel" onclick="btnCancel_Click()" value="退出" class="button" />
                    </td>
                </tr>
            </table>
            <div>
                <table width="600">
                    <tr>
                        <td class="label">
                            工号：
                        </td>
                        <td class="content">
                            <asp:TextBox ID="txtStaffNo" runat="server"></asp:TextBox>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            姓名：
                        </td>
                        <td class="content">
                            <asp:TextBox ID="txtStaffName" runat="server"></asp:TextBox>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            性别：
                        </td>
                        <td class="content">
                            <asp:DropDownList runat="server" ID="dplSex" AutoPostBack="True">
                                <asp:ListItem>男</asp:ListItem>
                                <asp:ListItem>女</asp:ListItem>
                            </asp:DropDownList>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            出生日期：
                        </td>
                        <td class="content">
                            <asp:TextBox ID="txtBirthDay" runat="server"></asp:TextBox>
                            <img alt="buttom" src="Images/cal.gif" style="width: 18px; height: 18px; cursor: hand;"
                                onmousedown="cal_Click()" />
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            地址：
                        </td>
                        <td class="content">
                            <asp:TextBox ID="txtAddress" runat="server" Width="180"></asp:TextBox>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            移动电话：
                        </td>
                        <td class="content">
                            <asp:TextBox ID="txtMobile" runat="server"></asp:TextBox>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            办公电话：
                        </td>
                        <td class="content">
                            <asp:TextBox ID="txtTel" runat="server"></asp:TextBox>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            电子邮件：
                        </td>
                        <td class="content">
                            <asp:TextBox ID="txtEmail" runat="server"></asp:TextBox>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">
                            备注：
                        </td>
                        <td class="content">
                            <asp:TextBox ID="txtRemark" Height="40px" runat="server" Width="243px"></asp:TextBox>
                        </td>
                    </tr>
                </table>
            </div>
        </ContentTemplate>
    </asp:UpdatePanel>
    </form>
</body>
</html>
