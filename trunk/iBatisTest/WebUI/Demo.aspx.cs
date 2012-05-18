using System;
using System.Web.UI;
using iBatis.DAL.DSys;
using iBatis.Factory;

namespace WebUI
{
    public partial class Demo : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            var dao = DALFactory.GetObject<DUser>("DSys.DUser");
            var list = dao.GetAll();
            grid.DataSource = list;
        }
    }
}