using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.Utility;
using Microsoft.Practices.Unity;
using MyRabbit.IService;

namespace MyRabbit.WebUI
{
    public partial class Account: BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {

            try
            {
                if (!IsPostBack)
                {
                    if (LogonUser == null)
                    {
                        Response.Redirect("~/Default.aspx", true);
                    }
                    else
                    {
                        txtLoginName.Text = LogonUser.LoginName;
                    }
                }
            }
            catch (Exception ex)
            {
                SysLog.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            try
            {
                DESEncrypt desEncrypt = new DESEncrypt();
                LogonUser.Password = desEncrypt.Encrypt(txtPassword.Text);
                userService.Update(LogonUser);
                ShowMessage("密码修改成功，请重新登录！","Logout.aspx");
            }
            catch (Exception ex)
            {
                SysLog.Error(ex);
                RedirectToErrorPage();
            }
        }
    }
}