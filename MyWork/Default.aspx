<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="MyWork._Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <link id="Link1" href="~/CSS/style.css" rel="stylesheet" type="text/css" runat="server" />
    <link id="Link2" href="~/CSS/table.css" rel="stylesheet" type="text/css" runat="server" />
    <script type="text/javascript" src="js/Calendar.js"></script>
    <script type="text/javascript">
        //打开新增对话框
        function btnAdd_Click() {
            var result = window.showModalDialog("StaffEdit.aspx", null,
                'dialogWidth:630px;dialogHeight:385px;help:no;scroll:yes;status:no;resizable:no;');
            if (result)
                document.getElementById("btnQuery").click();
        }
        
        //打开编辑对话框
        function btnEdit_Click() {
            var ogrid =  document.getElementById("grid");
            var staffid = 0;
            for (var i = 1; i < ogrid.rows.length; i++) {
                if (ogrid.rows(i).cells[0].firstChild.checked) {
                    staffid = ogrid.rows(i).cells[1].innerText;
                    break;
                }
            }
            if (staffid == 0) {
                alert("请选择要编辑的行！");
                return;
            }
            var result = window.showModalDialog("StaffEdit.aspx?StaffId="+staffid, null,
                'dialogWidth:630px;dialogHeight:385px;help:no;scroll:yes;status:no;resizable:no;');
            if(result)
                document.getElementById("btnQuery").click();
            //window.location.reload();
        }
        
        //全选
        function slctAll(o) {            
            var ogrid = document.getElementById("grid");
            for (var i = 1; i < ogrid.rows.length; i++) {
                ogrid.rows(i).cells[0].firstChild.checked = o.checked;
            }
        }
        
        //删除检查
        function btnDelete_Click() {
            var ogrid = document.getElementById("grid");
            var has = false;
            for (var i = 1; i < ogrid.rows.length; i++) {
                if (ogrid.rows(i).cells[0].firstChild.checked) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                alert("请选择要删除的行！");
                return false;
            }
            if (!window.confirm("确定要删除吗？")) {
                return false;
            }
        }

        var calender = new atCalendarControl();

        function cal_Click() {
            calender.show($get("txtStaffName"), '', $get("txtStaffName"));
        }
    </script>

</head>
<body>
    <div class="main" style="height: 700px;">
        <form id="form1" runat="server">
        <asp:ScriptManager ID="scriptMgr" runat="server" />
        <div style="margin: 10px;">
            <table class="title">
                <tr>
                    <td align="center" style="font-size: 22px;">
                        人员建档
                    </td>
                </tr>
            </table>
        </div>
        <asp:UpdatePanel runat="server" UpdateMode="Always">
            <ContentTemplate>
                <div style="margin: 10px;">
                    <table class="toolbar">
                        <tr>
                            <td style="width:350px;">
                                <input type="checkbox" onclick="slctAll(this)" />全选
                                <input type="button" id="btnAdd" value="新增" onclick="btnAdd_Click()" class="button" />
                                <input type="button" id="btnEdit" value="编辑" class="button" 
                                    onclick="btnEdit_Click()" />
                                <asp:Button ID="btnDelete" runat="server" Text="删除" CssClass="button" OnClientClick="return btnDelete_Click()" OnClick="btnDelete_Click" />
                            </td>
                            <td  style="width:60px; text-align:right;">
                                工号：</td>
                            <td >
                                <asp:TextBox ID="txtStaffNo" runat="server"></asp:TextBox>
                            </td>
                            <td style="width:60px; text-align:right;">
                                姓名：</td>
                            <td >
                                <asp:TextBox ID="txtStaffName" runat="server"></asp:TextBox>
                            </td>
                            <td style="width:60px; text-align:right;">                                
                                性别：</td>
                            <td >
                                <asp:DropDownList ID="dplSex" runat="server">
                                <asp:ListItem></asp:ListItem>
                                <asp:ListItem>男</asp:ListItem>
                                <asp:ListItem>女</asp:ListItem>
                                </asp:DropDownList></td>
                            <td>
                                <asp:Button ID="btnQuery" runat="server" Text="查询" CssClass="button" OnClick="btnQuery_Click" />
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="content" style="margin: 10px;">
                    <%--<table class="toolbar">
                        <tr>
                            <td style="width: 50%">
                                <input type="checkbox" onclick="slctAll(this)" />全选
                            </td>
                            <td align="right">
                                <asp:Label runat="server" ID="Label1"></asp:Label>
                            </td>
                        </tr>
                    </table>--%>
                    <asp:GridView ID="grid" runat="server" CellPadding="4" ForeColor="#333333" Width="98%"
                        AutoGenerateColumns="False" CssClass="content">
                        <RowStyle BackColor="#EFF3FB" />
                        <EmptyDataTemplate>
                            <table class="empytmp">
                                <tr>
                                    <td>
                                        没有人员数据！
                                    </td>
                                </tr>
                            </table>
                        </EmptyDataTemplate>
                        <Columns>
                            <asp:TemplateField HeaderText="选中">
                                <ItemTemplate>
                                    <asp:CheckBox ID="CheckBox1" runat="server" />
                                </ItemTemplate>
                            </asp:TemplateField>
                            <asp:BoundField DataField="StaffId" HeaderText="ID" />
                            <asp:BoundField DataField="StaffNo" HeaderText="工号"></asp:BoundField>
                            <asp:BoundField DataField="StaffName" HeaderText="姓名"></asp:BoundField>
                            <asp:BoundField DataField="Sex" HeaderText="性别"></asp:BoundField>
                            <asp:BoundField DataField="BirthDay" HeaderText="出生日期"></asp:BoundField>
                            <asp:BoundField DataField="Address" HeaderText="地址"></asp:BoundField>
                            <asp:BoundField DataField="Mobile" HeaderText="移动电话"></asp:BoundField>
                            <asp:BoundField DataField="Tel" HeaderText="办公电话"></asp:BoundField>
                            <asp:BoundField DataField="EMail" HeaderText="邮箱"></asp:BoundField>
                            <asp:BoundField DataField="Remark" HeaderText="备注" />
                        </Columns>
                        <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                        <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                        <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                        <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                        <EditRowStyle BackColor="#2461BF" />
                        <AlternatingRowStyle BackColor="White" />
                    </asp:GridView>
                   <%-- <table class="toolbar">
                        <tr>
                            <td style="width: 50%">
                                <input type="checkbox" onclick="slctAll(this)" />全选
                            </td>
                            <td align="right">
                                <asp:Label runat="server" ID="lblPage"></asp:Label>
                            </td>
                        </tr>
                    </table>--%>
                </div>
                <div style="margin: 10px;">
                    <table class="toolbar">
                        <tr>
                            <td>
                                <input type="checkbox" onclick="slctAll(this)" />全选
                                <input type="button" id="bbtnAdd" value="新增" onclick="btnAdd_Click()" class="button" />
                                <input type="button" id="bbtnEdit" value="编辑" class="button" 
                                    onclick="btnEdit_Click()" />
                                <asp:Button ID="bbtnDelete" runat="server" Text="删除" CssClass="button" OnClick="btnDelete_Click" />
                            </td>
                             <td align="right">
                                <asp:Label runat="server" ID="Label2"></asp:Label>
                            </td>
                        </tr>
                    </table>
                </div>
            </ContentTemplate>
        </asp:UpdatePanel>
        </form>
    </div>
</body>
</html>
