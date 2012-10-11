using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.WebUI;

namespace SINOMA.WebUI
{
    public partial class Login : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            try
            {
                string loginName = txtLoginName.Text.Trim();
                string password = txtPassword.Text.Trim();

                if (string.IsNullOrEmpty(loginName))
                    ShowMessage("请输入用户名。");

                if (string.IsNullOrEmpty(password))
                    ShowMessage("请输入密码。");

                UserLoginComponent userLoginComponent = new UserLoginComponent();
                string errMessage = userLoginComponent.UserAuthenticate(loginName, password);
                if (string.IsNullOrEmpty(errMessage))
                {
                    string url = Request.QueryString["url"];
                    if (!string.IsNullOrEmpty(url))
                        Response.Redirect(url, true);
                    else
                        Response.Redirect("~/Default.aspx", true);
                }
                else
                    ShowMessage(errMessage);
            }
            catch (Exception ex)
            {
                if (!ex.GetType().Equals(typeof(System.Threading.ThreadAbortException)))
                    Log.Error(ex);
            }
        }
    }
}