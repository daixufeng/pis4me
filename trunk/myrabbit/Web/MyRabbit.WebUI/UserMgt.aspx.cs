using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.Utility;
using MyRabbit.Entity;

namespace MyRabbit.WebUI
{
    public partial class UserMgt : AuthBasePage
    {
        #region Fields
        private readonly string USERS = "UserMgt_Users";
        private readonly string CURRENTUSER = "UserMgt_CurrentUser";

        private MyRabbit.Entity.User selectedUser = new MyRabbit.Entity.User();
        private PageData<MyRabbit.Entity.User> PageData { get; set; }

        #endregion

        #region Protected Methods
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                //if (!LogonUser.RoleName.Equals("Admin"))
                //{
                //    ShowMessage("你没有权限进行此操作！", "Default.aspx");
                //}

                if (!IsPostBack)
                {
                    BindData(1);

                    //if single login, show this function
                    gvwUser.Columns[4].Visible = SingleLogin;
                    gvwUser.Columns[6].Visible = SingleLogin;
                }
                else
                {
                    selectedUser = Session[CURRENTUSER] as MyRabbit.Entity.User;
                }
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void btnSearch_Click(object sender, EventArgs e)
        {
            try
            {
                BindData(1);
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            try
            {
                selectedUser = string.IsNullOrEmpty(hdfEditStatus.Value) ? new MyRabbit.Entity.User() : selectedUser;

                DESEncrypt desEncrypt = new DESEncrypt();
                selectedUser.NickName = txtNickName.Text;
                selectedUser.LoginName = txtLoginName.Text;
                selectedUser.Email = txtEmail.Text;
                selectedUser.Password = desEncrypt.Encrypt(txtPassword.Text);
                selectedUser.RoleId = Int32.Parse(dplUserRole.SelectedValue);
                if (selectedUser.Id > 0)
                    userService.Update(selectedUser);
                else
                    userService.Create(selectedUser);

                //write operation log
                //string message = string.IsNullOrEmpty(hdfEditStatus.Value) ? "新增用户：" : "变更用户：";
                //message += txtUserName.Text;
                //OperationLogType type = selectedUser.Id.Equals(0) ? OperationLogType.Insert : OperationLogType.Update;
                //WriteOperationLog(type, message);

                BindData(1);

                ShowMessage("保存成功。");
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void btnAdd_Click(object sender, EventArgs e)
        {
            try
            {
                lblEditTitle.Text = "新增用户";

                txtNickName.Text = string.Empty;
                txtLoginName.Text = string.Empty;
                txtPassword.Text = string.Empty;
                txtPassword.Attributes["value"] = string.Empty;
                dplUserRole.SelectedIndex = 0;

                txtNickName.Enabled = true;
                txtLoginName.Enabled = true;

                hdfEditStatus.Value = string.Empty;

                modalUserEdit.Show();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            try
            {
                IList<MyRabbit.Entity.User> lstUser = new List<MyRabbit.Entity.User>();

                IList<MyRabbit.Entity.User> displayUser = Session[USERS] as List<MyRabbit.Entity.User>;
                for (int i = 0; i < gvwUser.Rows.Count; i++)
                {
                    GridViewRow row = gvwUser.Rows[i];
                    if (((CheckBox)row.Cells[0].FindControl("chkBox")).Checked)
                        lstUser.Add(displayUser[i]);
                }

                if (lstUser.Contains(LogonUser))
                {
                    ShowMessage("不能删除自己！");
                    return;
                }

                userService.Delete(lstUser);

                //write operation log
                //string message = "删除用户：";
                //foreach (var o in lstUser)
                //    message += o.NickName + ",";
                //WriteOperationLog(OperationLogType.Delete, message);

                BindData(1);

                ShowMessage("操作成功！");
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void gvwUser_RowEditing(object sender, GridViewEditEventArgs e)
        {
            try
            {
                lblEditTitle.Text = "编辑用户";

                IList<MyRabbit.Entity.User> users = Session[USERS] as List<MyRabbit.Entity.User>;

                selectedUser = users[e.NewEditIndex];
                //if (selectedUser.Id.Equals(LogonUser.Id))
                //{
                //    ShowMessage("不能对自己进行编辑！");
                //    return;
                //}
                Session[CURRENTUSER] = selectedUser;

                txtNickName.Text = selectedUser.NickName;
                txtLoginName.Text = selectedUser.LoginName;
                dplUserRole.SelectedValue = selectedUser.RoleId.ToString();
                DESEncrypt desEncrypt = new DESEncrypt();
                txtPassword.Attributes["value"] = desEncrypt.Decrypt(selectedUser.Password);

                //txtNickName.Enabled = false;
                txtLoginName.Enabled = false;

                hdfEditStatus.Value = "true";
                uplEdit.Update();
                modalUserEdit.Show();
                gvwUser.SelectedIndex = e.NewEditIndex;

                e.Cancel = true;
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void gvwUser_SelectedIndexChanging(object sender, GridViewSelectEventArgs e)
        {
            try
            {
                if (gvwUser.Rows[e.NewSelectedIndex].Cells[4].Text.Equals("离线"))
                {
                    ShowMessage("当前用户无需进行此操作！");
                    return;
                }

                IList<MyRabbit.Entity.User> users = Session[USERS] as List<MyRabbit.Entity.User>;
                MyRabbit.Entity.User user = users[e.NewSelectedIndex];
                if (user.Id.Equals(LogonUser.Id))
                {
                    ShowMessage("当前用户不能进行此操作！");
                    return;
                } 

                Application.Lock();
                IList<MyRabbit.Entity.User> totalLogonUsers = Application[GlobalKeys.TotalLogonUsers] as List<MyRabbit.Entity.User>;

                user.IsLogon = 0;
                userService.Update(user);
                totalLogonUsers.Remove(user);
                Application[GlobalKeys.TotalLogonUsers] = totalLogonUsers;
                Application.UnLock();

                ShowMessage("操作成功！");
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                GoToErrorPage();
            }
        }

        protected void gvwUser_PageIndexChanged(object sender, GridViewPageEventArgs e)
        {
            try
            {
#if DEBUG
                //System.Threading.Thread.Sleep(2000);
#endif
                int start = e.NewPageIndex + 1;
                BindData(start);
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void gvwUser_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            try
            {
                if (e.Row.RowType.Equals(DataControlRowType.DataRow))
                {
                    MyRabbit.Entity.User user = e.Row.DataItem as MyRabbit.Entity.User;
                    //e.Row.Cells[3].Text = user.RoleName;
                    //e.Row.Cells[4].Text = GetUserStatus(user);
                }
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }
        #endregion

        #region Private Methods

        private string GetUserStatus(MyRabbit.Entity.User user)
        {
            if (SingleLogin)
            {
                Application.Lock();
                IList<MyRabbit.Entity.User> users = Application[GlobalKeys.TotalLogonUsers] as List<MyRabbit.Entity.User>;
                Application.UnLock();

                bool online = users.Contains(user);
                if (online && user.IsLogon == 1)
                    return "在线";
                else if (!online && user.IsLogon == 1)
                    return "锁定";
                else
                    return "离线";
            }
            else
            {
                if (user.IsLogon == 1)
                    return "在线";
                else
                    return "离线";
            }
        }

        private void BindData(int pageNo)
        {
            PageData = userService.GetPageData(pageNo, gvwUserPager.PageSize, new Entity.User());
            gvwUser.DataSource = PageData.Entities;
            gvwUser.DataBind();
            gvwUserPager.RecordCount = PageData.Count;
            Session[USERS] = PageData.Entities;
        }
        #endregion
    }
}