using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace MyRabbit.WebUI
{
    public partial class Logout :BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                UserLoginComponent userLoginComponent = new UserLoginComponent();
                userLoginComponent.UserLogout();
            }
            catch (Exception ex)
            {
                SysLog.Error(ex);
            }
            finally
            {
                Session.Clear();
                Session.Abandon();
                Response.Redirect("~/Default.aspx", true);
            }
        }
    }
}