using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using iBatis.DAL.DSys;
using iBatis.Factory;
using iBatis.Model.Sys;

namespace WebUI
{
    public partial class Demo : System.Web.UI.Page
    {
        IList<MUser> list;
        protected void Page_Load(object sender, EventArgs e)
        {
            DUser dao = DALFactory.GetObject<DUser>("DSys.DUser");
            list = dao.GetAll();
            grid.DataSource = list;
        }
    }
}