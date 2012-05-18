using System.Collections.Generic;
using iBatis.Model.Sys;

namespace iBatis.DAL.DSys
{
    public class DUser : DALSupport
    {
        public IList<MUser> GetAll()
        {
            return Session.QueryForList<MUser>("User_Select", null);
        }
        public void Save(MUser user)
        {
            Session.Insert("User_Insert", user);
        }

        public void Update(MUser user)
        {
            Session.Update("User_Update", user);
        }

        public void Delete(MUser user)
        {
            if (Session != null)
            {
                Session.Update("User_Delete", user);
            }
        }
    }
}
