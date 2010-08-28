/**************************************************************************
模块编号： MyWork.DAL.DStaff
包含：
作者：代绪丰
审核：
编写日期：2010-08-27
描述： 人员信息
..........................................
如果模块有修改，则每次修改添加以下注释：
Log编号：
修改作者：
修改日期：
修改描述：
**************************************************************************/
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using MyWork.Model;
using System.Data.SqlClient;

namespace MyWork.DAL
{
    public class DStaff
    {
        string strQuery = "SELECT [StaffId] ,[StaffNo] ,[StaffName] ,[Sex] ,[BirthDay] ,[Address]"+
                        " ,[Mobile] ,[Tel] ,[Email] ,[Remark] FROM [MyWork].[dbo].[T_Staff]";

        /// <summary>
        /// 人员查询
        /// </summary>
        /// <param name="staff"></param>
        /// <returns></returns>
        public DataTable GetStaff(MStaff staff) {
            string strFilter = "";
            IList<SqlParameter> objParams = new List<SqlParameter>();
            if (staff.StaffId != 0) {
                strFilter += " where StaffId = @StaffId ";
                objParams.Add(new SqlParameter("@StaffId", staff.StaffId));
            }
            if(staff.StaffNo!=null && staff.StaffNo!=""){
                strFilter += strFilter.Length > 0 ? " and " : " where ";
                strFilter+=" StaffNo like @StaffNo ";
                objParams.Add(new SqlParameter("@StaffNo","%"+staff.StaffNo+"%"));
            }
            if (staff.StaffName != null && staff.StaffName != "")
            {
                strFilter += strFilter.Length > 0 ? " and " : " where ";
                strFilter+= " StaffName like @StaffName ";
                objParams.Add(new SqlParameter("@StaffName", "%" + staff.StaffName + "%"));
            }
            if (staff.Sex != null && staff.Sex != "")
            {
                strFilter += strFilter.Length > 0 ? " and " : " where ";
                strFilter+=" Sex = @Sex ";
                objParams.Add(new SqlParameter("@Sex", staff.Sex));
            }

            DataTable dt = new DataTable();
            SqlDataAdapter sda = new SqlDataAdapter();
            SqlConnection conn = Connection.GetConnection();
            SqlCommand mycmd = conn.CreateCommand();
            mycmd.CommandText = strQuery + strFilter;
            sda.SelectCommand = mycmd;
            foreach (var o in objParams) {
                mycmd.Parameters.Add(o);
            }
            sda.Fill(dt);
            conn.Close();
            return dt;
        }

        /// <summary>
        /// 保存人员对象集合
        /// </summary>
        /// <param name="staffs">人员对象集合</param>
        /// <returns></returns>
        public bool SaveOrUpdate(ICollection<MStaff> staffs)
        {
            SqlConnection conn = Connection.GetConnection();
            SqlTransaction trn = conn.BeginTransaction();
            SqlCommand mycmd = conn.CreateCommand();
            mycmd.Transaction = trn;
            try
            {
                foreach (var staff in staffs)
                {
                    string strSql = staff.StaffId == 0 ? " insert into T_Staff ([StaffNo] ,[StaffName]" +
                        " ,[Sex] ,[BirthDay] ,[Address],[Mobile] ,[Tel] ,[Email] ,[Remark]) values " +
                        " (@StaffNo,@StaffName,@Sex,@BirthDay,@Address,@Mobile,@Tel,@Email,@Remark)" :
                        " update T_Staff set [StaffNo] = @StaffNo ,[StaffName] = @StaffName,[Sex] = @Sex " +
                        ",[BirthDay] = @BirthDay ,[Address] = @Address,[Mobile] = @Mobile ,[Tel] =@Tel" +
                        ",[Email] =@Email ,[Remark] =@Remark where StaffId = @StaffId";
                    mycmd.CommandText = strSql;

                    foreach (var property in staff.GetType().GetProperties())
                    {
                        SqlParameter param = new SqlParameter("@"+property.Name, property.GetValue(staff, null));
                        mycmd.Parameters.Add(param);
                    }
                    mycmd.ExecuteNonQuery();
                }
                trn.Commit();
                return true;
            }
            catch
            {
                trn.Rollback();
                throw;
            }
            finally {
                conn.Close();
            }

        }
        /// <summary>
        /// 保存单个人员信息
        /// </summary>
        /// <param name="staff">人员对象</param>
        /// <returns>操作结果</returns>
        public bool SaveOrUpdate(MStaff staff) {
            IList<MStaff> staffs = new List<MStaff>();
            staffs.Add(staff);
            return SaveOrUpdate(staffs);
        }

        /// <summary>
        /// 删除对象
        /// </summary>
        /// <param name="staffs"></param>
        /// <returns></returns>
        public bool Delete(ICollection<MStaff> staffs) {
            SqlConnection conn = Connection.GetConnection();
            SqlTransaction trn = conn.BeginTransaction();
            SqlCommand mycmd = conn.CreateCommand();
            mycmd.Transaction = trn;
            try
            {
                foreach (var staff in staffs)
                {
                    string strSql = "delete from T_Staff where StaffId = @StaffId";
                    mycmd.CommandText = strSql;

                    SqlParameter param = new SqlParameter("@StaffId", staff.StaffId);
                    mycmd.Parameters.Add(param);
                    mycmd.ExecuteNonQuery();
                }
                trn.Commit();
                return true;
            }
            catch
            {
                trn.Rollback();
                throw;
            }
            finally
            {
                conn.Close();
            }
        }

        public bool Delete(MStaff staff) {
            IList<MStaff> staffs = new List<MStaff>();
            staffs.Add(staff);
            return Delete(staffs);
        }
    }
}
