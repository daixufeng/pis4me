<%@ Page Title="登录" Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs"
    Inherits="SINOMA.WebUI.Login" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="asp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head runat="server">
    <title>登录</title>
    <meta content="" ie="EmulateIE7" http-equiv="X-UA-Compatible" />
    <meta content="text/html; charset=gb2312" http-equiv="Content-Type" />
    <meta name="description" content="" />
    <meta name="GENERATOR" content="MSHTML 9.00.8112.16430" />
    <link href="~/Styles/Site.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=ResolveUrl("~/Scripts/jquery-1.4.1.js")%>"></script>
    <script type="text/javascript">
        function Form_KeyDown(o, e) {
            var evt = event || e;
            var keyCode = evt.keyCode || evt.which;
            if (keyCode == 13)
                $("#<%=btnLogin.ClientID %>").trigger("click");
            return false;
        }
    </script>
</head>
<body>
    <form runat="server" id="mainForm">
    <asp:ScriptManager ID="ScriptManager" runat="server">
    </asp:ScriptManager>
    <div class="master-waper">
        <div class="header">
            <div class="header-logo">
                <a class="logo" href="<%=ResolveUrl("~/Default.aspx")%>"></a>
            </div>
        </div>
        <div class="headermenu">
            <div class="searchbox">
                <asp:UpdatePanel runat="server" ID="searchUpl">
                    <ContentTemplate>
                        <ul>
                            <li>
                                <asp:TextBox runat="server" ID="txtKeyWord" onkeydown="if(event.which || event.keyCode){if ((event.which == 13) || (event.keyCode == 13)) {document.getElementById('ctl00_ctl00_ctrlHeaderMenu_ctrlSearchBox_btnSearch').click();return false;}} else {return true}; "
                                    class="searchboxtext" onfocus="if(this.value=='产品搜索')this.value=''" Style="width: 200px;"
                                    value="产品搜索" type="text"></asp:TextBox>&nbsp;</li>
                            <li>
                                <asp:Button runat="server" CssClass="searchboxbutton" ID="btnSearch" /></li>
                        </ul>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
            <div class="nav">
                <ul>
                    <li><a href="<%=ResolveUrl("~/Default.aspx")%>"><span>主页</span>
                    </a></li>
                    <%--<li><a href="<%=ResolveUrl("~/Default.aspx")%>">产品信息</a> </li>
                <li><a href="<%=ResolveUrl("~/Default.aspx")%>">合同信息</a> </li>--%>
                    <li><a href="<%=ResolveUrl("~/Account.aspx")%>"><span>我的账户</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="master-waper-page">
            <div class="master-waper-header-bar">
                <h2>
                    登录</h2>
            </div>
            <div class="master-waper-edit-form" onkeydown="Form_KeyDown">
                <table>
                    <tr>
                        <th>
                            用户名：
                        </th>
                        <td>
                            <asp:TextBox runat="server" ID="txtLoginName"></asp:TextBox>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            密码：
                        </th>
                        <td>
                            <asp:TextBox runat="server" ID="txtPassword" TextMode="Password"></asp:TextBox>
                        </td>
                    </tr>
                </table>
                <table style="width: 50%;">
                    <tr>
                        <th align="right">
                            <asp:UpdatePanel ID="uplLogin" runat="server">
                                <ContentTemplate>
                                    <asp:LinkButton runat="server" ID="btnLogin" Style="float: right;" CssClass="button"
                                        OnClick="btnLogin_Click" Width="60px"><ins>登 录</ins></asp:LinkButton>
                                </ContentTemplate>
                            </asp:UpdatePanel>
                        </th>
                    </tr>
                </table>
            </div>
        </div>
        <div class="footer">
            <div id="footer">
                All right reserved sinoma.com
            </div>
        </div>
    </div>
    </form>
</body>
</html>
