using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using log4net;
using System.Web.SessionState;
using MyRabbit.Entity;
using MyRabbit.WebUI;
using MyRabbit.Service;

namespace MyRabbit.WebUI.Handlers
{
    /// <summary>
    /// Summary description for Handler1
    /// </summary>
    public class UserMgtHandler : IHttpHandler, IRequiresSessionState
    {
        #region Fields
        private ILog _log = null;
        private ILog Log
        {
            get
            {
                if (_log == null) _log = LogManager.GetLogger(this.GetType());

                return _log;
            }
        }

        public bool IsReusable
        {
            get
            {
                return false;
            }
        }

        private UserService userService = new UserService();
        #endregion

        public void ProcessRequest(HttpContext context)
        {
            try
            {
                User operateUser = context.Session[GlobalKeys.LogonUser] as User;

                string messageFormat = "requestUrl:{0},OperateUser:{1},Message:{2}";
                string logMessage = string.Empty;

                if (operateUser == null)
                {
                    logMessage = string.Format(messageFormat, context.Request.Url, "null", "Invalidate Request");
                }
                else if (!operateUser.RoleId.Equals("Admin"))
                {
                    logMessage = string.Format(messageFormat, context.Request.Url, operateUser.NickName, "Access denied");
                }
                else
                {
                    string userName = context.Request.QueryString["userName"];//用户名
                    string loginName = context.Request.QueryString["loginName"];//用户账户
                    User user = null;
                    
                    if (!string.IsNullOrEmpty(userName))
                    {
                        user = userService.GetByName(userName);
                    }
                    else if (!string.IsNullOrEmpty(loginName))
                    {
                        user = userService.GetByLoginName(loginName);
                    }

                    string respVal = user != null ? "{returnValue: true}" : "{returnValue: false}";
                    context.Response.Write(respVal);
                }

                if (!string.IsNullOrEmpty(logMessage))
                    Log.Debug(logMessage);
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                context.Response.Write("{returnValue: false}");
            }
        }
    }
}