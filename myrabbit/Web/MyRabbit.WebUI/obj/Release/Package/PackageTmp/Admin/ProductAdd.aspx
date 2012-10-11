<%@ Page Title="" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true"
    CodeBehind="ProductAdd.aspx.cs" Inherits="SINOMA.WebUI.Admin.ProductAdd" ValidateRequest="false" %>

<%@ Import Namespace="System.Linq" %>
<%@ Import Namespace="SINOMA.App.Entity" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <script type="text/javascript">

        $(document).ready(function () {
            Request = {
                QueryString: function (item) {
                    var svalue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)", "i"));
                    return svalue ? svalue[1] : svalue;
                }
            }
            $("#<%=btnAddProduct.ClientID%>").show();
            $("#<%=btnUpdateProduct.ClientID%>").show();
            if (Request.QueryString("id") == null) {
                $("#<%=btnAddProduct.ClientID%>").show();
                $("#<%=btnUpdateProduct.ClientID%>").hide();
            } else {
                $("#<%=btnAddProduct.ClientID%>").hide();
                $("#<%=btnUpdateProduct.ClientID%>").show();
                $("#treeviewDiv").attr('disabled', true);
                $("#<%=tvwCategory.ClientID %>").attr('disabled', true);
            }

            $(".master-waper-edit-form *").removeAttr('disabled');
            if (!$("#lblparentCategory").text().length > 0) {
                $(".master-waper-edit-form *").attr('disabled', true);
            }


            $("#aspnetForm").validate({
                debug: true,
                errorPlacement: function (error, element) {
                    error.appendTo(element.parent());
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });

        })

        function dataPick(id) {
            var obj;
            if (id.startsWith('txt')) {
                obj = $("#<% =txtDesignDate.ClientID  %>");

            } else {
                obj = $("#ex" + id);
            }
            obj.datepicker({
                closeText: '关闭',
                prevText: '上月',
                nextText: '下月',
                currentText: '今天',
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
		        '七月', '八月', '九月', '十月', '十一月', '十二月'],
                monthNamesShort: ['一', '二', '三', '四', '五', '六',
		        '七', '八', '九', '十', '十一', '十二'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                weekHeader: '周',
                dateFormat: 'yy-mm-dd',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: true,
                changeMonth: true,
                changeYear: true,
                buttonImage: '../images/calendar.png'
            });
            obj.triggerHandler("focus");
        }

        function showUploadPanels() {
            if ($('#upl20').is(':hidden')) {
                $('#upl20').show();
                $('#upl21').show();
            }
            else if ($('#upl30').is(':hidden')) {
                $('#upl30').show();
                $('#upl31').show();
            }
            else {
                $('#btnMoreUploads').attr("disabled", "disabled");
                alert("一次最多只能添加三个附件！");
            }
        }

        function deleteAttachment(id) {
            var newId = id + "div";
            if (confirm("确定删除附件吗？")) {
                $("#" + newId).hide();
                $("#h" + id).val("DELETED");
            }
        }

        function BeforeSave() {
            var message = "";
            if ($("#<%=txtName.ClientID %>").val().toString().trim() == "") {
                message = "产品名称不能为空！";
            }
            else if ($("#<%=txtPictureNo.ClientID %>").val().toString().trim() == "")
                message = "图号不能为空！";
            if (message != "") {
                alert(message);
                return false;
            } else
                return true;
        }
    </script>
    <div class="master-waper-header-bar">
        <h2>
            <asp:Label runat="server" ID="lblTitle"><%= Request.QueryString["ID"]==null? "添加产品":"修改产品"%></asp:Label></h2>
    </div>
    <div style="margin: 5px 0px 5px 0px; height: 100%;">
        <div id="treeviewDiv" style="float: left; width: 180px; border: 1px solid #aac1de;
            color: #808080; height: 700px; padding: 10px; overflow-y: scroll;">
            <asp:TreeView ID="tvwCategory" runat="server" CollapseImageUrl="../images/treeview-collapse.gif"
                ExpandImageUrl="../images/treeview-expand.gif" OnSelectedNodeChanged="tvwCategory_SelectedNodeChanged"
                SelectedNodeStyle-BackColor="#688fc9" SelectedNodeStyle-ForeColor="White" NodeWrap="false"
                ToolTip="选择需要添加的类别">
            </asp:TreeView>
        </div>
        <div style="float: right; width: 760px;">
            <div class="master-waper-edit-form">
                <table class="product-details-table-admin">
                    <tbody>
                        <tr>
                            <th rowspan="13">
                                主要参数
                            </th>
                            <td class="td2">
                                产品类别
                            </td>
                            <td >
                                <font class="categoryFont" style="color: Red; font-weight: bold;">
                                    <label id="lblparentCategory">
                                        <%=parentCategory!=null?parentCategory.Name:""%></label>
                                    >>
                                    <%=(category != null && category.Name !=null) ? category.Name : "请先从左边选择类别"%>
                                </font>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                名称
                            </td>
                            <td>
                                <asp:TextBox ID="txtName" class="required" runat="server" Text="<%#product.Name%>"
                                    Width="380px"></asp:TextBox>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                设计日期
                            </td>
                            <td>
                                <asp:TextBox ID="txtDesignDate" class="required  dateISO" runat="server" Width="200px" Text='<%#product.DesignDate.ToString("yyyy-MM-dd") %>'> </asp:TextBox>
                                <img alt="calendar" style="cursor: pointer" id="txtDesignDateImg" src="../images/calendar.png"
                                    onclick="dataPick(this.id)" />
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                水泥厂配套生产能力
                            </td>
                            <td>
                                <span>
                                    <asp:DropDownList ID="ddlCementMillProduceAbility" Width="228px" runat="server">
                                    </asp:DropDownList>
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                图号
                            </td>
                            <td>
                                <span>
                                    <asp:TextBox ID="txtPictureNo" runat="server" Width="380px" Text="<%#product.PictureNo %>"></asp:TextBox>
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                规格型号
                            </td>
                            <td>
                                <asp:TextBox ID="txtSpecificationType" Width="380px" runat="server" Text="<%#product.SpecificationType%>"></asp:TextBox>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                用途
                            </td>
                            <td>
                                <asp:TextBox ID="txtPurpose" Width="380px" runat="server" Text="<%#product.Purpose%>"></asp:TextBox>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                用途备注
                            </td>
                            <td>
                                <asp:TextBox ID="txtPurposeRemark" Width="380px" runat="server" Text="<%#product.PurposeRemark%>"></asp:TextBox>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                生产能力
                            </td>
                            <td>
                                <asp:TextBox ID="txtProduceAbility" Width="200px" runat="server" class="number" Text="<%#product.ProduceAbility%>"></asp:TextBox>
                                <asp:DropDownList ID="ddlProduceAbilityUnit" Width="80px" runat="server">
                                </asp:DropDownList>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                主电机功率
                            </td>
                            <td>
                                <asp:TextBox ID="txtMotorPower" Width="200px" runat="server" class="number" Text="<%#product.MotorPower%>"></asp:TextBox>
                                <asp:DropDownList ID="ddlMotorPower" runat="server" Width="80px">
                                </asp:DropDownList>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                单台设备重量
                            </td>
                            <td>
                                <asp:TextBox ID="txtDeviceWeight" Width="200px" runat="server" class="number" Text="<%#product.DeviceWeight%>"></asp:TextBox>
                                <asp:DropDownList ID="dllDeviceWeight" runat="server" Width="80px">
                                </asp:DropDownList>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                制造标准
                            </td>
                            <td>
                                <asp:TextBox ID="txtManufactureStandard" Width="380px" runat="server" Text="<%#product.ManufactureStandard%>"></asp:TextBox>
                            </td>
                        </tr>
                        <tr>
                            <td class="td2">
                                设计单位
                            </td>
                            <td>
                                <asp:TextBox ID="txtDesignUnit" Width="380px" runat="server" Text="<%#product.DesignUnit%>"></asp:TextBox>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <%if (exAttributes != null && exAttributes.Count > 0)
                  {
                      ExtendedAttribute currentAttr = new ExtendedAttribute();
                      //exAttributes = exAttributes.OrderBy<ExtendedAttribute, string>(p => p.Group).ToList<ExtendedAttribute>();
                      exAttributes = exAttributes.OrderBy<ExtendedAttribute, int>(p => p.Order).ToList<ExtendedAttribute>();
                      bool unEqual = true;
                      int groupCount = 1; %>
                <table class="product-details-table-admin">
                    <tbody>
                        <tr>
                            <th style="width:82px" rowspan="<%=exAttributes.Count + 1%>">
                                设计参数
                            </th>
                        </tr>
                        <% foreach (var attr in exAttributes)
                           {
                               if ((LogonUser.Role.Equals(UserRole.SeniorOperator) && !attr.AttributeCategory.Equals(ExtendedAttributeCategory.Produce)) 
                                  || ((LogonUser.Role.Equals(UserRole.SeniorDesigner) && attr.AttributeCategory.Equals(ExtendedAttributeCategory.Produce)) 
                                   )){continue;}
                               
                               if (!attr.Group.Equals(currentAttr.Group))
                               {
                                   unEqual = true;
                                   currentAttr = attr;
                               }
                               else
                                   unEqual = false;
                               groupCount = exAttributes.Where<ExtendedAttribute>(p => p.Group.Equals(currentAttr.Group)).Count<ExtendedAttribute>();
                               %>
                        <tr>
                            <%if (unEqual)
                              {%>
                                <th  rowspan="<%=groupCount %>" style="width:170px !important;"><%=attr.Group%></th>
                            <%} %>
                            <td class="td2" style="padding-right:0px; width:200px !important;">
                                <%=attr.DisplayName%>
                            </td>
                            <td  style="width:380px !important;">
                                <% if (attr.DataType != null && attr.DataType.ToUpper() == "STRING")
                                   { %>
                                <input style="width: 230px;" type="text" id="exAttrValue<%=attr.Id%>" name="exAttrValue<%=attr.Id%>" maxlength="500"
                                    value="<%=(product!=null && product.ExtendedAttributes.Count>0 && product.ExtendedAttributes.Where(c=>c.Attribute.Id == attr.Id ).ToList().Count()>0 )?Server.HtmlEncode(product.ExtendedAttributes.Where(c=>c.Attribute.Id == attr.Id ).First().Value):"" %>" />
                                <%}
                                   else if (attr.DataType != null && attr.DataType.ToUpper() == "DATETIME")
                                   { %>
                                <span>
                                    <input style="width: 230px;" class="dateISO" type="text" id="exAttrValue<%=attr.Id%>"
                                        name="exAttrValue<%=attr.Id%>" value="<%=(product!=null && product.ExtendedAttributes.Count>0 && product.ExtendedAttributes.Where(c=>c.Attribute.Id == attr.Id ).ToList().Count()>0)?Server.HtmlEncode(product.ExtendedAttributes.Where(c=>c.Attribute.Id == attr.Id ).First().Value):"" %>" />
                                    <img alt="calendar" style="cursor: pointer" id="AttrValue<%=attr.Id%>" src="../images/calendar.png"
                                        onclick="dataPick(this.id)" />
                                </span>
                                <%}
                                   else
                                   { %>
                                <input style="width: 200px;" class="number" type="text" id="exAttrValue<%=attr.Id%>"
                                    name="exAttrValue<%=attr.Id%>" value="<%=(product!=null && product.ExtendedAttributes.Count>0 && product.ExtendedAttributes.Where(c=>c.Attribute.Id == attr.Id ).ToList().Count()>0)?Server.HtmlEncode(product.ExtendedAttributes.Where(c=>c.Attribute.Id == attr.Id ).First().Value):"" %>" />
                                <%} %>
                            </td>
                        </tr>
                        <%} %>
                    </tbody>
                </table>
                <%} %>
                <table class="product-details-table-admin" style="margin: 8px 0px 0px 10px;">
                    <thead style="font-size: 14px; font-weight: bold; margin-bottom: 5px;">
                        附件:<img onclick="showUploadPanels()" id="btnMoreUploads" style="width: 16px; height:16px; margin-left:20px;cursor:pointer;" src="../images/plus.png" />
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <asp:FileUpload ID="flpAttachment1" Width="400px" Height="20px" runat="server" />
                            </td>
                        </tr>
                        <tr id="upl20" style="display: none;">
                            <td>
                                <asp:FileUpload ID="flpAttachment2" Width="400px" Height="20px" runat="server" />
                            </td>
                        </tr>
                        <tr id="upl30" style="display: none;">
                            <td>
                                <asp:FileUpload ID="flpAttachment3" Width="400px" Height="20px" runat="server" />
                            </td>
                        </tr>
                        <tr style="height: 0px; border: 0px !important;">
                            <td style="height: 0px; border: 0px;">
                            </td>
                        </tr>
                    </tbody>
                </table>
                <%if (product != null && product.Attachments != null && product.Attachments.Count > 0)
                  { %>
                <table class="product-details-table-admin" style="margin: 8px 0px 0px 10px;">
                    <thead style="font-size: 14px; font-weight: bold;">
                        已添加附件：
                    </thead>
                    <tbody>
                        <% foreach (var att in product.Attachments)
                           {   %>
                        <tr id="attachment<%=att.Id%>div">
                            <td>
                                <div>
                                    <a href='<%= filePath %><%=att.NewName %>'>
                                        <%=att.OriginalName%></a>
                                    <input type="hidden" name="attachment<%=att.Id%>" value="" id="hattachment<%=att.Id%>" />
                                    <img id="attachment<%=att.Id%>" style=" margin-left:20px; cursor:pointer;" onclick="deleteAttachment(this.id)" src="../Styles/images/unchecked.gif" />
                                </div>
                            </td>
                        </tr>
                        <%} %>
                    </tbody>
                </table>
                <%} %>
                <div style="text-align: right; padding: 5px 120px 5px;">
                    <input type="button" id="btnCancel" onclick="javascript:history.back()" value="返回列表"
                        style="width: 80px;" />
                    <asp:Button ID="btnAddProduct" runat="server" Text="添加" Width="80px" OnClick="btnAddProduct_Click" OnClientClick="return BeforeSave()" />
                    <asp:Button ID="btnUpdateProduct" runat="server" Text="更新" Width="80px" OnClick="btnUpdateProduct_Click" OnClientClick="return BeforeSave()" />
                </div>
            </div>
        </div>
    </div>
</asp:Content>
