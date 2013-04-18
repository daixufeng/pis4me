using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace MyRabbit.WebUI
{
    public partial class BugList : AuthBasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            gvwBug.DataSource = null;
            gvwBug.DataBind();
        }

        protected void gvwBug_RowEditing(object sender, GridViewEditEventArgs e)
        {
            try
            {
            }
            catch (Exception ex)
            {
                SysLog.Error(ex);
            }
        }

        protected void gvwBug_SelectedIndexChanging(object sender, GridViewSelectEventArgs e)
        {
            try
            {
            }
            catch (Exception ex)
            {
                SysLog.Error(ex);
            }
        }

        protected void gvwBug_PageIndexChanged(object sender, GridViewPageEventArgs e)
        {
            try
            {
            }
            catch (Exception ex)
            {
                SysLog.Error(ex);
            }
        }

        protected void gvwBug_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            try
            {
                if (e.Row.RowType.Equals(DataControlRowType.DataRow))
                {
                }
            }
            catch (Exception ex)
            {
                SysLog.Error(ex);
            }
        }
    }
}