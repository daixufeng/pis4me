using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using COCO.App.Entity;
using COCO.Utility;
using COCO.App.DAL;

namespace COCO.AppTest.DAL
{
    [TestFixture]
    public class Sys_UserDALTest:TestSupport
    {
        [Test]
        public void Save()
        {
            DESEncrypt desEncrypt = new DESEncrypt();
            Sys_UserDAL dal = new Sys_UserDAL();
            Sys_User user = new Sys_User()
            {
                Password = desEncrypt.Encrypt("111111"),
                Role = "employee",
                UserName = "Employee",
                IdentityCardNo = "500381198505228019",
                LoginName = "Employee",
                Mobile = "13627617899"
            };

            dal.Save(user);
            Log.Debug(user);
        }

        [Test]
        public void Update()
        {
            Sys_User user = new Sys_User();
            Sys_UserDAL dal = new Sys_UserDAL();
            user = dal.GetById(1);
            user.IdentityCardNo = "500381198505228018";
            user.LoginName = "Admin";
            user.Mobile = "13627617898";
            dal.SaveOrUpdate(user);
        }

        //[Test]
        public void LogTest()
        {
            Sys_UserDAL dal = new Sys_UserDAL();
            dal.ErrorTest();
            Log.Debug("Test Debug");
        }        
    }
}
