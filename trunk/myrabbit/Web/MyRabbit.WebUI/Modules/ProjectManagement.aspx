<%@ Page Title="项目管理" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ProjectManagement.aspx.cs" Inherits="MyRabbit.WebUI.ProjectManagement" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
<div class="content">
        <div class="cph">
            <div class="section-title">
                <img alt="Dashboard" src="/images/ico-dashboard.png" />项目管理
            </div>
            <div class="section-body">
                <asp:UpdatePanel ID="UpdatePanel1" runat="server">
                    <Triggers>
                        <asp:AsyncPostBackTrigger ControlID="gvwUserPager" />
                    </Triggers>
                    <ContentTemplate>
                    <div 
                        <table class="table-container">
                            <tr>
                                <th>Nick Name:</th>
                                <td>
                                    <asp:TextBox runat="server" CssClass="textBox" ID="NickName"></asp:TextBox>
                                </td>
                                <th>
                                    Email:
                                </th>
                                <td>
                                    <asp:TextBox runat="server" CssClass="textBox" ID="Email"></asp:TextBox>
                                </td>
                                <th>
                                    Category:
                                </th>
                                <td>
                                    <asp:DropDownList runat="server" ID="dplCategory">
                                        <asp:ListItem Value="0">Please select category</asp:ListItem>
                                        <asp:ListItem Value="Admin">Admin</asp:ListItem>
                                    </asp:DropDownList>
                                </td>
                                <td>
                                    <asp:Button runat="server" ID="btnSearch" Text="Search"/>
                                    <asp:Button runat="server" ID="btnClear" Text="Clear" />
                                    <asp:Button runat="server" ID="btnAdd" Text="Add" />
                                    <asp:Button runat="server" ID="btnDelete" Text="Delete" />
                                </td>
                            </tr>
                        </table>
                        <div class="blackbar">
                        </div>
                        <div style="width: 1024px; position: relative;">
                            <asp:GridView runat="server" ID="gvwUser" AutoGenerateColumns="False" CssClass="tablestyle"
                                ForeColor="#245D75" GridLines="None" HeaderStyle-CssClass="headerstyle" RowStyle-CssClass="rowstyle"
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
                            <aspx:GridViewPager runat="server" ID="gvwUserPager" PageSize="3"
                                IconImageUrl="../images/gridview-pager.png" />
                        </div>
                        <asp:ModalPopupExtender ID="modalUserEdit" runat="server" BackgroundCssClass="modalbackbround"
                            PopupControlID="pnlUserEdit" TargetControlID="hdfModalTarget" CancelControlID="btnCancel">
                        </asp:ModalPopupExtender>
                        <asp:HiddenField runat="server" ID="hdfModalTarget" />
                        <asp:HiddenField runat="server" ID="hdfEditStatus" />
                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>
    <asp:UpdatePanel runat="server" ID="uplEdit" UpdateMode="Conditional" ChildrenAsTriggers="false">
        <Triggers>
            <asp:AsyncPostBackTrigger ControlID="btnAdd" />
        </Triggers>
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
                    <asp:Button ID="btnSave" runat="server" OnClientClick="return btnSave_Click(this,event)"
                        Text="Save"></asp:Button>
                    <asp:Button ID="btnCancel" runat="server" Text="Cancel"></asp:Button>
                </div>
            </asp:Panel>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
