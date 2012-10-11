<%@ Page Title="营销参数管理" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true"
    CodeBehind="SalesDetail.aspx.cs" Inherits="SINOMA.WebUI.Admin.SalesDetail" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="asp" %>
<%@ Register Assembly="SINOMA.UserControl" Namespace="SINOMA.UserControl" TagPrefix="aspx" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <script type="text/javascript">

        function bindCheckbox() {
            var cbHeader = $(".cbHeader input");
            var cbRowItem = $(".cbRowItem input");
            cbHeader.bind("click", function () {
                cbRowItem.each(function () { this.checked = cbHeader[0].checked; })
            });
            cbRowItem.bind("click", function () { if ($(this).checked == false) cbHeader[0].checked = false; });
        }
        $(function () {
            bindCheckbox();
            Sys.WebForms.PageRequestManager.getInstance().add_endRequest(bindCheckbox);
        })

        $(document).ready(function () {
            $("#aspnetForm").validate({
                debug: true,
                errorPlacement: function (error, element) {
                    error.appendTo(element.parent());
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });
        })

        function dataPick(id) {
            var obj;
            if (id == "signDate") {
                obj = $("#<% =txtSignDate.ClientID  %>");
            } else {
                obj = $("#<% =txtDeliverDate.ClientID  %>");
            }
            obj.datepicker({
                closeText: '关闭',
                prevText: '上月',
                nextText: '下月',
                currentText: '今天',
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
		        '七月', '八月', '九月', '十月', '十一月', '十二月'],
                monthNamesShort: ['一', '二', '三', '四', '五', '六',
		        '七', '八', '九', '十', '十一', '十二'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                weekHeader: '周',
                dateFormat: 'yy-mm-dd',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: true,
                changeYear: true,
                changeMonth: true,
                buttonImage: '../images/calendar.png'
            });
            obj.triggerHandler("focus");
        }

        function confirmDelete() {
            return window.confirm("确定删除选中的所有数据项吗？");
        }

        function isNumber() {
            if (isNaN($("#<%=txtSaleCount.ClientID %>").val())) {
                alert("请输入数字！");
                return false;
            }
        }
    </script>
    <asp:UpdatePanel runat="server" ID="mainUpl">
        <Triggers>
            <asp:AsyncPostBackTrigger ControlID="gvSalesDetailPager" />
        </Triggers>
        <ContentTemplate>
            <div class="master-waper-header-bar">
                <h2>
                    <asp:Label runat="server" ID="lblTitle">营销参数管理</asp:Label></h2>
            </div>
            <div style="margin: 5px 0px 5px 0px; height: 100%;">
                <div id="treeviewDiv" style="float: left; width: 180px; border: 1px solid #aac1de;
                    color: #808080; height: 415px; padding: 10px; overflow-y: scroll;">
                    <asp:TreeView ID="tvwCategory" runat="server" CollapseImageUrl="../images/treeview-collapse.gif"
                        ExpandImageUrl="../images/treeview-expand.gif" OnSelectedNodeChanged="tvwCategory_SelectedNodeChanged"
                        SelectedNodeStyle-BackColor="#688fc9" SelectedNodeStyle-ForeColor="White" NodeWrap="false"
                        ToolTip="选择产品营销参数所属类别">
                    </asp:TreeView>
                </div>
                <div style="float: right; width: 760px;">
                    <div style="height: 158px; border: 1px solid #aac1de; overflow-y: scroll;">
                        <asp:GridView runat="server" ID="gvwProduct" AutoGenerateColumns="False" CssClass="gridview"
                            Width="740" DataKeyNames="Id" ForeColor="#333333" GridLines="None" OnSelectedIndexChanged="gvwProduct_SelectedIndexChanged">
                            <EmptyDataRowStyle CssClass="gridview-empty" />
                            <AlternatingRowStyle BackColor="White" />
                            <Columns>
                                <asp:BoundField DataField="Name" HeaderText="名称" ItemStyle-Width="250"  HeaderStyle-HorizontalAlign="Left"  />
                                <asp:TemplateField HeaderText="规格类型" ItemStyle-Width="200px"  HeaderStyle-HorizontalAlign="Left" >
                                    <ItemTemplate>
                                        <%#Eval("SpecificationType")%>
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="PictureNo" HeaderText="图号"  HeaderStyle-HorizontalAlign="Left"  />
                                <asp:CommandField SelectText="选择" HeaderStyle-Width="40" ShowSelectButton="true"  HeaderStyle-HorizontalAlign="Left" 
                                    HeaderText="选择" />
                            </Columns>
                            <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                            <HeaderStyle BackColor="#688fc9" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                            <RowStyle BackColor="#EFF3FB" />
                            <SelectedRowStyle BackColor="Orange" Font-Bold="True" ForeColor="#333333" />
                        </asp:GridView>
                    </div>
                    <div style="height: 21px; font-size: 16px; font-weight: bold; padding-top: 5px;">
                        产品营销明细</div>
                    <div style="height: 215px; border: 1px solid #aac1de; overflow-y: scroll;">
                        <asp:GridView runat="server" ID="gvSalesDetail" AutoGenerateColumns="False" CssClass="gridview"
                            ForeColor="#333333" GridLines="None" EnableModelValidation="True" OnRowDataBound="gvSalesDetail_RowDataBound">
                            <EmptyDataRowStyle CssClass="gridview-empty" />
                            <AlternatingRowStyle BackColor="White" />
                            <Columns>
                                <asp:TemplateField ItemStyle-Width="5%" >
                                    <HeaderTemplate>
                                        <asp:CheckBox ID="cbSelectAll" runat="server" CssClass="cbHeader" />
                                    </HeaderTemplate>
                                    <ItemTemplate>
                                        <asp:CheckBox ID="cbSalesDetail" runat="server" CssClass="cbRowItem" />
                                        <asp:HiddenField ID="hfSalesDetailId" runat="server" Value='<%# Eval("Id") %>' />
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" Width="5%" />
                                </asp:TemplateField>
                                <asp:BoundField DataField="SignDate" HeaderText="签订时间" ItemStyle-Width="150" HeaderStyle-HorizontalAlign="Left" >
                                </asp:BoundField>
                                <asp:BoundField DataField="Customer" HeaderText="客户名称" ItemStyle-Width="180" HeaderStyle-HorizontalAlign="Left" >
                                <ItemStyle Width="180px" />
                                </asp:BoundField>
                                <asp:BoundField DataField="SaleCount" HeaderText="销售数量" HeaderStyle-HorizontalAlign="Left"  />
                                <asp:TemplateField HeaderText="编辑" HeaderStyle-Width="55"  >
                                    <ItemTemplate>
                                        <asp:HiddenField ID="hfSalesDetail" runat="server" Value='<%# Eval("Id") %>' />
                                        <asp:LinkButton runat="server" ID="btnEdit" CommandArgument='<%# Eval("Id") %>' OnClick="btnEdit_Click"
                                            CssClass="button" Width="50"><ins>编 辑</ins></asp:LinkButton>
                                    </ItemTemplate>
                                    <HeaderStyle Width="55px" />
                                </asp:TemplateField>
                            </Columns>
                            <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                            <HeaderStyle BackColor="#688fc9" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                            <RowStyle BackColor="#EFF3FB" />
                            <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                        </asp:GridView>
                    </div>
                    <div>
                        <div style="height: 30px; margin-top: 5px; width: 200px; float: left;">
                            <asp:DropDownList runat="server" AutoPostBack="true" ID="ddlProducts" Width="160px"
                                OnSelectedIndexChanged="ddlProducts_SelectedIndexChanged" Height="23" Style="padding: 2px;"
                                CssClass="textbox" Visible="false">
                            </asp:DropDownList>
                            <asp:LinkButton runat="server" ID="btnAdd" CssClass="button" Width="90" OnClick="btnAdd_Click"><ins>新增营销记录</ins></asp:LinkButton>
                            <asp:LinkButton ID="btnDelete" runat="server" CssClass="button" Width="90" OnClick="btnDelete_Click"
                                OnClientClick="return confirmDelete()"><ins>删除营销记录</ins></asp:LinkButton>
                            <%--当前操作产品：<font style="font-weight: bold; font-size: 14px;" color="red"><label id="lblProductTip"
                            runat="server"></label></font>--%>
                        </div>
                        <div style="height: 30px; width: 480px; float: right;">
                            <aspx:GridViewPager runat="server" ID="gvSalesDetailPager" PageSize="10" OnPageIndexChanged="gvSalesDetail_PageIndexChanged"
                                IconImageUrl="../images/gridview-pager.png" />
                        </div>
                    </div>
                </div>
            </div>
            <asp:HiddenField ID="controlID" runat="server" />
            <asp:ModalPopupExtender ID="modalSaleDetailAdd" runat="server" BackgroundCssClass="modalbackbround"
                PopupControlID="pnlSaleDetailAdd" TargetControlID="controlID" CancelControlID="btnCancel">
            </asp:ModalPopupExtender>
        </ContentTemplate>
    </asp:UpdatePanel>
    <asp:Panel runat="server" ID="pnlSaleDetailAdd" Width="500" Height="350" CssClass="modalpopup"
        BackColor="#FFFFFF">
        <asp:UpdatePanel runat="server" ID="uplEdit">
            <ContentTemplate>
                <div class="tbar">
                    <h3 style="color: White">
                        <asp:Label ID="lblBarTitle" runat="server"></asp:Label></h3>
                </div>
                <div class="sales-waper-edit-form" style="border: none;">
                    <table>
                        <tr>
                            <th>
                                合同号：
                            </th>
                            <td>
                                <asp:TextBox runat="server" ID="txtContractNo" class="required"></asp:TextBox>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                图号：
                            </th>
                            <td>
                                <asp:TextBox runat="server" ID="txtPictureNo" class="required"></asp:TextBox>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                客户名称：
                            </th>
                            <td>
                                <asp:TextBox runat="server" class="required" ID="txtCustomer"></asp:TextBox>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                签订时间：
                            </th>
                            <td>
                                <asp:TextBox runat="server" class="dateISO" ID="txtSignDate"></asp:TextBox>
                                <img alt="calendar" id="signDate" style="cursor: pointer" src="../images/calendar.png" onclick="dataPick(this.id)" />
                            </td>
                        </tr>

                        <tr>
                            <th>
                                交货期：
                            </th>
                            <td>
                                <asp:TextBox runat="server" class="dateISO" ID="txtDeliverDate"></asp:TextBox>
                                 <img alt="calendar" id="deliverDate" style="cursor: pointer" src="../images/calendar.png" onclick="dataPick(this.id)" />
                            </td>
                        </tr>
                        <tr>
                            <th>
                                数量：
                            </th>
                            <td>
                                <asp:TextBox runat="server" class="number" ID="txtSaleCount"></asp:TextBox>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                签订单位：
                            </th>
                            <td>
                                <asp:TextBox runat="server" class="required" ID="txtSignUnit"></asp:TextBox>
                            </td>
                        </tr>
                    </table>
                </div>
                <div style="float: right; margin-right:50px;">
                    <asp:LinkButton ID="btnSave" runat="server" OnClientClick="return isNumber()" CssClass="button"
                        Width="60" OnClick="btnSave_Click"><ins>保 存</ins></asp:LinkButton>
                    <asp:LinkButton ID="btnCancel" runat="server" CssClass="button" Width="60"><ins>取 消</ins></asp:LinkButton>
                </div>
            </ContentTemplate>
        </asp:UpdatePanel>
    </asp:Panel>
</asp:Content>
