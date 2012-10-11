<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
    CodeBehind="SiteErrorPage.aspx.cs" Inherits="SINOMA.WebUI.ErrorPages.SiteErrorPage" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="errorpage">
        <div class="erroricon">
        </div>
        <div class="errormessge">
            服务器忙，请稍后重试！</div>
    </div>
</asp:Content>
