using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using iBatis.DAL.DSys;
using iBatis.Factory;
using iBatis.Model.Sys;
using log4net;

namespace Model.DAL.Sys
{
    [TestFixture]
    class DUserTest : iBatis.Test.DAL.TestSupport
    {
        [Test]
        public void GetAll()
        {
            DUser dao = DALFactory.GetObject<DUser>("DSys.DUser");
            IList<MUser> list = dao.GetAll();
        }

        public void Save()
        {
            DUser dao = DALFactory.GetObject<DUser>("DSys.DUser");
            MUser user = new MUser()
            {
                UserNo = "2003131302",
                UserName = "Allen",
                Password = "9",
                Address = "HeiFei",
                Tel = "02398947857"
            };
            dao.Save(user);
            Log.Debug(user);
        }

        public void Update()
        {
            DUser dao = DALFactory.GetObject<DUser>("DSys.DUser");
            MUser user = new MUser()
            {
                UserId = 2,
                UserNo = "2003131302",
                UserName = "Allen",
                Password = "9",
                Address = "BeiJing",
                Tel = "02398947857"
            };
            dao.Update(user);
            Log.Debug(user);
        }

        public void Delete()
        {
            MUser user = new MUser() { UserId = 2 };
            DUser dao = DALFactory.GetObject<DUser>("DSys.DUser");
            dao.Delete(user);
        }
    }
}
