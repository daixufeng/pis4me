<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ProductExportTemplate.aspx.cs" Inherits="SINOMA.WebUI.Temp.ProductExportTemplate" %>
<%@ Import Namespace="SINOMA.App.Entity" %>
<%@ Import Namespace="System.Linq" %>
<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <meta name="ProgId" content="Excel.Sheet">
    <meta name="Generator" content="Microsoft Excel 11">
    <style >
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
.xl1518375
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6518375
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	background:#A6A6A6;
	mso-pattern:black none;
	white-space:nowrap;}
.xl6618375
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	background:#A6A6A6;
	mso-pattern:black none;
	white-space:nowrap;}
.xl6718375
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	background:#C4D79B;
	mso-pattern:black none;
	white-space:nowrap;}
.xl6818375
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	background:#538DD5;
	mso-pattern:black none;
	white-space:nowrap;}
.shortdate
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:"Short Date";
	text-align:general;
	vertical-align:bottom;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#C4D79B;
	mso-pattern:black none;
	white-space:nowrap;}
-->
</style>
</head>
<body>
    <!--[if !excel]>&nbsp;&nbsp;<![endif]-->
    <!--The following information was generated by Microsoft Excel's Publish as Web
Page wizard.-->
    <!--If the same item is republished from Excel, all information between the DIV
tags will be replaced.-->
    <!----------------------------->
    <!--START OF OUTPUT FROM EXCEL PUBLISH AS WEB PAGE WIZARD -->
    <!----------------------------->
    <div id="Book1_18375" align="center" x:publishsource="Excel">
        <table border="0" cellpadding="0" cellspacing="0" width="1818" style='border-collapse: collapse;
            table-layout: fixed; width: 1366pt'>
            <col width="70" style='mso-width-source: userset; mso-width-alt: 2560; width: 53pt'>
            <col width="101" style='mso-width-source: userset; mso-width-alt: 3693; width: 76pt'>
            <col width="119" style='mso-width-source: userset; mso-width-alt: 4352; width: 89pt'>
            <col width="64" style='mso-width-source: userset; mso-width-alt: 2340; width: 48pt'>
            <col width="67" span="3" style='mso-width-source: userset; mso-width-alt: 2450; width: 50pt'>
            <col width="98" span="2" style='mso-width-source: userset; mso-width-alt: 3584; width: 74pt'>
            <col width="144" style='mso-width-source: userset; mso-width-alt: 5266; width: 108pt'>
            <col width="82" style='mso-width-source: userset; mso-width-alt: 2998; width: 62pt'>
            <col width="113" span="2" style='mso-width-source: userset; mso-width-alt: 4132;
                width: 85pt'>
            <col width="98" style='mso-width-source: userset; mso-width-alt: 3584; width: 74pt'>
            <col width="129" span="2" style='mso-width-source: userset; mso-width-alt: 4717;
                width: 97pt'>
            <col width="67" span="2" style='mso-width-source: userset; mso-width-alt: 2450; width: 50pt'>
            <col width="125" style='mso-width-source: userset; mso-width-alt: 4571; width: 94pt'>
            <tr height="20" style='height: 15.0pt'>
                <td height="20" class="xl6618375" width="70" style='height: 15.0pt; width: 53pt'>
                    名称
                </td>
                <td class="xl6518375" width="67" style='border-left: none; width: 50pt'>
                    规格型号
                </td>
                <td class="xl6518375" width="101" style='width: 76pt'>
                    用途
                </td>
               <%-- <td class="xl6518375" width="119" style='border-left: none; width: 89pt'>
                    用途备注
                </td>--%>
                <td class="xl6518375" width="64" style='border-left: none; width: 48pt'>
                    图号
                </td>
                <td class="xl6518375" width="67" style='border-left: none; width: 50pt'>
                    设计日期
                </td>
                <td class="xl6518375" width="144" style='border-left: none; width: 144pt'>
                    水泥厂配套生产能力
                </td>
                <td class="xl6518375" width="67" style='border-left: none; width: 50pt'>
                    生产能力
                </td>
                <td class="xl6518375" width="98" style='border-left: none; width: 74pt'>
                    生产能力单位
                </td>
                <td class="xl6518375" width="98" style='border-left: none; width: 74pt'>
                    生产能力备注
                </td>
                <td class="xl6518375" width="82" style='border-left: none; width: 62pt'>
                    主电机功率
                </td>
                <td class="xl6518375" width="113" style='border-left: none; width: 85pt'>
                    主电机功率单位
                </td>
                <td class="xl6518375" width="113" style='border-left: none; width: 85pt'>
                    主电机功率备注
                </td>
                <td class="xl6518375" width="98" style='border-left: none; width: 74pt'>
                    单台设备重量
                </td>
                <td class="xl6518375" width="129" style='border-left: none; width: 97pt'>
                    单台设备重量单位
                </td>
                <td class="xl6518375" width="129" style='border-left: none; width: 97pt'>
                    单台设备重量备注
                </td>
                <td class="xl6518375" width="67" style='border-left: none; width: 50pt'>
                    设计标准
                </td>
                <td class="xl6518375" width="67" style='border-left: none; width: 50pt'>
                    设计单位
                </td>
                <% foreach (var o in attributes)
                   {%>
                <td class="xl6518375" width="125" style='border-left: none; width: 94pt'>
                    <%=o.DisplayName %>
                </td>
                <%} %>
            </tr>
            <%foreach (var o in products)
              { %>
            <tr height="20" style='height: 15.0pt'>
                <td height="20" class="xl6718375" style='height: 15.0pt; border-top: none'>
                    <%=o.Name%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.SpecificationType%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.Purpose%><%=o.PurposeRemark%>
                </td>
               <%-- <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.PurposeRemark %>
                </td>--%>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.PictureNo%>
                </td>
                <td class="shortdate" style='border-top: none; border-left: none'>
                    <%=DisplayDateTime(o.DesignDate, false)%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.CementAbility%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.ProduceAbility%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.ProduceAbilityUnit.UnitName%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.ProduceAbilityRemark%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.MotorPower%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.MotorPowerUnit.UnitName%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.MotorPowerRemark%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.DeviceWeight%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.DeviceWeightUnit.UnitName%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.DeviceWeightRemark%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.ManufactureStandard%>
                </td>
                <td class="xl6718375" style='border-top: none; border-left: none'>
                    <%=o.DesignUnit%>
                </td>
                <%foreach (var attr in attributes)
                  { %>
                <td class=<%=attr.DataType.Equals("DateTime") ? "shortdate": "xl6718375"%> style='border-top: none; border-left: none'>
                    <%
                      System.Collections.Generic.IList<ProductExtendedAttributeValue> vals
                          = o.ExtendedAttributes.Where<ProductExtendedAttributeValue>(p => p.Attribute.Equals(attr))
                          .ToList<ProductExtendedAttributeValue>();
                      if (vals.Count > 0)
                      {%>
                         <%= vals[0].Value%> 
                      <%}%>
                </td>
                <%} %>
            </tr>
            <%} %>
            <![if supportMisalignedColumns]>
            <tr height="0" style='display: none'>
                <td width="70" style='width: 53pt'>
                </td>
                <td width="101" style='width: 76pt'>
                </td>
                <td width="119" style='width: 89pt'>
                </td>
                <td width="64" style='width: 48pt'>
                </td>
                <td width="67" style='width: 50pt'>
                </td>
                <td width="67" style='width: 50pt'>
                </td>
                <td width="67" style='width: 50pt'>
                </td>
                <td width="98" style='width: 74pt'>
                </td>
                <td width="98" style='width: 74pt'>
                </td>
                <td width="144" style='width: 108pt'>
                </td>
                <td width="82" style='width: 62pt'>
                </td>
                <td width="113" style='width: 85pt'>
                </td>
                <td width="113" style='width: 85pt'>
                </td>
                <td width="98" style='width: 74pt'>
                </td>
                <td width="129" style='width: 97pt'>
                </td>
                <td width="129" style='width: 97pt'>
                </td>
                <td width="67" style='width: 50pt'>
                </td>
                <td width="67" style='width: 50pt'>
                </td>
                <td width="125" style='width: 94pt'>
                </td>
            </tr>
            <![endif]>
        </table>
    </div>
    <!----------------------------->
    <!--END OF OUTPUT FROM EXCEL PUBLISH AS WEB PAGE WIZARD-->
    <!----------------------------->
</body>
</html>
