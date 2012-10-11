<%@ Page Title="用户管理" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true"
    CodeBehind="UserMgt.aspx.cs" Inherits="SINOMA.WebUI.Admin.UserMgt" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="asp" %>
<%@ Register Assembly="SINOMA.UserControl" Namespace="SINOMA.UserControl" TagPrefix="aspx" %>
<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        function SelectAll(o) {
            $(o).parentsUntil("table").find("TR").each(function (index, domElm) {
                $($($(domElm).find("TD").get(0)).find("input").get(0)).attr("checked", $(o).attr("checked"));
            });
        }

        function btnDelete_Click() {
            var has = false;
            $("#<%=gvwUser.ClientID %>").find("TR").each(function (index, domElm) {
                if ($($($(domElm).find("TD").get(0)).find("input").get(0)).attr("checked")) {
                    has = true;
                };
            });
            if (!has) {
                alert("请选择要删除的用户！");
                return false;
            } else {
                return window.confirm("确定删除选中的数据吗？");
            }
        }

        function btnSave_Click(o, e) {
            var oUserName = document.getElementById("<%=txtUserName.ClientID %>");
            var oLoginName = document.getElementById("<%=txtLoginName.ClientID %>");
            var oPassword = document.getElementById("<%=txtPassword.ClientID %>");

            if ($("#<%=hdfEditStatus %>").val() == "")
                return userNameValidator(oUserName, e)
                    && loginNameValidator(oLoginName, e) && PasswordValidator(oPassword, e);
        }

        function userNameValidator(o, e) {
            var result = false;
            var message = "";

            if (o.value == "") {
                message = "姓名不能为空";
            }
            else {
                $.ajax({
                    async: false,
                    url: "../Handlers/UserMgtHandler.ashx?userName=" + o.value,
                    data: {},
                    success: function (resp) {
                        result = !eval("(" + resp + ")").returnValue;
                    }
                });

                if (!result) {
                    message = "姓名已经存在";
                }
            }

            $("#lblUserNameChkMsg").empty();
            if (!result) {
                $("#lblUserNameChkMsg").text(message);
            } else {
                var validMsg = document.createElement("IMG");
                $(validMsg).attr("src", "../images/checked.png");
                $(validMsg).appendTo($("#lblUserNameChkMsg"));
            }

            return result;
        }

        function loginNameValidator(o, e) {
            var result = false;
            var message = "";

            if (o.value == "") {
                message = "用户名不能为空";
            }
            else {
                $.ajax({
                    async: false,
                    url: "../Handlers/UserMgtHandler.ashx?loginName=" + o.value,
                    data: {},
                    success: function (resp) {
                        result = !eval("(" + resp + ")").returnValue;
                    }
                });

                if (!result) {
                    message = "用户名已经存在";
                }
            }

            $("#lblLoginNameChkMsg").empty();
            if (!result) {
                $("#lblLoginNameChkMsg").text(message);
            } else {
                var validMsg = document.createElement("IMG");
                $(validMsg).attr("src", "../images/checked.png");
                $(validMsg).appendTo($("#lblLoginNameChkMsg"));
            }

            return result;
        }

        function PasswordValidator(o, e) {
            var result = false;
            var message = "";

            if (o.value == "") {
                message = "密码不能为空";
            } else if (o.value.lastIndexOf(" ") > 0) {
                message = "密码不能包含空字符";
            } else {
                result = true;
            }

            $("#lblPasswordChkMsg").empty();
            if (!result) {
                $("#lblPasswordChkMsg").text(message);
            } else {
                var validMsg = document.createElement("IMG");
                $(validMsg).attr("src", "../images/checked.png");
                $(validMsg).appendTo($("#lblPasswordChkMsg"));
            }

            return result;
        }


    </script>
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <asp:UpdatePanel runat="server" ID="mainUpl">
        <Triggers>
            <asp:AsyncPostBackTrigger ControlID="gvwUserPager" />
        </Triggers>
        <ContentTemplate>
            <div class="master-waper-header-bar">
                <h2>
                    <asp:Label runat="server" ID="lblTitle" Text="用户管理"></asp:Label></h2>
            </div>
            <div style="margin: 5px 0px 5px 0px; height: 100%;">
                <div style="height: 28px; padding: 5px;">
                    查询条件：<asp:TextBox runat="server" ID="txtKeyWord" CssClass="textbox"></asp:TextBox>
                    &nbsp;权限：<asp:DropDownList runat="server" ID="dplSchUserRole" Height="23" Style="padding: 2px;"
                        CssClass="combox">
                        <asp:ListItem Value="0" Text="请选择权限">
                        </asp:ListItem>
                        <asp:ListItem Value="Designer" Text="设计人员"></asp:ListItem>
                        <asp:ListItem Value="SeniorDesigner" Text="高级设计人员"></asp:ListItem>
                        <asp:ListItem Value="Operator" Text="制造人员"></asp:ListItem>
                        <asp:ListItem Value="SeniorOperator" Text="高级制造人员"></asp:ListItem>
                        <asp:ListItem Value="Salesman" Text="营销人员"></asp:ListItem>
                        <asp:ListItem Value="SeniorSalesman" Text="高级营销人员"></asp:ListItem>
                        <asp:ListItem Value="Administration" Text="管理层"></asp:ListItem>
                        <asp:ListItem Value="Admin" Text="管理员"></asp:ListItem>
                    </asp:DropDownList>
                    <asp:LinkButton runat="server" ID="btnSearch" CssClass="button" Width="60" OnClick="btnSearch_Click"><ins>查 询</ins></asp:LinkButton>
                    <asp:LinkButton runat="server" ID="btnAdd" CssClass="button" Width="60" OnClick="btnAdd_Click"><ins>新 增</ins></asp:LinkButton>
                    <asp:LinkButton ID="btnDelete" runat="server" CssClass="button" Width="60" OnClientClick="return btnDelete_Click()"
                        OnClick="btnDelete_Click"><ins>删 除</ins></asp:LinkButton>
                </div>
                <div style="height: 30px; position: relative;">
                    <aspx:GridViewPager runat="server" ID="gvwUserPager" PageSize="15" OnPageIndexChanged="gvwUser_PageIndexChanged"
                        IconImageUrl="../images/gridview-pager.png" />
                </div>
                <div style="height: 388px; border: 1px solid #aac1de;">
                    <asp:GridView runat="server" ID="gvwUser" AutoGenerateColumns="False" CssClass="gridview"
                        ForeColor="#333333" GridLines="None" OnRowEditing="gvwUser_RowEditing" OnSelectedIndexChanging="gvwUser_SelectedIndexChanging"
                        OnRowDataBound="gvwUser_RowDataBound">
                        <EmptyDataRowStyle CssClass="gridview-empty" />
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
                            <asp:TemplateField ItemStyle-Width="20" HeaderText="<input type='checkbox' onchange='SelectAll(this)'/>">
                                <ItemTemplate>
                                    <asp:CheckBox runat="server" ID="chkBox" />
                                </ItemTemplate>
                            </asp:TemplateField>
                            <asp:BoundField DataField="Name" HeaderText="姓名" ItemStyle-Width="150" ItemStyle-HorizontalAlign="Center" />
                            <asp:BoundField DataField="LoginName" HeaderText="用户名" ItemStyle-Width="180" ItemStyle-HorizontalAlign="Center" />
                            <asp:BoundField DataField="Role" HeaderText="权限" ItemStyle-HorizontalAlign="Center" />
                            <asp:BoundField DataField="IsLogin" HeaderText="状态" ItemStyle-HorizontalAlign="Center" />
                            <asp:CommandField HeaderText="编辑" EditText="编辑" ShowEditButton="true" ItemStyle-Width="70"
                                ItemStyle-HorizontalAlign="Center" />
                            <asp:CommandField HeaderText="解除锁定" SelectText="解除锁定" ShowSelectButton="true" ItemStyle-Width="90"
                                ItemStyle-HorizontalAlign="Center" />
                        </Columns>
                        <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                        <HeaderStyle BackColor="#688fc9" Font-Bold="True" ForeColor="White" />
                        <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                        <RowStyle BackColor="#EFF3FB" />
                        <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                    </asp:GridView>
                </div>
            </div>
            <asp:ModalPopupExtender ID="modalUserEdit" runat="server" BackgroundCssClass="modalbackbround"
                PopupControlID="pnlUserEdit" TargetControlID="hdfModalTarget" CancelControlID="btnCancel">
            </asp:ModalPopupExtender>
            <asp:HiddenField runat="server" ID="hdfModalTarget" />
            <asp:HiddenField runat="server" ID="hdfEditStatus" />
        </ContentTemplate>
    </asp:UpdatePanel>
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
                                姓 名：
                            </th>
                            <td>
                                <asp:TextBox runat="server" ID="txtUserName" Width="200" CssClass="textbox" onchange="userNameValidator(this, event)"></asp:TextBox>
                                <span id="lblUserNameChkMsg" style="color: Red;"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                用户名：
                            </th>
                            <td>
                                <asp:TextBox runat="server" ID="txtLoginName" Width="200" CssClass="textbox" onchange="loginNameValidator(this, event)"></asp:TextBox>
                                <span id="lblLoginNameChkMsg" style="color: Red;"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                密 码：
                            </th>
                            <td>
                                <asp:TextBox runat="server" ID="txtPassword" Width="200" TextMode="Password" CssClass="textbox"
                                    onchange="PasswordValidator(this, event)"></asp:TextBox>
                                <span id="lblPasswordChkMsg" style="color: Red;"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                权限
                            </th>
                            <td>
                                <asp:DropDownList runat="server" ID="dplUserRole" Height="23" CssClass="combox">
                                    <asp:ListItem Value="0" Text="请选择权限"></asp:ListItem>
                                    <asp:ListItem Value="Designer" Text="设计人员"></asp:ListItem>
                                    <asp:ListItem Value="SeniorDesigner" Text="高级设计人员"></asp:ListItem>
                                    <asp:ListItem Value="Operator" Text="制造人员"></asp:ListItem>
                                    <asp:ListItem Value="SeniorOperator" Text="高级制造人员"></asp:ListItem>
                                    <asp:ListItem Value="Salesman" Text="营销人员"></asp:ListItem>
                                    <asp:ListItem Value="SeniorSalesman" Text="高级营销人员"></asp:ListItem>
                                    <asp:ListItem Value="Administration" Text="管理层"></asp:ListItem>
                                    <asp:ListItem Value="Admin" Text="管理员"></asp:ListItem>
                                </asp:DropDownList>
                            </td>
                        </tr>
                    </table>
                </div>
                <div style="padding: 10px; float: right;">
                    <asp:LinkButton ID="btnSave" runat="server" CssClass="button" Width="60" OnClick="btnSave_Click"
                        OnClientClick="return btnSave_Click(this,event)"><ins>保 存</ins></asp:LinkButton>
                    <asp:LinkButton ID="btnCancel" runat="server" CssClass="button" Width="60"><ins>取 消</ins></asp:LinkButton>
                </div>
            </asp:Panel>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
