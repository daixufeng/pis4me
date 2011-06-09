using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using iBatis.DAL.DSys;
using iBatis.Factory;
using iBatis.Model.Sys;

namespace WebUI
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Response.Redirect("Demo.aspx");
        }
    }
}
