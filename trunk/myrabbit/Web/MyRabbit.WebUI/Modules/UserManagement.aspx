<%@ Page Title="用户管理" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
   CodeBehind="UserManagement.aspx.cs" Inherits="MyRabbit.WebUI.UserManagement" EnableEventValidation="false" %>

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
         var oUserName = document.getElementById("<%=txtNickName.ClientID %>");
         var oLoginName = document.getElementById("<%=txtLoginName.ClientID %>");
         var oPassword = document.getElementById("<%=txtPassword.ClientID %>");

         //            if ($("#<%=hdfEditStatus.ClientID %>").val() == "")
         //                return userNameValidator(oUserName, e)
         //                    && loginNameValidator(oLoginName, e) && PasswordValidator(oPassword, e);
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
               url: "Handlers/UserMgtHandler.ashx?userName=" + o.value,
               data: {},
               success: function (resp) {
                  result = !eval("(" + resp + ")").returnValue;
               },
               failure: function (resp) {
                  alert(resp);
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

         //return result;
         return true;
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
               url: "Handlers/UserMgtHandler.ashx?loginName=" + o.value,
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

         //return result;
         return true;
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
   <div class="content">
      <div class="cph">
         <div class="section-title">
            <img alt="Dashboard" src="/images/ico-dashboard.png" />用户管理
         </div>
         <div class="section-body">
            <asp:UpdatePanel runat="server" ID="uplTbar">
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
                           <asp:Button runat="server" ID="btnSearch" Text="Search" OnClick="btnSearch_Click" />
                           <asp:Button runat="server" ID="btnClear" Text="Clear" />
                           <asp:Button runat="server" ID="btnAdd" Text="Add" OnClick="btnAdd_Click" />
                           <asp:Button runat="server" ID="btnDelete" Text="Delete" />
                        </td>
                     </tr>
                  </table>
               </ContentTemplate>
            </asp:UpdatePanel>
            <asp:UpdatePanel runat="server" ID="uplMain" UpdateMode="Conditional">
               <Triggers>
                  <asp:AsyncPostBackTrigger ControlID="gvwUserPager" />
               </Triggers>
               <ContentTemplate>
                  <div class="blackbar">
                  </div>
                  <div style="width: 1024px; position: relative;">
                     <aspx:GridViewPager runat="server" ID="gvwUserPager" PageSize="3" OnPageIndexChanged="gvwUser_PageIndexChanged" />
                     <asp:GridView runat="server" ID="gvwUser" AutoGenerateColumns="False" CssClass="tablestyle"
                        ForeColor="#245D75" GridLines="None" OnRowEditing="gvwUser_RowEditing" OnSelectedIndexChanging="gvwUser_SelectedIndexChanging"
                        OnRowDataBound="gvwUser_RowDataBound" HeaderStyle-CssClass="headerstyle" RowStyle-CssClass="rowstyle"
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
                           <asp:TemplateField ItemStyle-Width="12" ItemStyle-HorizontalAlign="Center" HeaderText="<input type='checkbox' onchange='SelectAll(this)'/>">
                              <ItemTemplate>
                                 <asp:CheckBox runat="server" ID="chkBox" />
                              </ItemTemplate>
                           </asp:TemplateField>
                           <asp:BoundField DataField="NickName" HeaderText="姓名" ItemStyle-Width="120" ItemStyle-HorizontalAlign="Center" />
                           <asp:BoundField DataField="LoginName" HeaderText="用户名" ItemStyle-Width="180" ItemStyle-HorizontalAlign="Center" />
                           <asp:BoundField DataField="Email" HeaderText="Email" ItemStyle-Width="180" ItemStyle-HorizontalAlign="Center" />
                           <asp:CommandField HeaderText="编辑" EditText="编辑" ShowEditButton="true" ItemStyle-Width="70"
                              ItemStyle-HorizontalAlign="Center" />
                        </Columns>
                        <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                     </asp:GridView>
                  </div>
                  <asp:HiddenField runat="server" ID="hdfEditStatus" />
               </ContentTemplate>
            </asp:UpdatePanel>
         </div>
      </div>
   </div>
   <asp:UpdatePanel runat="server" ID="uplEdit" UpdateMode="Conditional" ChildrenAsTriggers="false">
      <ContentTemplate>
         <asp:ModalPopupExtender ID="modalUserEdit" runat="server" BackgroundCssClass="modalbackbround"
            PopupControlID="pnlUserEdit" TargetControlID="hdfModalTarget" CancelControlID="btnCancel">
         </asp:ModalPopupExtender>
         <asp:HiddenField runat="server" ID="hdfModalTarget" />
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
               <asp:Button ID="btnSave" runat="server" OnClick="btnSave_Click" OnClientClick="return btnSave_Click(this,event)"
                  Text="Save"></asp:Button>
               <asp:Button ID="btnCancel" runat="server" Text="Cancel"></asp:Button>
            </div>
         </asp:Panel>
      </ContentTemplate>
   </asp:UpdatePanel>
</asp:Content>
