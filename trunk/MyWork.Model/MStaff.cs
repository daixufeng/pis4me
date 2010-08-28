using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MyWork.Model
{
    public class MStaff
    {
        /// <summary>
        /// 人员ID
        /// </summary>
        public int StaffId{get;set;}
        /// <summary>
        /// 工号
        /// </summary>
        public string StaffNo { get; set; }
        /// <summary>
        /// 姓名
        /// </summary>
        public string StaffName { get; set; }
        /// <summary>
        /// 性别
        /// </summary>
        public string Sex
        {
            get;
            set;
        }
        /// <summary>
        /// 出生日期
        /// </summary>
        public Nullable<DateTime> BirthDay { get; set; }
        /// <summary>
        /// 家庭地址
        /// </summary>
        public string Address { get; set; }
        /// <summary>
        /// 移动电话
        /// </summary>
        public string Mobile { get; set; }
        /// <summary>
        /// 办公电话
        /// </summary>
        public string Tel { get; set; }
        /// <summary>
        /// 电子邮件
        /// </summary>
        public string Email { get; set; }
        /// <summary>
        /// 备注
        /// </summary>
        public string Remark { get; set; }
    }
}
