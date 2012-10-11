<%@ Page Title="产品列表" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true"
    CodeBehind="Products.aspx.cs" Inherits="SINOMA.WebUI.Admin.Products" %>

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

        function confirmDelete() {
            return window.confirm("确定删除选中的所有数据项吗？");
        }
    </script>
    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
        <Triggers>
            <asp:AsyncPostBackTrigger ControlID="gvProductsPager" />
        </Triggers>
        <ContentTemplate>
            <div class="master-waper-header-bar">
                <h2>
                    <asp:Label runat="server" ID="lblTitle">产品管理</asp:Label></h2>
            </div>
            <div style="margin: 5px 0px 5px 0px; height: 100%;">
                <div style="float: left; width: 200px; border: 1px solid #aac1de; color: #808080;
                    height: 415px; padding: 10px; overflow-y: scroll;">
                    <asp:TreeView ID="tvwCategory" runat="server" CollapseImageUrl="../images/treeview-collapse.gif"
                        ExpandImageUrl="../images/treeview-expand.gif" OnSelectedNodeChanged="tvwCategory_SelectedNodeChanged"
                        SelectedNodeStyle-BackColor="#688fc9" SelectedNodeStyle-ForeColor="White" NodeWrap="false"
                        ToolTip="鼠标点击选择类别">
                    </asp:TreeView>
                </div>
                <div id="divOuter" runat="server" style="float: right; width: 750px; height:415px">
                    <div id="divAddAndDeleteHeader" runat="server" style="height: 30px; padding: 5px;">
                        <asp:HyperLink runat="server" ID="lbtnAdd" NavigateUrl="~/Admin/ProductAdd.aspx"
                            CssClass="button" Width="60"><ins>添加产品</ins></asp:HyperLink>
                        <asp:LinkButton ID="lbtnDelete" runat="server" CssClass="button" Width="60" OnClientClick="return confirmDelete()"
                            OnClick="lbtnDelete_Click"><ins>删 除</ins></asp:LinkButton>
                        <asp:HyperLink runat="server" ID="lbtnExport" NavigateUrl="#" Visible="false" ToolTip="导出当前类别所有产品数据"
                            Target="_self" CssClass="button" Width="70"><ins>导出数据</ins></asp:HyperLink>
                    </div>
                    <div style="width: 100%;">
                        <aspx:GridViewPager runat="server" ID="gvProductsPager" PageSize="14" OnPageIndexChanged="gvProducts_PageIndexChanged"
                            IconImageUrl="../images/gridview-pager.png" />
                    </div>
                    <div id="divGvProducts" runat="server" style="overflow-y: scroll; height: 89%; border: 1px solid #aac1de; width: 100%;">
                        <asp:GridView ID="gvProducts" runat="server" CssClass="gridview" AutoGenerateColumns="false"
                            PageSize="15" Width="733px">
                            <AlternatingRowStyle BackColor="White" />
                            <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                            <HeaderStyle BackColor="#688fc9" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                            <RowStyle BackColor="#EFF3FB" />
                            <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                            <Columns>
                                <asp:TemplateField ItemStyle-Width="5%" ItemStyle-HorizontalAlign="Center">
                                    <HeaderTemplate>
                                        <asp:CheckBox ID="cbSelectAll" runat="server" CssClass="cbHeader" />
                                    </HeaderTemplate>
                                    <ItemTemplate>
                                        <asp:CheckBox ID="cbProduct" runat="server" CssClass="cbRowItem" />
                                        <asp:HiddenField ID="hfProductId" runat="server" Value='<%# Eval("Id") %>' />
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="名称" >
                                    <ItemTemplate>
                                        <%#Eval("Name")%>
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="规格类型" ItemStyle-Width="200px">
                                    <ItemTemplate>
                                        <%#Eval("SpecificationType")%>
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="图号" ItemStyle-Width="120px">
                                    <ItemTemplate>
                                        <%#Eval("PictureNo")%>
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="详细属性" HeaderStyle-HorizontalAlign="Center" ItemStyle-Width="52px"
                                    ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <a href="../ProductDetail.aspx?productId=<%#Eval("Id")%>" title="详细属性">详细属性 </a>
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="编辑" HeaderStyle-HorizontalAlign="Center" ItemStyle-Width="30px"
                                    ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <a href="ProductAdd.aspx?Id=<%#Eval("Id")%>" title="编辑">编辑 </a>
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="营销记录管理" HeaderStyle-HorizontalAlign="Center" ItemStyle-Width="80px"
                                    ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <a href="SalesDetail.aspx?productId=<%#Eval("Id")%>" title="添加营销记录">营销记录管理 </a>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                    </div>
                </div>
            </div>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
