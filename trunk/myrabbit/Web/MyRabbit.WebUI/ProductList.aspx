<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeBehind="ProductList.aspx.cs"
    Inherits="SINOMA.WebUI.ProductList" %>

<%@ Register Assembly="SINOMA.UserControl" Namespace="SINOMA.UserControl" TagPrefix="aspx" %>
<%@ Import Namespace="MyRabbit.Data.Entity" %>
<%@ Import Namespace="System.Linq" %>
<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
    <script type="text/javascript">
        $(function () {
            $("#tCondition").click(function () {
                if ($("#panelContidion").css("display") == "block") {
                    $("#panelContidion").slideUp("fast");
                    $(this).text("+ 展开条件");
                    $(this).attr("title", "显示查询条件");
                    document.getElementById("<%=hdfToggleStatus.ClientID %>").value = "true";
                } else {
                    $("#panelContidion").slideDown("fast");
                    $(this).text("- 收起条件");
                    $(this).attr("title", "隐藏查询条件");
                    document.getElementById("<%=hdfToggleStatus.ClientID %>").value = "false";
                }
            })

            if (document.getElementById("<%=hdfToggleStatus.ClientID %>").value == "true") {
                $("#tCondition").text("+ 展开条件");
                $("#tCondition").attr("title", "显示查询条件");
            } else {
                $("#tCondition").text("- 收起条件");
                $("#tCondition").attr("title", "隐藏查询条件");
            }
        })

        function ConditionClick(o) {
            document.getElementById("<% =hdfValue.ClientID%>").value = $(o).attr("keyvalue");
        }
    </script>
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <div>
        <div class="master-waper-header-bar">
            <div style="float: left;">
                <asp:Label runat="server" ID="lblTitle"></asp:Label></div>
            <div style="float: right;">
                <span id="tCondition" style="float: right; cursor: pointer;">- 收起条件<%=lblPictureNO %></span></div>
        </div>
        <asp:UpdatePanel runat="server" ID="mainUpl" ChildrenAsTriggers="true">
            <Triggers>
                <asp:AsyncPostBackTrigger ControlID="listPager" />
            </Triggers>
            <ContentTemplate>
                <div class="master-waper-content-main">
                    <div id="panelContidion" class="master-waper-search-condition" style="display: <%=hdfToggleStatus.Value.Equals("true")?"none":"block" %>">
                        <div class="master-waper-search-condition-category">
                            <asp:Panel runat="server" ID="panelCdnCategory" Visible="false">
                                <dl>
                                    <dd>
                                        <ul>
                                            <%foreach (var o in categories)
                                              { %>
                                            <li><a href="ProductList.aspx?categoryId=<%=o.Id %>">
                                                <%=string.IsNullOrEmpty(keyWord)? o.Name: o.Name.Replace(keyWord, "<font color=\"red\">" + keyWord + "</font>")%>(<%=products.Where<Product>(p => p.Category.Equals(o)).Count<Product>()%>)</a></li>
                                            <%} %>
                                        </ul>
                                    </dd>
                                </dl>
                            </asp:Panel>
                        </div>
                        <div class="master-waper-search-condition-list">
                            <asp:Panel runat="server" ID="panelCdnAttribute" Visible="false">
                                <!--ChildCategorie display -->
                                <%if (childCategories.Count > 0)
                                  { %>
                                <dl>
                                    <dt>类别：</dt>
                                    <dd class="w40">
                                        <a keyvalue="{Key:'Category'}" onclick="ConditionClick(this,'clear')" href="javascript:__doPostBack(&#39;<%=AttrAddButton.UniqueID %>&#39;,&#39;&#39;)"
                                            class="<%= selectedConditions.Where<SelectedCondition>(p=>p.Key.Equals("Category")).Count<SelectedCondition>() > 0 ? "" : "here"%>">
                                            全部</a></dd>
                                    </dd>
                                    <dd>
                                        <%foreach (var o in childCategories)
                                          { %>
                                        <a keyvalue="{Key:'Category',ValueMember:'<%=o.Id%>',DisplayMember:'<%=o.Name %>'}"
                                            href="javascript:__doPostBack(&#39;<%=AttrAddButton.UniqueID %>&#39;,&#39;&#39;)"
                                            class="<%= selectedConditions.Where<SelectedCondition>(p => p.Key.Equals("Category") && p.ValueMember.Equals(o.Id.ToString())).Count<SelectedCondition>() > 0? "here":""%>"
                                            onclick="ConditionClick(this,'add')">
                                            <%= o.Name%></a>
                                        <%} %></dd>
                                </dl>
                                <%} %>
                                <!--Attribute display -->
                                <%foreach (var o in attributeCondition)
                                  {
                                      if (o.Name.Equals("PictureNO"))
                                      { %>
                                <dl>
                                    <dt>
                                        <%=o.DisplayName%>：</dt>
                                    <dd>
                                        <input type="text" keyvalue="{Key:'<%=o.Name %>',ValueMember:'<%=lblPictureNO %>',DisplayMember:'<%=lblPictureNO %>'}"
                                            id="txtPictureNO" name="txtPictureNO" value="<%=lblPictureNO %>" /><input type="button" value="提交" onclick="javascript:__doPostBack(&#39;<%=AttrAddButton.UniqueID %>&#39;,&#39;&#39;);" /></dd></dl>
                                <% }
                                      else if (o.Name.Equals("SpecificationType"))
                                      {
                                %>
                                <dl>
                                    <dt>
                                        <%=o.DisplayName%>：</dt>
                                    <dd>
                                        <input type="text" keyvalue="{Key:'<%=o.Name %>',ValueMember:'<%=lblSpec %>',DisplayMember:'<%=lblSpec %>'}"
                                            id="txtSpec" name="txtSpec" value="<%=lblSpec %>"/><input type="button" value="提交" onclick="javascript:__doPostBack(&#39;<%=AttrAddButton.UniqueID %>&#39;,&#39;&#39;);"/></dd></dl>
                                <%
                                   }
                                      else if (o.Values.Count > 0)
                                      { %>
                                <dl>
                                    <dt>
                                        <%=o.DisplayName%>：</dt>
                                    <dd class="w40">
                                        <a keyvalue="{Key:'<%=o.Name %>'}" onclick="ConditionClick(this,'clear')" href="javascript:__doPostBack(&#39;<%=AttrAddButton.UniqueID %>&#39;,&#39;&#39;)"
                                            class="<%= selectedConditions.Where<SelectedCondition>(p=>p.Key.Equals(o.Name)).Count<SelectedCondition>() > 0 ? "":"here"%>">
                                            全部</a></dd>
                                    <dd>
                                        <%foreach (var value in o.Values)
                                          { %>
                                        <a keyvalue="{Key:'<%=o.Name %>',ValueMember:'<%=value %>',DisplayMember:'<%=value %>'}"
                                            href="javascript:__doPostBack(&#39;<%=AttrAddButton.UniqueID %>&#39;,&#39;&#39;)"
                                            <%= selectedConditions.Where<SelectedCondition>(p => p.Key.Equals(o.Name)).Count<SelectedCondition>() > 0 && selectedConditions.Single<SelectedCondition>(p => p.Key.Equals(o.Name)).ValueMember == value.ToString() ? "class='here'":""%>
                                            onclick="ConditionClick(this,'add')">
                                            <%= value.ToString()%></a>
                                        <%} %></dd>
                                </dl>
                                <%}
                                  }%>
                                <%if (selectedConditions.Count > 0 && isConditionListShow)
                                  { %>
                                <dl class="bg">
                                    <dt>选择结果： </dt>
                                    <dd>
                                        <span>
                                            <%foreach (var o in selectedConditions)
                                              {
                                                  if (o.Key.Equals("Category") && !isParentCategory) continue;%>
                                            <a keyvalue="{Key:'<%=o.Key %>'}" href="javascript:__doPostBack(&#39;<%=AttrAddButton.UniqueID %>&#39;,&#39;&#39;)"
                                                onclick="ConditionClick(this,'clear')" class="close">
                                                <%=o.DisplayMember %></a>
                                            <%} %>
                                        </span><a keyvalue="" href="javascript:__doPostBack(&#39;<%=AttrAddButton.UniqueID %>&#39;,&#39;&#39;)"
                                            onclick="ConditionClick(this,'clear')" class="reset"></a>
                                    </dd>
                                </dl>
                                <%} %>
                            </asp:Panel>
                        </div>
                    </div>
                    <div style="height: 30px; position: relative;">
                        <aspx:ListViewPager runat="server" ID="listPager" PageSize="5" OnPageIndexChanged="ListPageIndexChange"
                            IconImageUrl="images/listview-pager.png" />
                    </div>
                    <div class="master-waper-product-list">
                        <%for (int i = 0; i < displayList.Count; i++)
                          {
                              var o = displayList[i]; %>
                        <div class='<%=i % 2 == 0 ? "master-waper-product-item" : "master-waper-product-item_w" %>'>
                            <dl>
                                <dt><a title="<%=o.Name %>" href="ProductDetail.aspx?productId=<%=o.Id %>">
                                    <%=string.IsNullOrEmpty(keyWord) ? o.Name : o.Name.Replace(keyWord, "<font color=\"red\">" + keyWord + "</font>")%></a>
                                </dt>
                                <dd>
                                    规格型号：
                                    <%=string.IsNullOrEmpty(keyWord) ? o.SpecificationType: o.SpecificationType.Replace(keyWord,"<font color=\"red\">"+keyWord+"</font>")%></dd>
                                <dd>
                                    用途：
                                    <%=string.IsNullOrEmpty(keyWord) ?o.Purpose: o.Purpose.Replace(keyWord,"<font color=\"red\">"+keyWord+"</font>")%></dd>
                                <dd>
                                    生产能力<%=o.ProduceAbilityUnit.UnitName%>：
                                    <%=o.ProduceAbility%></dd>
                                <dd>
                                    水泥厂配套生产能力：<%=o.CementAbility %></dd>
                                <dd>
                                    主电动机功率<%=o.MotorPowerUnit.UnitName%>：
                                    <%=o.MotorPower%></dd>
                                <dd>
                                    设计日期：
                                    <%=DisplayDateTime(o.DesignDate)%></dd>
                                <dd>
                                    图号：
                                    <%=string.IsNullOrEmpty(keyWord) ? o.PictureNo : o.PictureNo.Replace(keyWord, "<font color=\"red\">" + keyWord + "</font>")%></dd>
                                <dd>
                                    设计单位：
                                    <%=string.IsNullOrEmpty(keyWord) ? o.DesignUnit : o.DesignUnit.Replace(keyWord, "<font color=\"red\">" + keyWord + "</font>")%></dd>
                                <dd class="n1">
                                    <a title="<%=o.Name %>" href="ProductDetail.aspx?productId=<%=o.Id %>">详细参数</a>&gt;&gt;
                                </dd>
                            </dl>
                        </div>
                        <%} %>
                    </div>
                </div>
                <div style="display: none;">
                    <asp:LinkButton runat="server" ID="AttrAddButton" OnClick="AttributePostBack"></asp:LinkButton>
                    <asp:HiddenField runat="server" ID="hdfValue" />
                    <asp:HiddenField runat="server" ID="hdfToggleStatus" Value="false" />
                </div>
            </ContentTemplate>
        </asp:UpdatePanel>
    </div>
</asp:Content>
