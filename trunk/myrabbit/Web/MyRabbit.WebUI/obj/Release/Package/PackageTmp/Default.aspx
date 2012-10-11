<%@ Page Title="" Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true"
    CodeBehind="Default.aspx.cs" Inherits="SINOMA.WebUI.Default" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <div>
        <div class="master-waper-header-bar">
            <h2>
                中材装备产品分类</h2>
        </div>
        <div class="master-waper-content-main">
            <div class="master-waper-content">
                <dl>
                    <%for (int i = 0; i < categories.Count; i++)
                      {
                    %>
                    <dt><a class="a1" href="ProductList.aspx?isParent=true&categoryId=<%=categories[i].Id%>" target="_self"><% =(i + 1).ToString()+"、"+categories[i].Name%></a></dt><dd>
                            <ul>
                                <%foreach (var o in categories[i].Children)
                                  { %>
                                <li><a href="ProductList.aspx?categoryId=<%=o.Id%>"><%=o.Name%></a></li>                               
                                <%} %>
                            </ul>
                        </dd>
                    <%} %>
                </dl>
            </div>
        </div>
    </div>
</asp:Content>
