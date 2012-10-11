<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
    CodeBehind="ProductDetail.aspx.cs" Inherits="SINOMA.WebUI.ProductDetail" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $("#panelContainer div").each(function (index, domElm) {
                $(domElm).hide();
                if (index == 0) {
                    $(domElm).show();
                }
            })
        });

        function showAndHideDiV(id) {
            var elmIndex = 0;
            $("#tabContainer a").each(function (index, domElm) {
                $(domElm).removeClass("waper-tab-active");
                $(domElm).addClass("waper-tab-inactive");
                if ($(domElm).attr("id") == id) {
                    elmIndex = index;
                    $(domElm).removeClass("waper-tab-inactive");
                    $(domElm).addClass("waper-tab-active");
                }
            });

            $("#panelContainer div").each(function (index, domElm) {
                $(domElm).hide();
                if (index == elmIndex) {
                    $(domElm).show();
                }
            })
            return false;
        }

        function GetTabs() {
            return [$("#<%=lbtnCommon.ClientID %>"), $("#<%=lbtnDesign.ClientID %>"), $("#<%=lbtnSale.ClientID %>"),
            $("#<%=lbtnProduce.ClientID %>"), $("#<%=lbtn.ClientID %>"), $("#<%=lbtnAttachment.ClientID %>")];
        }

        function GetPanels() {
            return [$("#<%=pnlCommon.ClientID %>"), $("#<%=pnlDesign.ClientID %>"), $("#<%=pnlSale.ClientID %>"),
            $("#<%=pnlProduce.ClientID %>"), $("#<%=pnl.ClientID %>"), $("#<%=pnlAttachment.ClientID %>")];
        }

        function minusAndPlus(id) {
            if ($("#" + id + " a").first().hasClass("plus")) {
                $(".tr" + id).show();
                $("#" + id + " a").first().removeClass("plus");
                $("#" + id + " a").first().addClass("minus");
            } else {
                $(".tr" + id).hide();
                $("#" + id + " a").first().removeClass("minus");
                $("#" + id + " a").first().addClass("plus");

            }
        }
    </script>
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="master-waper-header-bar">
        <h2>
            <a href="Default.aspx">中材装备产品分类 </a>> <a href="ProductList.aspx?categoryId=<%=product.Category.Id %>">
                <%=product.Category.Name %></a> > <a href="#">
                    <%=product.Name%></a>
        </h2>
    </div>
    <div id="tabContainer" class="waper-tab-container">
        <asp:LinkButton runat="server" ID="lbtnCommon" CssClass="waper-tab-active" OnClientClick="return showAndHideDiV(this.id)">
            通用参数</asp:LinkButton>
        <asp:LinkButton runat="server" ID="lbtnDesign" CssClass="waper-tab-inactive" OnClientClick="return showAndHideDiV(this.id)">
                设计参数</asp:LinkButton>
        <asp:LinkButton runat="server" ID="lbtnSale" CssClass="waper-tab-inactive" OnClientClick="return showAndHideDiV(this.id)">
                营销记录</asp:LinkButton>
        <asp:LinkButton runat="server" ID="lbtnProduce" CssClass="waper-tab-inactive" OnClientClick="return showAndHideDiV(this.id)">
                制造参数</asp:LinkButton>
        <asp:LinkButton runat="server" ID="lbtn" CssClass="waper-tab-inactive" OnClientClick="return showAndHideDiV(this.id)">
                备品备件</asp:LinkButton>
        <asp:LinkButton runat="server" ID="lbtnAttachment" CssClass="waper-tab-inactive"
            OnClientClick="return showAndHideDiV(this.id)">
                附件</asp:LinkButton>
    </div>
    <div id="panelContainer">
        <asp:Panel runat="server" ID="pnlCommon">
            <%-- <h2 class="product-details-title">
                通用参数</h2>--%>
            <table class="product-details-table">
                <tbody>
                    <th colspan="3">
                        <label id='Label1' onclick="minusAndPlus(this.id);" class="groupLable">
                            <a class="minus"></a><a style="margin-left: 2px;">常用参数 </a>
                        </label>
                    </th>
                    <tr class="trLabel1">
                        <td class="td2">
                            设备名称
                        </td>
                        <td>
                            <span>
                                <%=product.Name%></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            设计日期
                        </td>
                        <td>
                            <span>
                                <%=String.Format("{0:yyyy-MM-dd}",product.DesignDate)%></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            规格型号
                        </td>
                        <td>
                            <span>
                                <%=product.SpecificationType%></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            <span>生产能力<%=string.IsNullOrEmpty(product.ProduceAbilityUnit.UnitName.Trim()) ? product.ProduceAbilityUnit.UnitName : "(" + product.ProduceAbilityUnit.UnitName + ")"%></span>
                        </td>
                        <td>
                            <span>
                                <%=product.ProduceAbility%></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            <span>水泥厂配套生产能力<%=string.IsNullOrEmpty(product.ProduceAbilityUnit.UnitName.Trim()) ? product.ProduceAbilityUnit.UnitName : "(" + product.ProduceAbilityUnit.UnitName + ")"%></span>
                        </td>
                        <td>
                            <span>
                                <%=product.CementAbility%></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            主电机功率<%=string.IsNullOrEmpty(product.MotorPowerUnit.UnitName.Trim()) ? product.MotorPowerUnit.UnitName : "(" + product.MotorPowerUnit.UnitName + ")"%>
                        </td>
                        <td>
                            <span>
                                <%=product.MotorPower%></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            图号
                        </td>
                        <td>
                            <span>
                                <%=product.PictureNo%></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            设计单位
                        </td>
                        <td>
                            <span>
                                <%=product.DesignUnit%></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            用途
                        </td>
                        <td>
                            <span>
                                <%=product.Purpose%><%=product.PurposeRemark %></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            单台设备重量<%=string.IsNullOrEmpty(product.DeviceWeightUnit.UnitName.Trim()) ? product.DeviceWeightUnit.UnitName : "(" + product.DeviceWeightUnit.UnitName + ")"%>
                        </td>
                        <td>
                            <span>
                                <%=product.DeviceWeight%></span>
                        </td>
                    </tr>
                    <tr class="trLabel1">
                        <td class="td2">
                            制造标准
                        </td>
                        <td>
                            <span>
                                <%=product.ManufactureStandard%></span>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table class="product-details-table">
                <tbody>
                    <% foreach (var g in commonGroup)
                       { %>
                    <th colspan="3">
                        <label id='<%=g.Replace(" ","")%><%=commonGroup.IndexOf(g) %>' onclick="minusAndPlus(this.id);"
                            class="groupLable">
                            <a class="minus"></a><a style="margin-left: 2px;">
                                <%=g%>
                            </a>
                        </label>
                    </th>
                    <% foreach (var attributeValue in commonExAttrValues)
                       {
                           if (attributeValue.Attribute.Group == g)
                           {  %>
                    <tr class="tr<%=g.Replace(" ","")%><%=commonGroup.IndexOf(g) %>">
                        <td class="td2" style="width:300px;">
                            <%=attributeValue.Attribute.DisplayName%>
                        </td>
                        <td>
                            <span>
                                <%=attributeValue.Value%></span>
                        </td>
                        <%} %>
                    </tr>
                    <%} %>
                    <%} %>
                </tbody>
            </table>
        </asp:Panel>
        <asp:Panel runat="server" ID="pnlDesign">
            <%--  <h2 class="product-details-title">
                设计参数</h2>--%>
            <table class="product-details-table">
                <tbody>
                    <% foreach (var g in designGroup)
                       { %>
                    <th colspan="2">
                        <label id='<%=g.Replace(" ","")%><%=designGroup.IndexOf(g) %>' onclick="minusAndPlus(this.id);"
                            class="groupLable">
                            <a class="minus"></a><a style="margin-left: 2px;">
                                <%=g%>
                            </a>
                        </label>
                    </th>
                    <% foreach (var attributeValue in designExAttrValues)
                       {
                           if (attributeValue.Attribute.Group == g)
                           {  %>
                    <tr class="tr<%=g.Replace(" ","")%><%=designGroup.IndexOf(g) %>">
                        <td class="td2" style="width:300px;">
                            <%=attributeValue.Attribute.DisplayName%>
                        </td>
                        <td>
                            <span>
                                <%=attributeValue.Value%></span>
                        </td>
                        <%} %>
                    </tr>
                    <%} %>
                    <% }%>
                </tbody>
            </table>
        </asp:Panel>
        <asp:Panel runat="server" ID="pnlSale">
            <%-- <h2 class="product-details-title">
                营销参数</h2>--%>
            <table class="product-details-table">
                <tbody>
                    <tr>
                        <th rowspan="100">
                            营销记录
                        </th>
                        <td class="">
                            <span>合同号</span>
                        </td>
                        <td>
                            <span>图号</span>
                        </td>
                        <td>
                            <span>客户名称</span>
                        </td>
                        <td>
                            <span>签订日期</span>
                        </td>
                        <td>
                            <span>交货期</span>
                        </td>
                        <td>
                            <span>数量</span>
                        </td>
                        <td>
                            <span>签订单位</span>
                        </td>
                    </tr>
                    <% foreach (var record in product.SaleDetail)
                       {  %>
                    <tr>
                        <td class="">
                            <span>
                                <%=record.ContractNo%></span>
                        </td>
                        <td>
                            <span>
                                <%=record.PictureNo%></span>
                        </td>
                        <td>
                            <span>
                                <%=record.Customer%></span>
                        </td>
                        <td>
                            <span>
                                <%=String.Format("{0:yyyy-MM-dd}", record.SignDate)%></span>
                        </td>
                        <td>
                            <span>
                                <%=String.Format("{0:yyyy-MM-dd}", record.DeliverDate)%></span>
                        </td>
                        <td>
                            <span>
                                <%=record.SaleCount%></span>
                        </td>
                        <td>
                            <span>
                                <%=record.SignUnit%></span>
                        </td>
                    </tr>
                    <%} %>
                </tbody>
            </table>
        </asp:Panel>
        <asp:Panel runat="server" ID="pnlProduce">
            <%--  <h2 class="product-details-title">
                制造参数</h2>--%>
            <table class="product-details-table">
                <tbody>
                    <% foreach (var g in produceGroup)
                       { %>
                    <th colspan="2">
                        <label id='<%=g.Replace(" ","")%><%=produceGroup.IndexOf(g) %>' onclick="minusAndPlus(this.id);"
                            class="groupLable">
                            <a class="minus"></a><a style="margin-left: 2px;">
                                <%=g%>
                            </a>
                        </label>
                    </th>
                    <% foreach (var attributeValue in produceExAttrValues)
                       {
                           if (attributeValue.Attribute.Group == g)
                           {  %>
                    <tr class="tr<%=g.Replace(" ","")%><%=produceGroup.IndexOf(g) %>">
                        <td class="td2" style="width:300px;">
                            <%=attributeValue.Attribute.DisplayName%>
                        </td>
                        <td>
                            <span>
                                <%=attributeValue.Value%></span>
                        </td>
                        <%} %>
                    </tr>
                    <%} %>
                    <%} %>
                </tbody>
            </table>
        </asp:Panel>
        <asp:Panel runat="server" ID="pnl">
            <%-- <h2 class="product-details-title">
                备品备件</h2>--%>
            <table class="product-details-table">
                <tbody>
                    <% foreach (var g in spareGroup)
                       { %>
                    <th colspan="2">
                        <label id='<%=g.Replace(" ","")%><%=spareGroup.IndexOf(g) %>' onclick="minusAndPlus(this.id);"
                            class="groupLable">
                            <a class="minus"></a><a style="margin-left: 2px;">
                                <%=g%>
                            </a>
                        </label>
                    </th>
                    <% foreach (var attributeValue in spareExAttrValues)
                       {
                           if (attributeValue.Attribute.Group == g)
                           {  %>
                    <tr class="tr<%=g.Replace(" ","")%><%=spareGroup.IndexOf(g) %>">
                        <td class="td2" style="width:300px;">
                            <%=attributeValue.Attribute.DisplayName%>
                        </td>
                        <td>
                            <span>
                                <%=attributeValue.Value%></span>
                        </td>
                        <%} %>
                    </tr>
                    <%} %>
                    <%} %>
                </tbody>
            </table>
        </asp:Panel>
        <asp:Panel runat="server" ID="pnlAttachment">
            <%-- <h2 class="product-details-title">
                附 件</h2>--%>
            <table class="product-details-table">
                <tbody>
                    <tr>
                        <th rowspan="<%=product.Attachments.Count + 1 %>">
                            附件
                        </th>
                        <td class="td2">
                            No.
                        </td>
                        <td>
                            <span>名称</span>
                        </td>
                    </tr>
                    <% foreach (var attachment in product.Attachments)
                       {  %>
                    <tr>
                        <td class="td2">
                            <span>
                                <%=attachment.Id%></span>
                        </td>
                        <td>
                            <span><a href="<%=rootPath %><%=attachment.NewName %>">
                                <%=attachment.OriginalName%></a></span>
                        </td>
                    </tr>
                    <%} %>
                </tbody>
            </table>
        </asp:Panel>
    </div>
</asp:Content>
