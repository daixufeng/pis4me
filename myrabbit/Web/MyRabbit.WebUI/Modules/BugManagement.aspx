<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
    CodeBehind="BugManagement.aspx.cs" Inherits="MyRabbit.WebUI.BugList" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="content">
        <div class="cph">
            <div class="section-title">
                <img alt="Dashboard" src="/images/ico-dashboard.png" />Bug管理
            </div>
            <div class="section-body" style="height:470px;width:auto;">
                <div style="float: left; width: 20%; height: 465px; border:1px solid #D7D7D7;">
                    <asp:TreeView ID="tvwCategory" runat="server">
                    </asp:TreeView>
                </div>
                <div style="float: right; height: 465px; width:79%; border:0px solid #D7D7D7;">
                    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
                        <Triggers>
                            <asp:AsyncPostBackTrigger ControlID="gvwUserPager" />
                        </Triggers>
                        <ContentTemplate>
                            <table class="table-container">
                                <tr>
                                    <th>
                                        Nick Name:
                                    </th>
                                    <td>
                                        <asp:TextBox runat="server" CssClass="textBox" ID="NickName"></asp:TextBox>
                                    </td>
                                    <th>
                                        Email:
                                    </th>
                                    <td>
                                        <asp:TextBox runat="server" CssClass="textBox" ID="Email"></asp:TextBox>
                                    </td>
                                    <td>
                                        <asp:Button runat="server" ID="btnSearch" Text="Search" />
                                        <asp:Button runat="server" ID="btnAdd" Text="新建" />
                                    </td>
                                </tr>
                            </table>
                            <div class="blackbar">
                            </div>
                            <div style="position: relative;">
                                <asp:GridView runat="server" ID="gvwBug" AutoGenerateColumns="False" CssClass="tablestyle"
                                    ForeColor="#245D75" GridLines="None" OnRowEditing="gvwBug_RowEditing" OnSelectedIndexChanging="gvwBug_SelectedIndexChanging"
                                    OnRowDataBound="gvwBug_RowDataBound" HeaderStyle-CssClass="headerstyle" RowStyle-CssClass="rowstyle"
                                    HorizontalAlign="Center">
                                    <EmptyDataRowStyle CssClass="headerstyle" />
                                    <AlternatingRowStyle BackColor="White" />
                                    <EmptyDataTemplate>
                                        姓名</td>
                                        <td>
                                            用户名
                                        </td>
                                        <td>
                                            权限
                                        </td>
                                        </tr>
                                        <tr>
                                            <td colspan="6">
                                                <span>请添加用户</span>
                                            </td>
                                    </EmptyDataTemplate>
                                    <Columns>
                                        <asp:TemplateField ItemStyle-Width="20" ItemStyle-HorizontalAlign="Center" HeaderText="<input type='checkbox' onchange='SelectAll(this)'/>">
                                            <ItemTemplate>
                                                <asp:CheckBox runat="server" ID="chkBox" />
                                            </ItemTemplate>
                                        </asp:TemplateField>
                                        <asp:BoundField DataField="NickName" HeaderText="姓名" ItemStyle-Width="120" ItemStyle-HorizontalAlign="Center" />
                                        <asp:BoundField DataField="LoginName" HeaderText="用户名" ItemStyle-Width="180" ItemStyle-HorizontalAlign="Center" />
                                        <asp:BoundField DataField="Email" HeaderText="Email" ItemStyle-Width="180" ItemStyle-HorizontalAlign="Center" />
                                        <asp:BoundField DataField="RoleName" HeaderText="权限" ItemStyle-Width="120" ItemStyle-HorizontalAlign="Center" />
                                        <asp:CommandField HeaderText="编辑" EditText="编辑" ShowEditButton="true" ItemStyle-Width="70"
                                            ItemStyle-HorizontalAlign="Center" />
                                    </Columns>
                                    <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                                </asp:GridView>
                                <aspx:GridViewPager runat="server" ID="gvwUserPager" PageSize="3" OnPageIndexChanged="gvwBug_PageIndexChanged"
                                    IconImageUrl="../images/gridview-pager.png" />
                            </div>
                            <asp:ModalPopupExtender ID="modalUserEdit" runat="server" BackgroundCssClass="modalbackbround"
                                PopupControlID="pnlUserEdit" TargetControlID="btnAdd" CancelControlID="btnCancel">
                            </asp:ModalPopupExtender>
                            <asp:HiddenField runat="server" ID="hdfModalTarget" />
                            <asp:HiddenField runat="server" ID="hdfEditStatus" />
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>
    <asp:UpdatePanel runat="server" ID="uplEdit" UpdateMode="Conditional" ChildrenAsTriggers="false">
        <ContentTemplate>
            <asp:Panel runat="server" ID="pnlUserEdit" Width="500" Height="309" CssClass="modalpopup">
                <div class="tbar">
                    <h3>
                        <asp:Label runat="server" ID="lblEditTitle" Text="新增用户"></asp:Label></h3>
                </div>
                <div class="master-waper-edit-form" style="height: 200px; border: none;">
                    <table>
                        <tr>
                            <th>
                                NickName：
                            </th>
                            <td>
                                <asp:TextBox runat="server" ID="txtNickName" Width="200" CssClass="textbox" onchange="userNameValidator(this, event)"></asp:TextBox>
                                <span id="lblUserNameChkMsg" style="color: Red;"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                LoginName：
                            </th>
                            <td>
                                <asp:TextBox runat="server" ID="txtLoginName" Width="200" CssClass="textbox" onchange="loginNameValidator(this, event)"></asp:TextBox>
                                <span id="lblLoginNameChkMsg" style="color: Red;"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Email：
                            </th>
                            <td>
                                <asp:TextBox runat="server" ID="txtEmail" Width="200" CssClass="textbox"></asp:TextBox>
                                <span id="Span1" style="color: Red;"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Password：
                            </th>
                            <td>
                                <asp:TextBox runat="server" ID="txtPassword" Width="200" TextMode="Password" CssClass="textbox"
                                    onchange="PasswordValidator(this, event)"></asp:TextBox>
                                <span id="lblPasswordChkMsg" style="color: Red;"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Role
                            </th>
                            <td>
                                <asp:DropDownList runat="server" ID="dplUserRole" Height="23" CssClass="combox">
                                    <asp:ListItem Value="0" Text="请选择权限"></asp:ListItem>
                                    <asp:ListItem Value="1" Text="Admin"></asp:ListItem>
                                    <asp:ListItem Value="2" Text="User"></asp:ListItem>
                                </asp:DropDownList>
                            </td>
                        </tr>
                    </table>
                </div>
                <div style="padding: 10px; float: right;">
                    <asp:Button ID="btnSave" runat="server"  OnClientClick="return btnSave_Click(this,event)"
                        Text="Save"></asp:Button>
                    <asp:Button ID="btnCancel" runat="server" Text="Cancel"></asp:Button>
                </div>
            </asp:Panel>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
