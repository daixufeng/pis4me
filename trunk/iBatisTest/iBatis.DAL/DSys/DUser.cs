using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using iBatis.Model.Sys;
using IBatisNet.DataMapper;

namespace iBatis.DAL.DSys
{
    public class DUser:DALSupport
    {
        public IList<MUser> GetAll() {
            return Session.QueryForList<MUser>("User_Select", null);
        }
        public void Save(MUser user)
        {
            Session.Insert("User_Insert", user);
        }

        public void Update(MUser user)
        {
            Session.Update("User_Update",user);
        }

        public void Delete(MUser user)
        {
            Session.Update("User_Delete", user);
        }
    }
}
