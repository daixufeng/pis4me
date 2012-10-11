<%@ Page Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true"
    CodeBehind="Default.aspx.cs" Inherits="SINOMA.WebUI.Admin.Default" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="desktop">
        <ul>
            <li style=<%=isAdmin?"display:block;": "display:none;"%>><a href="CategoryMgt.aspx">
                <h2>
                    类别管理</h2>
            </a></li>
            <li><a href="Products.aspx">
                <h2>
                    产品列表</h2>
            </a></li>
            <li style=<%=isSalesman?"display:none;": "display:block;"%>><a href="ProductAdd.aspx">
                <h2>
                    新增产品</h2>
            </a></li>
            <li style=<%=isDesigner?"display:none;": "display:block;"%>><a href="SalesDetail.aspx">
                <h2>
                    营销记录管理</h2>
            </a></li>
            <li style=<%=isAdmin?"display:block;": "display:none;"%>><a href="UserMgt.aspx">
                <h2>
                    用户管理</h2>
            </a></li>
        </ul>
    </div>
</asp:Content>
