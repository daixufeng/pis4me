using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.WebUI;

namespace SINOMA.WebUI.Admin
{
    public partial class LogMgt : AuthBasePage
    {
        private string OPERATIONLOGS = "LogMgt_OperationLogs";

        private IList<OperationLog> operationlogs = new List<OperationLog>();

        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                if (!LogonUser.Role.Equals(UserRole.Admin))
                {
                    ShowMessage("你没有权限进行此操作！", "Default.aspx");
                }

                if (!IsPostBack)
                {
                    txtBegDate.Text = DisplayDateTime(DateTime.Now, false);
                    txtEndDate.Text = DisplayDateTime(DateTime.Now, false);

                    Seach();
                }
            }
            catch (Exception ex)
            {
                if (!ex.GetType().Equals(typeof(System.Threading.ThreadAbortException)))
                    Log.Error(ex);
            }
        }

        protected void btnSearch_Click(object sender, EventArgs e)
        {
            try
            {
                Seach();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                //RedirectToErrorPage();
            }
        }

        protected void gvwLog_PageIndexChanged(object sender, GridViewPageEventArgs e)
        {
            try
            {
                int start = e.NewPageIndex * gvwLogPager.PageSize;
                operationlogs = Session[OPERATIONLOGS] as List<OperationLog>;

                gvwLog.DataSource = GetDisplay(start, operationlogs);
                gvwLog.DataBind();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void gvwLog_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            try
            {
                if (e.Row.RowType.Equals(DataControlRowType.DataRow))
                {
                    OperationLog log = e.Row.DataItem as OperationLog;
                    e.Row.Cells[0].Text = EnumHelper.EnumToString<OperationLogType>(log.OperationType);
                    e.Row.Cells[2].Text = log.Operator.Name;
                    e.Row.Cells[3].Text = DisplayDateTime(log.CreateTime);
                }
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        private void Seach()
        {
            OperationLogType type = (OperationLogType)Enum.Parse(typeof(OperationLogType), dplLogType.SelectedValue, false);
            DateTime begDate = new DateTime();
            DateTime endDate = new DateTime();
            begDate = DateTime.TryParse(txtBegDate.Text, out begDate) ? begDate : new DateTime(1900, 1, 1);
            endDate = DateTime.TryParse(txtEndDate.Text, out endDate) ? endDate.AddDays(1) : new DateTime(9999, 12, 31);

            begDate = new DateTime(begDate.Year, begDate.Month, begDate.Day, 0, 0, 0, DateTimeKind.Utc);
            endDate = new DateTime(endDate.Year, endDate.Month, endDate.Day, 0, 0, 0, DateTimeKind.Utc);

            operationlogs = operationLogManager.GetByCondition(0, type, begDate, endDate);
            Session[OPERATIONLOGS] = operationlogs;
            gvwLog.DataSource = GetDisplay(0, operationlogs);
            gvwLog.DataBind();
        }

        private IList<OperationLog> GetDisplay(int start, IList<OperationLog> logs)
        {
            IList<OperationLog> displayList = new List<OperationLog>();

            for (int i = 0; i< gvwLogPager.PageSize && i < logs.Count ; i++)
            {
                displayList.Add(logs[i + start]);
            }
            gvwLogPager.CurrentPageIndex = start == 0 ? 0 : gvwLogPager.CurrentPageIndex;
            gvwLogPager.RecordCount = logs.Count;
            return displayList;
        }
    }
}