using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using MyRabbit.Entity;
using MyRabbit.IService;
using log4net;
using Microsoft.Practices.Unity;

namespace MyRabbit.WebUI
{
    /// <summary>
    /// Un logon Base Page
    /// </summary>
    public class BasePage : System.Web.UI.Page
    {
        #region Fields

        protected IBugService  bugService{get;set;}

        protected IBugTrackService bugTrackService {get;set;}

        protected IProjectService projectService{get;set;}

        protected ITaskService taskService {get;set;}

        protected ITaskTrackService taskTrackService { get; set; }

        [Dependency]
        public IUserService userService { get; set; }
        /// <summary>
        /// Server root path
        /// </summary>
        protected string RootPath
        {
            get
            {
                return Request.UrlReferrer != null ? "http://" + Request.UrlReferrer.Authority + "/" : "/";
            }
        }

        /// <summary>
        /// Employee object 
        /// </summary>
        public User LogonUser
        {
            get
            {
                if (Session[GlobalKeys.LogonUser] == null)
                {
                    return null;
                }
                else
                {
                    return Session[GlobalKeys.LogonUser] as User;
                }
            }
            set
            {
                Session[GlobalKeys.LogonUser] = value;
            }
        }

        /// <summary>
        /// Is user online
        /// </summary>
        public bool Online
        {
            get
            {
                return LogonUser != null;
            }
        }

        /// <summary>
        /// Error Page
        /// </summary>
        protected string ErrorPageUrl
        {
            get
            {
                return RootPath + "Common/ErrorPages/ErrorPage.aspx";
            }
        }


        private ILog _log = null;
        /// <summary>
        /// Application Log
        /// </summary>
        protected ILog SysLog
        {
            get
            {
                if (_log == null) _log = LogManager.GetLogger(this.GetType());

                return _log;
            }
        }

        /// <summary>
        /// 是否可以单个用户，多次登录
        /// </summary>
        protected bool SingleLogin
        {
            get
            {
                string singleLogin = System.Configuration.ConfigurationManager.AppSettings["SingleLogin"];
                if (singleLogin.Trim().ToLower().Equals("true"))
                    return true;
                else
                    return false;
            }
        }
        #endregion

        #region Protected methods
        /// <summary>
        /// Show Message
        /// </summary>
        /// <param name="strMessage">message string</param>
        protected void ShowMessage(string strMessage)
        {
            ScriptManager.RegisterClientScriptBlock(this.Page, Page.GetType(), "", "alert('" + strMessage + "');", true);
        }

        /// <summary>
        /// Show Message
        /// </summary>
        /// <param name="strMessage">message string</param>
        /// <param name="redirectUrl">redirect url</param>
        protected void ShowMessage(string strMessage, string redirectUrl)
        {
            string strScript = "alert('{0}');window.location.href='{1}';";

            if (redirectUrl.Substring(0, 1).Equals("/")) 
                redirectUrl = redirectUrl.Substring(1, redirectUrl.Length - 1);

            strScript = string.Format(strScript, strMessage, redirectUrl);

            ScriptManager.RegisterClientScriptBlock(this.Page, this.GetType(), "", strScript, true);
        }

        protected void GoToErrorPage()
        {
            ScriptRedirect(ErrorPageUrl);
        }

        /// <summary>
        /// redirect url example: admin/default.aspx
        /// </summary>
        /// <param name="url"></param>
        protected void ScriptRedirect(string url)
        {
            string strScript = "window.location.href='{0}';";

            if (url.Substring(0, 1).Equals("/")) 
                url = url.Substring(1, url.Length - 1);

            strScript = string.Format(strScript, RootPath + url);

            ScriptManager.RegisterClientScriptBlock(this.Page, this.GetType(), "", strScript, true);
        }

        /// <summary>
        /// Display Date or Datetime to seconds
        /// </summary>
        /// <param name="dateTime">dateTime</param>
        /// <returns>time string</returns>
        protected string DisplayDateTime(DateTime dateTime, bool isToSeconds)
        {
            string time = string.Empty;

            if (dateTime != DateTime.MinValue && dateTime != new DateTime(1900, 1, 1))
            {
                if (isToSeconds)
                {
                    time = dateTime.ToString("yyyy-MM-dd HH:mm:ss");
                }
                else
                {
                    time = dateTime.ToString("yyyy-MM-dd");
                }
            }

            return time;
        }

        protected string DisplayDateTime(DateTime dateTime)
        {
           return DisplayDateTime(dateTime, false);
        }

        protected void RedirectToErrorPage()
        {
            //Response.Redirect("~/ErrorPages/SiteErrorPage.aspx",true);
        }

        protected string GetOledbConnctionString(string dataSource)
        {
            string oledbVer = System.Configuration.ConfigurationManager.AppSettings["OledbVersion"];
            string excelVer = System.Configuration.ConfigurationManager.AppSettings["ExcelVersion"];
            string str12 = "Provider=Microsoft.ACE.OLEDB.{0};Data Source={1};Extended Properties=\"Excel {2} Xml;HDR=Yes;IMEX=1\"";
            string str04 = "Provider=Microsoft.Jet.OLEDB.{0};Data Source={1};Extended Properties=\"Excel {2};HDR=Yes;IMEX=1\"";
            string connectionString = string.Empty;

            if (oledbVer.Equals("4.0"))
            {
                connectionString = string.Format(str04, oledbVer, dataSource, excelVer);
            }
            else if (oledbVer.Equals("12.0"))
            {
                connectionString = string.Format(str12, oledbVer, dataSource, excelVer);
            }

            return connectionString;
        }
        #endregion
    }
}