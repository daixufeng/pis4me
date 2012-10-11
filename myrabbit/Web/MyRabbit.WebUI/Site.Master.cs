using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using MyRabbit.Entity;
using MyRabbit.Service;

namespace MyRabbit.WebUI
{
    public partial class SiteMaster : System.Web.UI.MasterPage
    {
        #region Fileds
        protected UserService userService = new UserService();
        
        protected User LogonUser
        {
            get { return Session[GlobalKeys.LogonUser] as User; }
            set { Session[GlobalKeys.LogonUser] = value; }
        }


        private log4net.ILog _log = null;
        protected log4net.ILog Log
        {
            get
            {
                if (_log == null) _log = log4net.LogManager.GetLogger(this.GetType());
                
                return _log;
            }
        }
        #endregion

        #region Protected Methods
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                this.Page.Header.DataBind();
               
            }
            catch (Exception ex)
            {
                if (!ex.GetType().Equals(typeof(System.Threading.ThreadAbortException)))
                    Log.Error(ex);
                Log.Error(ex);
            }
        }


        protected void ShowMessage(string strMessage)
        {
            ScriptManager.RegisterClientScriptBlock(this.Page, typeof(SiteMaster), "", "alert('" + strMessage + "');", true);
        }
        #endregion
    }
}
