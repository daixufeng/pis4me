<%@ Page Title="产品销售记录查询" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
    CodeBehind="ProductSalesDetail.aspx.cs" Inherits="SINOMA.WebUI.ProductSalesDetail" %>

    <%@ Register Assembly="SINOMA.UserControl" Namespace="SINOMA.UserControl" TagPrefix="aspx" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
<script type="text/javascript">
    function EndRequestHandler() {
        $.datepicker.regional['zh-CN'] = {
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
            buttonImage: 'images/calendar.png'
        };
        $("#<%=txtBegDate.ClientID %>").datepicker($.datepicker.regional['zh-CN']);
        $("#<%=txtEndDate.ClientID %>").datepicker($.datepicker.regional['zh-CN']);
    }

    $(function () {
        EndRequestHandler();
        Sys.WebForms.PageRequestManager.getInstance().add_endRequest(EndRequestHandler);
    })

    </script>
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <asp:UpdatePanel runat="server" ID="uplMain">
     <Triggers>
            <asp:AsyncPostBackTrigger ControlID="gvSalesDetailPager" />
        </Triggers>
        <ContentTemplate>
        <div class="master-waper-header-bar">
                <h2>
                    <asp:Label runat="server" ID="lblTitle">产品销售记录查询</asp:Label></h2>
            </div>
         <div style="margin: 5px 0px 5px 0px; height: 100%;">
        <div style="float: left; width: 200px; border: 1px solid #aac1de; color: #808080; height: 415px; padding: 10px; overflow-y: scroll;">
            <asp:TreeView ID="tvwCategory" runat="server" ShowCheckBoxes="Leaf" CollapseImageUrl="../images/treeview-collapse.gif"
                ExpandImageUrl="../images/treeview-expand.gif"
                SelectedNodeStyle-BackColor="#688fc9" SelectedNodeStyle-ForeColor="White" NodeWrap="false"
                ToolTip="选择产品营销记录所属类别">
            </asp:TreeView>
           </div>
            <div style="width: 750px; height: 100%; float:right;">
                 <div style="float: left;  height: 30px; padding: 2px;">
                    开始时间：<asp:TextBox runat="server" ID="txtBegDate" CssClass="textbox"></asp:TextBox>
                    截止时间：<asp:TextBox runat="server" ID="txtEndDate" CssClass="textbox"></asp:TextBox>
                    <asp:LinkButton runat="server" ID="btnSearch" CssClass="button" Width="60" OnClick="btnSearch_Click"><ins>查 询</ins></asp:LinkButton>
                    <asp:LinkButton runat="server" ID="btnClear" CssClass="button" Width="60" OnClick="btnClear_Click"><ins>清 除</ins></asp:LinkButton>
                </div>
                <div style="height: 30px; width:100%; float:left;" >
                <div style="height: 30px; padding:5px 0px 5px 2px; width:49%; float:left;">
                销售总数：<font style="color:red; font-weight:bold;";><asp:Label ID="lblSalesSum" runat="server"></asp:Label></font>
                </div>
                <div style="height: 30px; width:50%; float:left; position: relative;">
                    <aspx:gridviewpager runat="server" id="gvSalesDetailPager" pagesize="12" onpageindexchanged="gvSalesDetail_PageIndexChanged"
                        iconimageurl="../images/gridview-pager.png" />
                </div>
                </div>
                  <div style="overflow-y: scroll; height: 364px;border: 1px solid #aac1de; width:100%; margin-top:2px; ">
                    <asp:GridView runat="server" ID="gvSalesDetail" EnableModelValidation="True" AutoGenerateColumns="False" CssClass="gridview"
                          CellPadding="4" ForeColor="#333333"  GridLines="None" OnRowDataBound="gvSalesDetail_RowDataBound" Width="733px">
                        <EmptyDataRowStyle CssClass="gridview-empty" />
                        <AlternatingRowStyle BackColor="White" />
                        <Columns>
                            <asp:BoundField DataField="Name" HeaderText="产品名称"  ItemStyle-Width="150" ItemStyle-HorizontalAlign="Left" />
                             <asp:TemplateField HeaderText="规格类型" ItemStyle-Width="200px" ItemStyle-HorizontalAlign="Left" >
                                    <ItemTemplate>
                                        <%#Eval("SpecificationType")%>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            <asp:BoundField DataField="PictureNo" HeaderText="图号" ItemStyle-Width="100" ItemStyle-HorizontalAlign="Left" />
                            <asp:BoundField DataField="DesignUnit" HeaderText="设计单位" ItemStyle-Width="100" ItemStyle-HorizontalAlign="Left" />
                            <asp:BoundField DataField="SaleDetail" HeaderText="销售数量" ItemStyle-Width="45" ItemStyle-HorizontalAlign="Left" />
                        </Columns>
                        <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                        <HeaderStyle BackColor="#688fc9" Font-Bold="True" ForeColor="White" />
                        <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                        <RowStyle BackColor="#EFF3FB" />
                        <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                    </asp:GridView>
                </div>
            </div>
            </div>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
