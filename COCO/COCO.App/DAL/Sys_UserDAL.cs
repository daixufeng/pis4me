using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using COCO.App.Entity;
using NHibernate;

namespace COCO.App.DAL
{
    public class Sys_UserDAL:AbstractDAL<Sys_User>
    {

        public void ErrorTest()
        {
            try
            {
                throw new Exception("test message");
            }
            catch (Exception ex)
            {
                log.Error(ex);
            }
        }
    }
}
