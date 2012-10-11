<%@ Page Title="营销数据导入" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true"
    CodeBehind="SalesDataImport.aspx.cs" Inherits="SINOMA.WebUI.Admin.SalesDataImport" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
<script type="text/javascript">
    function btnDown_Click(o, e) {
        if ($(o).attr("href") == "#" || $(o).attr("href") == "") {
            alert("请选择左边的类别！");
        }
    }

    function btnUpload_Click(o, e) {
        if ($("#<%=fldProduct.ClientID %>").val() == "") {
            alert("请添加文件！");
            return false;
        }
    }
    </script>
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="master-waper-header-bar">
        <h2>
            营销数据导入</h2>
    </div>
    <div style="margin: 5px 0px 5px 0px; height: 100%;">
        <div style="float: left; width: 190px; border: 1px solid #aac1de; color: #808080;
            height: 415px; padding: 10px; overflow: scroll;">
            <asp:UpdatePanel ID="UpdatePanel1" runat="server">
                <ContentTemplate>
                    <asp:TreeView ID="tvwCategory" runat="server" CollapseImageUrl="../images/treeview-collapse.gif"
                        ExpandImageUrl="../images/treeview-expand.gif" OnSelectedNodeChanged="tvwCategory_SelectedNodeChanged"
                        SelectedNodeStyle-BackColor="#688fc9" SelectedNodeStyle-ForeColor="White" NodeWrap="false">
                    </asp:TreeView>
                </ContentTemplate>
            </asp:UpdatePanel>
        </div>
        <div style="float: right; width: 760px; height: 415px;">            
            <div style="height: 30px; padding: 0px 0px 5px 0px;">
                <div style="float: left; width: 530px;">
                    <table>
                        <tr>
                            <td>
                                <asp:UpdatePanel ID="UpdatePanel2" runat="server">
                                    <ContentTemplate>
                                        <asp:FileUpload runat="server" ID="fldProduct" CssClass="textbox" Height="21" Width="250" />
                                    </ContentTemplate>
                                    <Triggers>
                                        <asp:PostBackTrigger ControlID="btnUpload" />
                                    </Triggers>
                                </asp:UpdatePanel>
                            </td>
                            <td>
                                <asp:UpdatePanel ID="UpdatePanel3" runat="server">
                                    <ContentTemplate>
                                        <a class="button" href="<%=filePath %>" style="width: 70px;" onclick="btnDown_Click(this, event)">
                                            <ins>下载模板</ins></a>
                                            </ContentTemplate>
                                </asp:UpdatePanel>
                            </td>
                            <td>
                                <asp:UpdatePanel ID="UpdatePanel4" runat="server">
                                    <ContentTemplate>
                                        <asp:LinkButton runat="server" ID="btnUpload" CssClass="button" Width="70" 
                                            OnClick="btnUpload_Click" OnClientClick="return btnUpload_Click(this,event)"><ins>上传文件</ins></asp:LinkButton>
                                    </ContentTemplate>
                                </asp:UpdatePanel>
                            </td>
                            <td>
                                <asp:UpdatePanel ID="UpdatePanel5" runat="server">
                                    <ContentTemplate>
                                        <asp:LinkButton runat="server" ID="btnImport" CssClass="button" Width="70" OnClick="btnImport_Click"><ins>导入数据</ins></asp:LinkButton>
                                    </ContentTemplate>
                                </asp:UpdatePanel>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div style="overflow: scroll; width: auto; height: 400px; border: 1px solid #aac1de;">
                <asp:UpdatePanel ID="UpdatePanel8" runat="server">
                    <ContentTemplate>
                        <asp:GridView runat="server" ID="gvwSalesDetail" EnableModelValidation="True" CssClass="gridview"
                            CellPadding="4" ForeColor="#333333" GridLines="None" HeaderStyle-Wrap="false"
                            RowStyle-Wrap="false">
                            <AlternatingRowStyle BackColor="White" />
                            <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                            <HeaderStyle BackColor="#688fc9" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                            <RowStyle BackColor="#EFF3FB" />
                            <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                        </asp:GridView>
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>
</asp:Content>
