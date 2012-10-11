using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.Data.Entity;

namespace SINOMA.WebUI.Temp
{
    public partial class ProductExportTemplate : BasePage
    {
        protected IList<ExtendedAttribute> attributes = new List<ExtendedAttribute>();
        public IList<Product> products = new List<Product>();

        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                Response.ContentType = "application/vnd.ms-excel";
                int categoryId = int.Parse(Request.QueryString["categoryId"]);
                attributes = extendedAttributeManager.GetExtendedAttributeByCategoryId(categoryId);
                if (attributes == null)
                    attributes = new List<ExtendedAttribute>();
                products = productManager.GetProductByCategory(categoryId, false);
                Response.AppendHeader("Content-Description", "attachment;filename=Products.xls");
            }
            catch (Exception ex)
            {
                Log.Error(ex);
            }
        }
    }
}