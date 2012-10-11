<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
    CodeBehind="Details.aspx.cs" Inherits="SINOMA.WebUI.Details" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="asp" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="master-waper-header-bar">
        <h2>
            <a href="Default.aspx">中材装备产品分类 </a>> <a href="ProductList.aspx?categoryId=<%=product.Category.Id %>">
                <%=product.Category.Name %></a> > <a href="#">
                    <%=product.Name%></a>
        </h2>
    </div>
    <div style="padding: 5px 0px 0px 0px; border: 1px solid #c5ddf1;">
        <asp:TabContainer ID="TabContainer1" runat="server" CssClass="grey">
            <asp:TabPanel ID="TabPanel1" runat="server" HeaderText=" 参 数 ">
                <ContentTemplate>                    
                    <div class="product-details-title">
                        <h2>
                            <%=product.Name%>
                            参数细节</h2>
                    </div>
                    <table class="product-details-table">
                        <tbody>
                            <tr>
                                <th rowspan="10">
                                    主要参数
                                </th>
                                <td class="td2">
                                    设备名称
                                </td>
                                <td>
                                    <span>
                                        <%=product.Name%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="td2">
                                    设计日期
                                </td>
                                <td>
                                    <span>
                                        <%=product.DesignDate%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="td2">
                                    图号
                                </td>
                                <td>
                                    <span>
                                        <%=product.PictureNo%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="td2">
                                    规格型号
                                </td>
                                <td>
                                    <span>
                                        <%=product.SpecificationType%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="td2">
                                    用途
                                </td>
                                <td>
                                    <span>
                                        <%=product.Purpose%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="td2">
                                    <span> 生产能力(<%=product.ProduceAbilityUnit.UnitName %>)</span>
                                </td>
                                <td>
                                    <span>
                                        <%=product.ProduceAbility%><%=product.ProduceAbilityRemark %></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="td2">
                                    主电机功率(<%=product.MotorPowerUnit.UnitName%>)
                                </td>
                                <td>
                                    <span>
                                        <%=product.MotorPower%><%=product.MotorPowerRemark%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="td2">
                                    单台设备重量(<%=product.DeviceWeightUnit.UnitName%>)
                                </td>
                                <td>
                                    <span>
                                        <%=product.DeviceWeight%> <%=product.DeviceWeightRemark%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="td2">
                                    制造标准
                                </td>
                                <td>
                                    <span>
                                        <%=product.ManufactureStandard%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="td2">
                                    设计单位
                                </td>
                                <td>
                                    <span>
                                        <%=product.DesignUnit%></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <table class="product-details-table">
                        <tbody>
                            <tr>
                                <th rowspan="100">
                                    动态参数
                                </th>
                            </tr>
                            <% foreach (var attributeValue in product.ExtendedAttributes)
                               {  %>
                            <tr>
                                <td class="td2">
                                    <%=attributeValue.Attribute.Name %>
                                </td>
                                <td>
                                    <span>
                                        <%=attributeValue.Value %></span>
                                </td>
                            </tr>
                            <%} %>
                        </tbody>
                    </table>
                </ContentTemplate>
            </asp:TabPanel>
            <asp:TabPanel ID="tplMarketingRecord" runat="server" HeaderText="营销记录">
                <ContentTemplate>
                    <div class="product-details-title">
                        <h2>
                            <%=product.Name%>
                            营销记录</h2>
                    </div>
                    <table class="product-details-table">
                        <tbody>
                            <tr>
                                <th rowspan="100">
                                    营销记录
                                </th>
                                <td class="td2">
                                    <span>营销编号</span>
                                </td>
                                <td>
                                    <span>销售日期</span>
                                </td>
                                <td>
                                    <span>客户名称</span>
                                </td>
                                <td>
                                    <span>销售数量</span>
                                </td>
                            </tr>
                            <% foreach (var record in product.SaleDetail)
                               {  %>
                            <tr>
                                <td class="td2">
                                    <span>
                                        <%=record.Id%></span>
                                </td>
                                <td>
                                    <span>
                                        <%=record.SaleDate%></span>
                                </td>
                                <td>
                                    <span>
                                        <%=record.Customer%></span>
                                </td>
                                <td>
                                    <span>
                                        <%=record.SaleCount%></span>
                                </td>
                            </tr>
                            <%} %>
                        </tbody>
                    </table>
                </ContentTemplate>
            </asp:TabPanel>
            <asp:TabPanel ID="TabPanel2" runat="server" HeaderText="附 件">
                <ContentTemplate>
                    <div class="product-details-title">
                        <h2>
                            <%=product.Name%>
                            附件</h2>
                    </div>
                    <table class="product-details-table">
                        <tbody>
                            <tr>
                                <th rowspan="8">
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
                </ContentTemplate>
            </asp:TabPanel>
        </asp:TabContainer>
    </div>
</asp:Content>