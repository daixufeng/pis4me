<%@ Page Title="日志管理" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true"
    CodeBehind="LogMgt.aspx.cs" Inherits="SINOMA.WebUI.Admin.LogMgt" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="asp" %>
<%@ Register Assembly="SINOMA.UserControl" Namespace="SINOMA.UserControl" TagPrefix="aspx" %>
<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        function EndRequestHandler() {
            $("#<%=txtBegDate.ClientID %>").datepicker({ dateFormat: 'yy-mm-dd' });
            $("#<%=txtEndDate.ClientID %>").datepicker({ dateFormat: 'yy-mm-dd' });
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
        <asp:AsyncPostBackTrigger ControlID="gvwLogPager" />
        </Triggers>
        <ContentTemplate>
            <div class="master-waper-header-bar">
                <h2>
                    <asp:Label runat="server" ID="lblTitle" Text="日志管理"></asp:Label></h2>
            </div>
            <div style="margin: 5px 0px 5px 0px; height: 100%;">
                <div style="height: 30px; padding: 5px;">
                    开始时间：<asp:TextBox runat="server" ID="txtBegDate" CssClass="textbox"></asp:TextBox>
                    结束时间：<asp:TextBox runat="server" ID="txtEndDate" CssClass="textbox"></asp:TextBox>
                    日志类别：<asp:DropDownList runat="server" ID="dplLogType" Height="23" Style="padding: 2px;"
                        CssClass="combox">
                        <asp:ListItem Value="All" Text="请选择类型"></asp:ListItem>
                        <asp:ListItem Value="Insert" Text="新增"></asp:ListItem>
                        <asp:ListItem Value="Update" Text="修改"></asp:ListItem>
                        <asp:ListItem Value="Delete" Text="删除"></asp:ListItem>
                    </asp:DropDownList>
                    <asp:LinkButton runat="server" ID="btnSearch" CssClass="button" Width="60" OnClick="btnSearch_Click"><ins>查 询</ins></asp:LinkButton>
                </div>
                <div style="height: 28px; position: relative;">
                    <aspx:GridViewPager runat="server" ID="gvwLogPager" PageSize="15" OnPageIndexChanged="gvwLog_PageIndexChanged"
                        IconImageUrl="../images/gridview-pager.png" />
                </div>
                <div style="height: 388px; border: 1px solid #aac1de;">
                    <asp:GridView runat="server" ID="gvwLog" EnableModelValidation="True" AutoGenerateColumns="False" CssClass="gridview"
                        OnRowDataBound="gvwLog_RowDataBound"  CellPadding="4" ForeColor="#333333" GridLines="None">
                        <EmptyDataRowStyle CssClass="gridview-empty" />
                        <AlternatingRowStyle BackColor="White" />
                        <EmptyDataTemplate>
                            日志类别</td>
                            <td>
                                内容
                            </td>
                            <td>
                                操作员
                            </td>
                            <td>
                                操作时间
                            </td>
                            </tr>
                            <tr>
                                <td colspan="6">
                                    <span>没有结果</span>
                                </td>
                        </EmptyDataTemplate>
                        <Columns>
                            <asp:BoundField DataField="OperationType" HeaderText="日志类别" ItemStyle-Width="100" ItemStyle-HorizontalAlign="Center" />
                            <asp:BoundField DataField="Message" HeaderText="内容" ItemStyle-HorizontalAlign="Left" />
                            <asp:BoundField DataField="Operator" HeaderText="操作员" ItemStyle-Width="80" ItemStyle-HorizontalAlign="Center" />
                            <asp:BoundField DataField="CreateTime" HeaderText="操作时间" ItemStyle-Width="80" ItemStyle-HorizontalAlign="Center" />
                        </Columns>
                        <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                        <HeaderStyle BackColor="#688fc9" Font-Bold="True" ForeColor="White" />
                        <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                        <RowStyle BackColor="#EFF3FB" />
                        <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                    </asp:GridView>
                </div>
            </div>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
