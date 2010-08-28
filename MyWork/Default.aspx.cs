using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyWork.DAL;
using MyWork.Model;
using System.Data;

namespace MyWork
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack) {
                loadData(new MStaff());
            }
        }

        private void loadData(MStaff staff)
        {
            DStaff dao = new DStaff();
            grid.DataSource = dao.GetStaff(staff);
            grid.DataBind();        
        }

        protected void btnQuery_Click(object sender, EventArgs e)
        {
            MStaff staff = new MStaff()
            {                
                StaffNo=txtStaffNo.Text,
                StaffName=txtStaffName.Text,
                Sex=dplSex.Text
            };
            loadData(staff);
        }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            IList<MStaff> staffs = new List<MStaff>();
            DStaff dao = new DStaff();
            try
            {
                foreach (GridViewRow row in grid.Rows)
                {
                    CheckBox cb = (CheckBox)row.FindControl("CheckBox1");
                    if (cb.Checked)
                    {
                        MStaff staff = new MStaff() { StaffId = int.Parse(row.Cells[1].Text) };
                        staffs.Add(staff);
                    }
                }
                if (staffs.Count == 0) {
                    Utility.ShowInfo(this.Page, " 请选择要删除的数据！");
                    return;
                }

                dao.Delete(staffs);
                loadData(new MStaff());
                Utility.ShowInfo(this.Page, "操作成功！");
            }
            catch (Exception ex) {
                Utility.ShowError(this.Page, ex.Message);
            }
        }
    }
}
