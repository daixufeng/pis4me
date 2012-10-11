using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using SINOMA.App.Entity;
using SINOMA.App.BLL;

namespace SINOMA.WebUI
{
    public partial class Details : AuthBasePage
    {
        #region Fields
        protected Product product = new Product();
        protected string rootPath;
        #endregion

        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                if (LogonUser.Role.Equals(UserRole.Designer))
                {
                    tplMarketingRecord.Visible = false;
                }
                if (!IsPostBack)
                {
                    if (!string.IsNullOrEmpty(Request.QueryString["productId"]))
                    {
                        int id = Int32.Parse(Request.QueryString["productId"]);
                        product = productManager.GetProductById(id);
                    }
                }
                rootPath = this.RootPath + "Attachments/";
            }
            catch (Exception ex)
            {
                Log.Error(ex);
            }
        }
    }
}