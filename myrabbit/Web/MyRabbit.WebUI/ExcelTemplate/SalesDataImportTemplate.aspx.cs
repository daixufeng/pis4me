using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.Data.Entity;

namespace SINOMA.WebUI.Temp
{
    public partial class SalesDataImportTemplate :AuthBasePage
    {
        public IList<Product> products = new List<Product>();

        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                Response.AppendHeader("Content-Description", "attachment;filename=ProductImprotTemplate.xls");
                Response.ContentType = "application/vnd.ms-excel";
                int categoryId = int.Parse(Request.QueryString["categoryId"]);

                products = productManager.GetProductByCategory(categoryId, false);
            }
            catch (Exception ex)
            {
                Log.Error(ex);
            }
        }
    }
}