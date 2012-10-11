<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Account.aspx.cs" Inherits="SINOMA.WebUI.Account" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        $(document).ready(function () {
            $("#pwdChgTab").show();
            $("#baseInforTab").hide();
            //$("#lnkBaseInfor").addClass("waper-tab-active");
        });
        function showAndHideDiV(id) {
            if (id == "lnkBaseInfor") {
                $("#pwdChgTab").hide();
                $("#lnkPwdChg").removeClass("waper-tab-active");
                $("#lnkPwdChg").addClass("waper-tab-inactive");

                $("#baseInforTab").show();
                $("#lnkBaseInfor").removeClass("waper-tab-inactive");
                $("#lnkBaseInfor").addClass("waper-tab-active");
            }
            else if (id == "lnkPwdChg") {
                $("#baseInforTab").hide();
                $("#lnkBaseInfor").removeClass("waper-tab-active");
                $("#lnkBaseInfor").addClass("waper-tab-inactive");

                $("#pwdChgTab").show();
                $("#lnkPwdChg").removeClass("waper-tab-inactive");
                $("#lnkPwdChg").addClass("waper-tab-active");
            } else {
            }
        }

        function PasswordCheck(o, e) {
            var result = false;
            var message;
            if (o.value == "") {
                message = "密码不能为空";
            } else if (o.value.lastIndexOf(" ") > 0) {
                message = "密码不能包含空字符";
            } else if (o.value.length < 6) {
                message = "密码长度不能小于6";
            } else if (o.value.length > 15) {
                message = "密码长度不能大于15";
            } else {
                result = true;
            }
            if (!result)
                $("#lblPwdChkMsg").text(message);
            else
                $("#lblPwdChkMsg").text("");
            return result;
        }

        function PasswordConfirmCheck(o, e) {
            var result = false;
            var message;
            if (o.value == "") {
                message = "请再次输入密码";
            }
            else if (o.value != $("#<%=txtPassword.ClientID %>").val()) {
                message = "两次密码输入不同";
            }
            else {
                result = true;
            }

            if (!result)
                $("#lblPwdCfmChkMsg").text(message);
            else
                $("#lblPwdCfmChkMsg").text("");

            return result;
        }

        function SaveCheck(o, e) {
            var oPwd = document.getElementById("<%=txtPassword.ClientID %>");
            var oPwdCfm = document.getElementById("<%=txtPasswordConfirm.ClientID %>");
            return PasswordCheck(oPwd, e) && PasswordConfirmCheck(oPwdCfm, e);
        }
    </script>
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="master-waper-header-bar">
        <h2>
            我的账户</h2>
    </div>
    <%--<div class="waper-tab-container">
        <a id="lnkBaseInfor" class="waper-tab-active" href="#" onclick="showAndHideDiV(this.id)">
            基本信息</a> <a id="lnkPwdChg" class="waper-tab-inactive" onclick="showAndHideDiV(this.id)">
                密码修改</a>
    </div>--%>
    <div class="master-waper-edit-form">
        <div id="baseInforTab">
            <table>
                <tr>
                    <th>
                        姓名：
                    </th>
                    <td>
                        <asp:TextBox runat="server" ID="txtName"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <th>
                        姓名：
                    </th>
                    <td>
                        <asp:TextBox runat="server" ID="TextBox2"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <th>
                        姓名：
                    </th>
                    <td>
                        <asp:TextBox runat="server" ID="TextBox3"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <th>
                        姓名：
                    </th>
                    <td>
                        <asp:TextBox runat="server" ID="TextBox4"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <th>
                        姓名：
                    </th>
                    <td>
                        <asp:TextBox runat="server" ID="TextBox5"></asp:TextBox>
                    </td>
                </tr>
            </table>
        </div>
        <div id="pwdChgTab">
            <table>
                <tr>
                    <th>
                        用户名：
                    </th>
                    <td>
                        <asp:TextBox runat="server" ID="txtLoginName" Width="200" Enabled="false"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <th>
                        新密码：
                    </th>
                    <td>
                        <asp:TextBox runat="server" ID="txtPassword" Width="200" TextMode="Password" 
                        onblur="PasswordCheck(this,event)" onchange="PasswordCheck(this, event)"></asp:TextBox>
                        <span id="lblPwdChkMsg" style="color: Red;"></span>
                    </td>
                </tr>
                <tr>
                    <th>
                        确认密码：
                    </th>
                    <td>
                        <asp:TextBox runat="server" ID="txtPasswordConfirm" Width="200" TextMode="Password"
                        onblur="PasswordConfirmCheck(this,event)" onchange="PasswordConfirmCheck(this, event)"></asp:TextBox>
                        <span id="lblPwdCfmChkMsg" style="color: Red;"></span>
                    </td>
                </tr>
            </table>
        </div>
        <table style="width: 90%;">
            <tr>
                <th align="right">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <asp:LinkButton runat="server" ID="btnSave" style="float:right;"
                                CssClass="button" Width="60px" OnClientClick="return SaveCheck(this,event)" OnClick="btnSave_Click"><ins>保 存</ins></asp:LinkButton>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </th>
            </tr>
        </table>
    </div>
</asp:Content>
