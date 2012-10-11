<%@ Page Title="产品数据导入" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true"
    CodeBehind="ProductImport.aspx.cs" Inherits="SINOMA.WebUI.Admin.ProductImport" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="asp" %>
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

        function btnImport_Click(o, e) {
            if ($("#gridviewEmptyTip") != null) {
                alert("请先上传数据文件！");
                return false;
            } else {
                return window.confirm("请确认导入的数据和选择的类别是否一致？");
            }
        }
    </script>
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <%--<asp:UpdatePanel runat="server" ID="uplMain">
        <ContentTemplate>--%>
    <div class="master-waper-header-bar">
        <h2>
            产品数据导入</h2>
    </div>
    <div style="margin: 5px 0px 5px 0px; height: 100%;">
        <div style="float: left; width: 190px; border: 1px solid #aac1de; color: #808080;
            height: 415px; padding: 10px; overflow: scroll;">
            <asp:UpdatePanel runat="server">
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
                                <asp:UpdatePanel ID="UpdatePanel1" runat="server">
                                    <ContentTemplate>
                                        <asp:FileUpload runat="server" ID="fldProduct" CssClass="textbox" Height="21" Width="250" />
                                    </ContentTemplate>
                                    <Triggers>
                                        <asp:PostBackTrigger ControlID="btnUpload" />
                                    </Triggers>
                                </asp:UpdatePanel>
                            </td>
                            <td>
                                <asp:UpdatePanel runat="server">
                                    <ContentTemplate>
                                        <a class="button" href="<%=filePath %>" style="width: 70px;" onclick="btnDown_Click(this, event)">
                                            <ins>下载模板</ins></a>
                                    </ContentTemplate>
                                </asp:UpdatePanel>
                            </td>
                            <td>
                                <asp:UpdatePanel runat="server">
                                    <ContentTemplate>
                                        <asp:LinkButton runat="server" ID="btnUpload" CssClass="button" Width="70" OnClick="btnUpload_Click"
                                            OnClientClick="return btnUpload_Click(this,event)"><ins>上传文件</ins></asp:LinkButton>
                                    </ContentTemplate>
                                </asp:UpdatePanel>
                            </td>
                            <td>
                                <asp:UpdatePanel runat="server">
                                    <ContentTemplate>
                                        <asp:LinkButton runat="server" ID="btnImport" CssClass="button" Width="70" 
                                            OnClick="btnImport_Click"><ins>导入数据</ins></asp:LinkButton>
                                    </ContentTemplate>
                                </asp:UpdatePanel>
                            </td>
                            <td>
                                <asp:UpdatePanel runat="server">
                                    <ContentTemplate>
                                        <asp:LinkButton runat="server" ID="btnHelp" CssClass="button" Width="50"><ins>帮助</ins></asp:LinkButton>
                                        <asp:ModalPopupExtender ID="modalHelp" runat="server" BackgroundCssClass="modalbackbround"
                                            PopupControlID="panelHelp" TargetControlID="btnHelp" CancelControlID="btnOK">
                                        </asp:ModalPopupExtender>
                                    </ContentTemplate>
                                </asp:UpdatePanel>
                            </td>
                        </tr>
                    </table>
                </div>
                <div style="float: right; display: none;">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <table>
                                <tr>
                                    <td>
                                        产品类别：<asp:DropDownList runat="server" ID="dplCategory" CssClass="combox" Height="23"
                                            Width="150" OnSelectedIndexChanged="dplCategory_SelectedIndexChanged">
                                        </asp:DropDownList>
                                    </td>
                                </tr>
                            </table>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
            <div style="overflow: scroll; width: auto; height: 400px; border: 1px solid #aac1de;">
                <asp:UpdatePanel runat="server">
                    <ContentTemplate>
                        <asp:GridView runat="server" ID="gvwProduct" EnableModelValidation="True" CssClass="gridview"
                            CellPadding="4" ForeColor="#333333" GridLines="None" HeaderStyle-Wrap="false"
                            RowStyle-Wrap="false">
                            <AlternatingRowStyle BackColor="White" />
                            <EmptyDataRowStyle CssClass="gridview-empty" />
                            <EmptyDataTemplate>
                            <h2 id="gridviewEmptyTip">请上传数据</h2>
                            </EmptyDataTemplate>
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
    <%-- </ContentTemplate>
    </asp:UpdatePanel>--%>
    <asp:Panel runat="server" ID="panelHelp" CssClass="help" Width="600" Style="height: 340px;
        padding: 10px;">
        <div>
            <h3>
                数据导入操作流程</h3>
        </div>
        <div style="padding: 5px; height: 280px;">
            <h4>
                1.下载模板</h4>
            <p>
                &nbsp;&nbsp;&nbsp;&nbsp;选中左边一个类别，<font color="red">末级类别</font>，服务端将会创建数据导入模板，然后点击【下载模板按钮】</p>
            <h4>
                2.编辑数据</h4>
            <p>
                &nbsp;&nbsp;&nbsp;&nbsp;注意一下属性的格式【设计日期】格式应为：2008-09-12或者2008/09/12、【水泥厂配套生产能力】自能是数字、【生产能力】只能是数字、【主电机功率】自能为数字、【单台设备重量】自能是数字</p>
            <h4>
                3.上传数据</h4>
            <p>
                &nbsp;&nbsp;&nbsp;&nbsp;选择本地要上传的数据文件，点击【上传数据】如果数据正确，将会在右边以表格的形式显示，以便检查。</p>
            <h4>
                4.导入数据</h4>
            <p>
                &nbsp;&nbsp;&nbsp;&nbsp;点击【导入数据】将产品数据导入到数据库。</p>
        </div>
        <div style="text-align: center; height: 30px;">
            <asp:LinkButton runat="server" ID="btnOK" CssClass="button" Width="60"><ins>确定</ins></asp:LinkButton></div>
    </asp:Panel>
</asp:Content>
