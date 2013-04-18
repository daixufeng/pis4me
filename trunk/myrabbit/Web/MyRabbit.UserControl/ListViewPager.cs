using System;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Web.UI;
using System.Web.UI.WebControls;

[assembly: WebResource("Aspx.UserControl.images.listview-pager.png", "image/png")]
namespace Aspx.UserControl
{
    public class ListViewPager : WebControl, IPostBackEventHandler, IPostBackDataHandler
    {
        #region Events
        public event GridViewPageEventHandler PageIndexChanged;
        #endregion

        #region IPostBackEventHandler Members

        public void RaisePostBackEvent(string eventArgument)
        {
            switch (eventArgument.ToLower())
            {
                case "prevpage":
                    CurrentPageIndex = CurrentPageIndex - 1;
                    this.OnPageIndexChanged(CurrentPageIndex);
                    break;
                case "nextpage":
                    CurrentPageIndex = CurrentPageIndex + 1;
                    this.OnPageIndexChanged(CurrentPageIndex);
                    break;
                default:
                    break;
            }
        }

        #endregion

        #region IPostBackDataHandler Members

        public bool LoadPostData(string postDataKey, System.Collections.Specialized.NameValueCollection postCollection)
        {
            return false;
        }

        public void RaisePostDataChangedEvent()
        {
            
        }

        #endregion

        #region Fields
        public int PageCount
        {
            get
            {
                if ((this.RecordCount % this.PageSize) == 0)
                {
                    return this.RecordCount / this.PageSize;
                }
                else
                {
                    return (this.RecordCount / this.PageSize) + 1;
                }
            }
        }

        public int PageSize
        {
            get
            {
                return (ViewState[this.ID + "_PageSize"] == null ? 20 : (int)ViewState[this.ID + "_PageSize"]);
            }
            set
            {
                ViewState[this.ID + "_PageSize"] = value;
            }
        }

        public int RecordCount
        {
            get
            {
                if (ViewState[this.ID + "_RecordCount"] != null && ViewState[this.ID + "_RecordCount"] is int)
                    return (int)ViewState["RecordCount"];
                else
                    return 0;
            }
            set
            {
                ViewState[this.ID + "_RecordCount"] = value;
            }
        }

        public int CurrentPageIndex
        {
            get
            {
                return (ViewState[this.ID + "_CurrentPageIndex"] == null ? 0 : (int)ViewState[this.ID + "_CurrentPageIndex"]);
            }
            set
            {
                ViewState[this.ID + "_CurrentPageIndex"] = value;
            }
        }        
        #endregion

        #region Protected Methods 
        protected virtual void OnPageIndexChanged(int newPageIndex)
        {
            if (this.PageIndexChanged != null)
                this.PageIndexChanged(this, new GridViewPageEventArgs(newPageIndex));
        }

        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);
            Page.RegisterRequiresPostBack(this);
        }

        /// <summary>
        /// </summary>
        /// <param name="writer"></param>
        protected override void AddAttributesToRender(HtmlTextWriter writer)
        {
            base.AddAttributesToRender(writer);
        } 
        
        /// <summary>
        /// 
        /// </summary>
        /// <param name="output"></param>
        protected override void Render(HtmlTextWriter output)
        {
            if (PageCount == 0) return;

            output.Write("<div style=\"width:100%; height:26px;\"><table style=\"float:right;\"><tr><td></td>");

            if (this.CurrentPageIndex > 0)
                output.Write("<td style=\"width:30px;\"><a style=\"display:block;height:22px; width:22px; text-decoration:none;background:url(" + GetImageUrl() + ") no-repeat 0px 0px;\" href=\"javascript:" + Page.ClientScript.GetPostBackEventReference(this, "PrevPage") + "\"></a></td>");

            output.Write("<td style=\"font-size:14px; text-align:center;\">" + (CurrentPageIndex + 1).ToString() + "/" + PageCount + "</td>");

            output.Write("<td style=\"width:72px;padding:1px;\"><a style=\"display:block;height:22px; width:70px;");

            if (this.CurrentPageIndex < this.PageCount - 1)
                output.Write("background:url(" + GetImageUrl() + ") no-repeat -40px 0px;text-decoration:none;\" href=\"javascript:" + Page.ClientScript.GetPostBackEventReference(this, "NextPage") + "\"></a></td>");
            else
                output.Write("background:url(" + GetImageUrl() + ") no-repeat -125px 0px;text-decoration:none;\"></a></td>");

            output.Write("</tr></table></div>");
        }
        #endregion

        /// <summary>
        /// button image
        /// </summary>
        public string GetImageUrl()
        {
            string str = Page.ClientScript.GetWebResourceUrl(this.GetType(), "SME.WebControl.images.gridview-pager.png");

            if (str != null)
            {
                return str;
            }
            else
            {
                return String.Empty;
            }
        }
    }
}
