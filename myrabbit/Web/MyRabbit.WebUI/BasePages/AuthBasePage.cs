using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;
using System.Globalization;
using System.Resources;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using System.Threading;
using System.Web.Script.Serialization;
using MyRabbit.Entity;

namespace MyRabbit.WebUI
{
    /// <summary>
    /// Logon Base page
    /// </summary>
    public class AuthBasePage : BasePage
    {
        #region Protected Methods
        /// <summary>
        /// BasePage Init Event
        /// </summary>
        /// <param name="e"></param>
        protected override void OnInit(EventArgs e)
        {
            base.OnInit(e);

#if DEBUG
            if (LogonUser == null)
            {
                LogonUser = userService.GetById(1);
            }
#endif
            if (LogonUser == null)
            {
                //ShowMessage("请登录", "Default.aspx");
                //ScriptRedirect("Login.aspx");
                Response.Redirect("~/Login.aspx?url=" + Request.RawUrl, true);
            }
            //if (LogonUser.RoleName.Equals(MyRabbit.Entity.UserRole.Designer)
            //    || LogonUser.Role.Equals(MyRabbit.Entity.UserRole.Operator)
            //    || LogonUser.Role.Equals(MyRabbit.Entity.UserRole.Salesman)) {
            //        Response.Redirect("~/Default.aspx", true);
            //}
        }

        /// <summary>
        /// Over write the Culture Initialition
        /// </summary>
        protected override void InitializeCulture()
        {
            //base.InitializeCulture();
            //string currentLanguage = Language;
            //if (!string.IsNullOrEmpty(currentLanguage) && currentLanguage != "Auto")
            //{
            //    this.UICulture = Language;
            //    Thread.CurrentThread.CurrentCulture = CultureInfo.CreateSpecificCulture(Language);
            //}
        }

        /// <summary>
        /// User Operation log
        /// </summary>
        /// <param name="type">
        /// Operation Type: Insert,Update,Delete</param>
        /// <param name="message">Message about operation such as : Add product 回转窑</param>
        protected void WriteOperationLog(OperationLogType type, string message)
        {
            try
            {
                //OperationLog log = new OperationLog()
                //{
                //    OperationType = type,
                //    Operator = LogonUser,
                //    CreateTime = DateTime.Now,
                //    Message = message
                //};
                //operationLogManager.WriteOperationLog(log);
            }
            catch (Exception ex)
            {
                Log.Error(ex);
            }
        }
        #endregion
    }
}
