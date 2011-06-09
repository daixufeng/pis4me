<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Demo.aspx.cs" Inherits="WebUI.Demo" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:GridView ID="grid" runat="server">
            <Columns>
                <asp:BoundField DataField="UserId" HeaderText="用户ID" />
                <asp:BoundField DataField="UserNo" HeaderText="工号" />
                <asp:BoundField DataField="UserName" HeaderText="用户名" />
                <asp:BoundField DataField="Address" HeaderText="地址" />
                <asp:BoundField DataField="Tel" HeaderText="联系电话" />
            </Columns>
        </asp:GridView>
        <%for %>
    
    </div>
    </form>
</body>
</html>
