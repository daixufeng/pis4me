using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;

namespace MyWork
{
    public class Utility
    {
        public static void ShowError(Control control, string Message) {
            ScriptManager.RegisterStartupScript(control, typeof(Control),
                    "错误", "alert('"+ Message +"');", true);
        }

        public static void ShowInfo(Control control, string Message) {
            ScriptManager.RegisterStartupScript(control, typeof(Control),
                    "提示信息", "alert('"+ Message +"');", true);
        }

    }
}
