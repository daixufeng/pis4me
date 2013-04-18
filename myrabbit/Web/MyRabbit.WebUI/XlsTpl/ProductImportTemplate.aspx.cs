using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.Data.Entity;

namespace SINOMA.WebUI.Temp
{
    public partial class ProductImportTemplate : BasePage
    {
        protected IList<ExtendedAttribute> attributes = new List<ExtendedAttribute>();

        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                Response.AppendHeader("Content-Description", "attachment;filename=ProductImprotTemplate.xls");
                Response.ContentType = "application/vnd.ms-excel";
                
                int categoryId = int.Parse(Request.QueryString["CategoryId"]);
                attributes = extendedAttributeManager.GetExtendedAttributeByCategoryId(categoryId);
                if (attributes == null)
                    attributes = new List<ExtendedAttribute>();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                Response.Clear();
            }
        }
    }
}