using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyWork.Model;
using MyWork.DAL;
using System.Data;

namespace MyWork
{
    public partial class StaffEdit : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                txtStaffId.Value = Request["StaffId"] == null ? "0" : Request["StaffId"];
                if (txtStaffId.Value == "0") { return; }
                MStaff staff = new MStaff() { StaffId = int.Parse(txtStaffId.Value) };
                DStaff dao = new DStaff();
                DataTable dt = dao.GetStaff(staff);
                txtStaffNo.Text = dt.Rows[0]["StaffNo"].ToString();
                txtStaffName.Text = dt.Rows[0]["StaffName"].ToString();
                dplSex.SelectedIndex = dt.Rows[0]["Sex"].ToString() == "女" ? 1 : 0;
                txtBirthDay.Text = ((DateTime)dt.Rows[0]["BirthDay"])
                    .ToString("yyyyMMdd");
                txtAddress.Text = dt.Rows[0]["Address"].ToString();
                txtMobile.Text = dt.Rows[0]["Mobile"].ToString();
                txtTel.Text = dt.Rows[0]["Tel"].ToString();
                txtEmail.Text = dt.Rows[0]["Email"].ToString();
                txtRemark.Text = dt.Rows[0]["Remark"].ToString();
            }
        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            MStaff staff = new MStaff();
            
            staff.StaffId = int.Parse(txtStaffId.Value);
            staff.StaffNo=txtStaffNo.Text;
            staff.StaffName=txtStaffName.Text;
            staff.Sex=dplSex.Text;
            if(txtBirthDay.Text.Length>0){
                staff.BirthDay=  new DateTime(int.Parse(txtBirthDay.Text.Substring(0,4)),
                    int.Parse(txtBirthDay.Text.Substring(4,2)),
                    int.Parse(txtBirthDay.Text.Substring(6,2)));
            }
            staff.Address=txtAddress.Text;
            staff.Mobile=txtMobile.Text;
            staff.Tel=txtTel.Text;
            staff.Email = txtEmail.Text;
            staff.Remark = txtRemark.Text;           

            DStaff dao = new DStaff();
            try
            {
                dao.SaveOrUpdate(staff);
                Utility.ShowInfo(this.Page,"保存成功！");
                
                ScriptManager.RegisterStartupScript(this.Page, typeof(StaffEdit), "", "window.returnValue = true;window.close();", true);
                
            }
            catch (Exception ex) {
                Utility.ShowError(this.Page,ex.Message);
            }
        }
    }
}
