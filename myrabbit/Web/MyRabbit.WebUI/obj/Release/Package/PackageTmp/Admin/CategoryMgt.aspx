<%@ Page Title="类别管理" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true"
    CodeBehind="CategoryMgt.aspx.cs" Inherits="SINOMA.WebUI.Admin.CategoryMgt" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="asp" %>
<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
    <script type="text/javascript">
        function GetInnerText(o) {
            if (navigator.userAgent.indexOf("MSIE") > 0) {
                return o.innerText;
            } else {
                return o.textContent;
            }
        }

        function btnDelete_Click(o, e) {
            var has = false;
            $("#<%=tvwCategory.ClientID %>").find("A").each(function (index, domElm) {
                if ($(domElm).css("color") == "white") {
                    has = true;
                    return;
                }
            });
            if (!has) {
                alert("请选择要删除的类别！");
                return false;
            }

            if ($("#<%=hdfIsUsed.ClientID %>").val() == "true") {
                alert("类别已经在使用中，不能删除。若要删除，请先清除此类别下得所有产品。");
                return false;
            }

            return window.confirm("确定删除选中的类别吗？");
        }

        function attrDelete_Click() {
            return window.confirm("确定删除选中的属性吗？");
        }

        function btnSave_Click(o, e) {
            var reVal = false;
            var arg = { orderMessage: "", enameMessage: "", cnameMessage: "" };
            var grid = document.getElementById("<%=gvwExtendedAttribute.ClientID %>");
            var rows = [];
            if (grid)
                rows = grid.getElementsByTagName("TR");

            if ($("#<%=txtCategory.ClientID %>").val() == "") {
                alert("类别名称不能为空！");
//            } else if (!enameCheck(arg, rows) || !cnameCheck(arg, rows)) {//!orderCheck(arg, rows) || 
//                var message = arg.orderMessage;
//                message += (message != "") ? (arg.enameMessage != "" ? "；\r\n" + arg.enameMessage : "") : arg.enameMessage;
//                message += (message != "") ? (arg.cnameMessage != "" ? "；\r\n" + arg.cnameMessage : "") : arg.cnameMessage;
//                alert(message);
            } else
                reVal = true;
            return reVal;
        }

        function orderCheck(arg, rows) {
            var fVal = 0;
            var val = 0;
            for (var i = 1; i < rows.length; i++) {
                val += i;
                fVal += parseInt($($(rows[i]).find("TD").get(0)).text());
            }
            if (val != fVal) {
                arg.orderMessage = "请检查【序号】，并重新设置";
                return false;
            } else
                return true;
        }

        function enameCheck(arg, rows) {
            var errIndex = "";
            var duplicateIndex = "";
            var emptyIndex = "";

            for (var i = 1; i < rows.length; i++) {
                var val = $($($(rows[i]).find("TD").get(1)).find("input").get(0)).text().toString().replace(" ", "");
                var reg = /^(\w+)$/;

                val = GetInnerText(rows[i].getElementsByTagName("TD")[1]);
                emptyIndex += val == "" || val == " " ? "、" + i.toString() : "";
                //errIndex += reg.test(val) ? "" : "、" + i.toString();

                //                for (var k = i + 1; k < rows.length; k++) {
                //                    if (val == $($(rows[k]).find("TD").get(1)).text()) {
                //                        duplicateIndex += "、" + i.toString() + "行与" + k.toString() + "行";
                //                        break;
                //                    }
                //                }
            }

            if (errIndex != "" || emptyIndex != "") {
                arg.enameMessage = emptyIndex != "" ? "第" + emptyIndex.substr(1, emptyIndex.length - 1) + "行【标签分组名称】不能为空。" : "";
                //arg.enameMessage += errIndex != "" ? "第" + errIndex.substr(1, errIndex.length - 1) + "行【标签分组名称】不合法。" : "";
                //arg.enameMessage += duplicateIndex != "" ? "第" + duplicateIndex.substr(1, duplicateIndex.length - 1) + "【标签分组名称】重复。" : "";
                return false;
            } else
                return true;
        }

        function cnameCheck(arg, rows) {
            var duplicateIndex = "";
            var emptyIndex = "";

            for (var i = 1; i < rows.length; i++) {
                var val = $($(rows[i]).find("TD").get(2)).text().toString().replace(" ", "");
                val = GetInnerText(rows[1].getElementsByTagName("TD")[2]);
                emptyIndex += val == "" || val == " " ? "、" + i.toString() : "";

                for (var k = i + 1; k < rows.length; k++) {
                    if (val == $($(rows[k]).find("TD").get(2)).val()) {
                        duplicateIndex += "、" + i.toString() + "行与" + k.toString() + "行";
                        break;
                    }
                }
            }

            if (duplicateIndex != "" || emptyIndex != "") {
                arg.enameMessage = emptyIndex != "" ? "第" + emptyIndex.substr(1, emptyIndex.length - 1) + "行【标签名称】不能为空。" : "";
                arg.enameMessage += duplicateIndex != "" ? "第" + duplicateIndex.substr(1, duplicateIndex.length - 1) + "【标签名称】重复。" : "";
                return false;
            } else
                return true;
        }

        function EndRequestHandler() {
            $("#<%=tvwCategory.ClientID %>").find("A").each(function (index, domElm) {
                $(domElm).click(EditStatusValidate);
            });
        }

        $(function () {
            EndRequestHandler();
            Sys.WebForms.PageRequestManager.getInstance().add_endRequest(EndRequestHandler);
        })

        function btnAdd_Click(o, e) {
            return EditStatusValidate();
        }

        function EditStatusValidate() {
            if ($("#<%=hdfEditStatus.ClientID %>").val() == "")
                return true;
            else
                if (window.confirm("类别属性有修改，确定放弃修改的信息吗？")) {
                    $("#<%=hdfEditStatus.ClientID %>").val("");
                    return true;
                } else
                    return false;
        }
    </script>
</asp:Content>
<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <asp:UpdatePanel runat="server" ID="mainUpl">
        <ContentTemplate>
            <div class="master-waper-header-bar">
                <h2>
                    <asp:Label runat="server" ID="lblTitle"></asp:Label></h2>
            </div>
            <div style="margin: 5px 0px 5px 0px; height: 100%;">
                <div style="float: left; width: 240px; border: 1px solid #aac1de; color: #808080;
                    height: 415px; padding: 10px; overflow-y: scroll;">
                    <asp:TreeView ID="tvwCategory" runat="server" CollapseImageUrl="../images/treeview-collapse.gif"
                        ExpandImageUrl="../images/treeview-expand.gif" OnSelectedNodeChanged="tvwCategory_SelectedNodeChanged"
                        SelectedNodeStyle-BackColor="#688fc9" SelectedNodeStyle-ForeColor="White" NodeWrap="false"
                        ToolTip="鼠标点击击类别进行编辑、删除">
                    </asp:TreeView>
                </div>
                <div style="float: right; width: 710px; height: 415px;">
                    <div class="master-waper-edit-form">
                        <table>
                            <tr>
                                <th>
                                    上级类别：
                                </th>
                                <td>
                                    <asp:DropDownList runat="server" ID="dplParent" Height="23" CssClass="textbox">
                                    </asp:DropDownList>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    类别名称：
                                </th>
                                <td>
                                    <asp:TextBox runat="server" ID="txtCategory" CssClass="textbox" MaxLength="100" AutoPostBack="true"
                                        OnTextChanged="txtCategory_TextChanged"></asp:TextBox>
                                    <asp:Label runat="server" ID="lblCategoryChkMsg" ForeColor="Red" Visible="false"></asp:Label>
                                    <asp:Image runat="server" ID="imgCategoryChkMsg" ImageUrl="../images/checked.png"
                                        Visible="false" />
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    类别描述：
                                </th>
                                <td>
                                    <asp:TextBox runat="server" Width="400" ID="txtDescription" MaxLength="200" CssClass="textbox"></asp:TextBox>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div style="padding: 5px 0px 0px 0px; height: 295px;">
                        <div style="height: 255px;">
                            <asp:Panel runat="server" ID="panelGridView" Visible="false" Height="255px" Style="border: 1px solid #aac1de;
                                overflow-y: scroll;">
                                <asp:GridView runat="server" ID="gvwExtendedAttribute" EnableModelValidation="True"
                                    Width="690" AutoGenerateColumns="False" OnRowEditing="gvwExtendedAttribute_RowEditing"
                                    OnRowCancelingEdit="gvwExtendedAttribute_RowCancelingEdit" OnRowUpdating="gvwExtendedAttribute_RowUpdating"
                                    OnRowDeleting="gvwExtendedAttribute_RowDeleting" OnRowDataBound="gvwExtendedAttribute_RowDataBound"
                                    CssClass="gridview" CellPadding="4" ForeColor="#333333" GridLines="None">
                                    <EmptyDataRowStyle CssClass="gridview-empty" />
                                    <EmptyDataTemplate>
                                        标签分组名称</td>
                                        <td>
                                            标签名称
                                        </td>
                                        <td>
                                            显示序号
                                        </td>
                                        <td>
                                            可以为空
                                        </td>
                                        <td>
                                            数据类型
                                        </td>
                                        </tr>
                                        <tr>
                                            <td colspan="9">
                                                请添加属性
                                            </td>
                                    </EmptyDataTemplate>
                                    <AlternatingRowStyle BackColor="White" />
                                    <Columns>
                                        <asp:BoundField DataField="Order" HeaderText="序号" ReadOnly="true" ItemStyle-Width="25px" />
                                        <%--<asp:BoundField DataField="Group" HeaderText="标签分组名称" ItemStyle-Width="120px" />--%>
                                        <asp:TemplateField HeaderText="标签分组名称" ItemStyle-Width="120px">
                                            <ItemTemplate>
                                                <asp:TextBox runat="server" ID="txtGroup"></asp:TextBox></ItemTemplate>
                                        </asp:TemplateField>
                                        <%--<asp:BoundField DataField="DisplayName" HeaderText="标签名称" />--%>
                                        <asp:TemplateField HeaderText="标签名称">
                                            <ItemTemplate>
                                                <asp:TextBox runat="server" ID="txtDisplayName"></asp:TextBox></ItemTemplate>
                                        </asp:TemplateField>
                                        <asp:TemplateField HeaderText="可以为空" ItemStyle-Width="60px">
                                            <ItemTemplate>
                                                <asp:CheckBox runat="server" ID="chkEnableEmpty" />
                                            </ItemTemplate>
                                        </asp:TemplateField>
                                        <asp:TemplateField HeaderText="数据类型" ItemStyle-Width="50px">
                                            <ItemTemplate>
                                                <asp:DropDownList runat="server" ID="dplDataType">
                                                    <asp:ListItem Value="String">字符串</asp:ListItem>
                                                    <asp:ListItem Value="Int">整数</asp:ListItem>
                                                    <asp:ListItem Value="DateTime">日期时间</asp:ListItem>
                                                    <asp:ListItem Value="Float">小数</asp:ListItem>
                                                </asp:DropDownList>
                                            </ItemTemplate>
                                        </asp:TemplateField>
                                        <asp:TemplateField HeaderText="单位" ItemStyle-Width="50px">
                                            <ItemTemplate>
                                                <asp:DropDownList runat="server" ID="dplDataUnit">
                                                </asp:DropDownList>
                                            </ItemTemplate>
                                        </asp:TemplateField>
                                        <asp:TemplateField HeaderText="属性类别" ItemStyle-Width="50px">
                                            <ItemTemplate>
                                                <asp:DropDownList runat="server" ID="dplCategory">
                                                    <asp:ListItem Value="Common">通用参数</asp:ListItem>
                                                    <asp:ListItem Value="Design">设计参数</asp:ListItem>
                                                    <asp:ListItem Value="Produce">制造参数</asp:ListItem>
                                                    <asp:ListItem Value="Spare">备用设备</asp:ListItem>
                                                </asp:DropDownList>
                                            </ItemTemplate>
                                        </asp:TemplateField>
                                        <asp:CommandField HeaderText="编辑" ItemStyle-Width="60px"  ShowCancelButton="true" CancelText="取消"
                                            ShowDeleteButton="True" DeleteText="<span onclick='return attrDelete_Click()'>删除</span>"
                                            ItemStyle-HorizontalAlign="Center" />
                                    </Columns>
                                    <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                                    <HeaderStyle BackColor="#688fc9" Font-Bold="True" ForeColor="White" />
                                    <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                                    <RowStyle BackColor="#EFF3FB" />
                                    <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                                </asp:GridView>
                                <a name="gridviewbottom" href="gridviewbottom"></a>
                            </asp:Panel>
                        </div>
                        <div style="height: 25px; padding: 12px 5px 5px 5px;">
                            <asp:LinkButton runat="server" ID="btnAdd" CssClass="button" Width="80" OnClick="btnAdd_Click"
                                OnClientClick="return btnAdd_Click(this, event);"><ins>添加类别</ins></asp:LinkButton>
                            <asp:LinkButton ID="btnAddAttr" runat="server" CssClass="button" Width="100" OnClick="btnAddAttr_Click"><ins>添加扩展属性</ins></asp:LinkButton>
                            <asp:LinkButton ID="btnDelete" runat="server" CssClass="button" Width="80" OnClientClick="return btnDelete_Click(this, event)"
                                OnClick="btnDelete_Click"><ins>删除类别</ins></asp:LinkButton>
                            <asp:LinkButton ID="btnSave" runat="server" CssClass="button" Width="60" OnClientClick="return btnSave_Click(this, event)"
                                OnClick="btnSave_Click"><ins>保 存</ins></asp:LinkButton>
                            <asp:LinkButton ID="btnHelp" runat="server" CssClass="button" Width="60"><ins>帮 助</ins></asp:LinkButton>
                        </div>
                    </div>
                </div>
            </div>
            <asp:HiddenField runat="server" ID="hdfEditStatus" />
            <asp:HiddenField runat="server" ID="hdfIsUsed" />
            <asp:ModalPopupExtender ID="modalHelp" runat="server" BackgroundCssClass="modalbackbround"
                PopupControlID="panelHelp" TargetControlID="btnHelp">
            </asp:ModalPopupExtender>
        </ContentTemplate>
    </asp:UpdatePanel>
    <asp:Panel runat="server" ID="panelHelp" Width="800" Height="400" CssClass="help">
        <div style="height: 340px; padding: 10px;">
            <div>
                <h3>
                    新增类别操作流程</h3>
            </div>
            <div style="padding: 5px;">
                <p>
                    <h4>
                        1.新增</h4>
                    <p>
                    </p>
                    <p>
                        &nbsp;&nbsp;&nbsp;&nbsp;点击【新增】按钮，选择上一级类别，填写新增类别名称、描述。添加类别扩展属性，参考第4.添加扩展属性，点击【保存】，完成新增操作</p>
                    <p>
                        <h4>
                            2.更新
                        </h4>
                        <p>
                        </p>
                        <p>
                            &nbsp;&nbsp;&nbsp;&nbsp;选择左边类别组织中一个类别，修改名称、描述，点击【保存】，完成类别更新操作</p>
                        <p>
                            <h4>
                                3.删除
                            </h4>
                            <p>
                            </p>
                            <p>
                                &nbsp;&nbsp;&nbsp;&nbsp;选择左边类别组织中一个类别，点击【删除】</p>
                            <p>
                                <h4>
                                    4.添加扩展属性</h4>
                                <p>
                                </p>
                                <p>
                                    &nbsp;&nbsp;&nbsp;&nbsp;点击【添加扩展属性】按钮，下方将显示类别属性列表，并且新增的一列处于编辑状态的扩展属性，完成扩展属性字段编辑后，点击【更新】，当前列完成编辑操作。如果想取消当列，点击【取消】。如果想删除已编辑好的属性，点击【删除】操作，讲扩展属性移除。<font>注意</font>，点击【更新】、【取消】、【删除】操作并没有修改数据库中的数据，当完成所有扩展属性编辑，点击【保存】按钮，提示“保存成功！”后，类别的扩展属性才会保存到数据库中。</p>
                                <p>
                                </p>
                                <p>
                                </p>
                                <p>
                                </p>
                                <p>
                                </p>
                                <p>
                                </p>
                            </p>
                        </p>
                    </p>
                </p>
            </div>
        </div>
        <div style="text-align: center; height: 30px;">
            <asp:UpdatePanel runat="server" ID="uplPopup">
                <ContentTemplate>
                    <asp:CheckBox runat="server" ID="chkCookie" Text="下次不再显示" Checked="true" />
                    <asp:LinkButton runat="server" ID="btnCancel" CssClass="button" Width="60px" OnClick="btnCancel_Click"><ins>确 定</ins></asp:LinkButton>
                </ContentTemplate>
            </asp:UpdatePanel>
        </div>
    </asp:Panel>
</asp:Content>
