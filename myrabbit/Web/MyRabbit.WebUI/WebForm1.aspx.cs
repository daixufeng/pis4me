using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
using System.Net;

namespace SINOMA.WebUI
{
    public partial class WebForm1 : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string url = "http://teams9.sharepoint.hp.com/teams/APJnetworkplanner/Shared%20Documents/ETOT3%20BRD/ETOT3%20Data%20Source/Sharepoint%20data%20source/Sharepoint%20Data/LT%20CHUB-APDC.xls";
            string path = HttpUtility.UrlPathEncode(url);
            string ss = HttpUtility.HtmlDecode(url);
            Response.Write(path + ss);
            MemoryStream stream = Download(url, "$etot2001", "!qa2ws3ed4rf");
            byte[] buffer = stream.ToArray();
            System.IO.File.WriteAllBytes(@"c:\\tbl.xls", buffer);
            stream.Close();
        }
        public static MemoryStream Download(string url, string userName, string password)
        {
            WebRequest requeset = HttpWebRequest.Create(url);

            //GlobalParameter gp = ExcelDataAccessor.GetSharePointCredentials();
            //string username = gp.Value.Trim();
            //string password = gp.Ref1.Trim();
            requeset.Credentials = new System.Net.NetworkCredential(userName, password);
            WebResponse response = requeset.GetResponse();
            MemoryStream memory = new MemoryStream((int)response.ContentLength);

            Stream stream = response.GetResponseStream();
            byte[] buffer = new byte[8096];
            while (true)
            {
                int count = stream.Read(buffer, 0, buffer.Length);
                if (count > 0)
                {
                    memory.Write(buffer, 0, count);
                }
                else
                {
                    break;
                }
            }
            return memory;
        }
    }
}