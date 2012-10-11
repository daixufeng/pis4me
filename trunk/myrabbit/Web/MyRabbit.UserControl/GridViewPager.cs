using System;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace MyRabbit.UserControl
{
    public class GridViewPager : WebControl, IPostBackEventHandler, IPostBackDataHandler
    {
        #region Events
        public event GridViewPageEventHandler PageIndexChanged;
        #endregion

        #region IPostBackEventHandler Members

        public void RaisePostBackEvent(string eventArgument)
        {
            switch (eventArgument.ToLower())
            {
                case "firstpage":
                    CurrentPageIndex = 0;
                    break;
                case "prevpage":
                    CurrentPageIndex = CurrentPageIndex - 1;
                    break;
                case "nextpage":
                    CurrentPageIndex = CurrentPageIndex + 1;
                    break;
                case "lastpage":
                    CurrentPageIndex = PageCount - 1;
                    break;
                default:
                    CurrentPageIndex = int.Parse(this.Text) - 1;
                    CurrentPageIndex = CurrentPageIndex < 0 ? 0 : CurrentPageIndex;
                    CurrentPageIndex = CurrentPageIndex > PageCount - 1 ? PageCount - 1 : CurrentPageIndex;
                    break;
            }
            Text = (CurrentPageIndex + 1).ToString();
            this.OnPageIndexChanged(CurrentPageIndex);
        }

        #endregion

        #region IPostBackDataHandler Members

        public bool LoadPostData(string postDataKey, System.Collections.Specialized.NameValueCollection postCollection)
        {
            this.Text = postCollection[postDataKey];
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
                return (ViewState["PageSize"] == null ? 20 : (int)ViewState["PageSize"]);
            }
            set
            {
                ViewState["PageSize"] = value;
            }
        }

        public int RecordCount
        {
            get
            {
                if (ViewState["RecordCount"] != null && ViewState["RecordCount"] is int)
                    return (int)ViewState["RecordCount"];
                else
                    return 0;
            }
            set
            {
                ViewState["RecordCount"] = value;
            }
        }

        public int CurrentPageIndex
        {
            get
            {
                return (ViewState["CurrentPageIndex"] == null ? 0 : (int)ViewState["CurrentPageIndex"]);
            }
            set
            {
                ViewState["CurrentPageIndex"] = value;
            }
        }

        /// <summary>
        /// button image
        /// </summary>
        [EditorAttribute(typeof(System.Web.UI.Design.ImageUrlEditor), typeof(System.Drawing.Design.UITypeEditor))]
        public string IconImageUrl
        {
            get
            {
                string str = (String)ViewState["IconImageUrl"];
                if (str != null)
                {
                    return str;
                }
                else
                {
                    return String.Empty;
                }
            }
            set
            {
                ViewState["IconImageUrl"] = value;
            }
        }

        /// <summary>
        /// Text attribute 
        /// </summary>
        public string Text
        {
            get
            {
                if (this.ViewState["AppointPage"] != null && !this.ViewState["AppointPage"].ToString().Equals(""))
                {
                    return this.ViewState["AppointPage"].ToString();
                }
                else
                {
                    return "1";
                }
            }
            set
            {
                this.ViewState["AppointPage"] = value;
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
        } /// <summary>

        protected override void Render(HtmlTextWriter output)
        {

            if (PageCount == 0) return;

            output.Write("<div style=\"width:100%; height:26px;\"><table style=\"float:right;\"><tr><td></td>");

            if (this.CurrentPageIndex > 0)
            {
                output.Write("<td style=\"padding:0px;\"><a id=\"" + this.ID + "_FirstPage\" title=\"第一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(" + IconImageUrl + ") no-repeat 1px -2px;\" href=\"javascript:" + Page.ClientScript.GetPostBackEventReference(this, "FirstPage") + "\"></a></td>");
                output.Write("<td style=\"padding:0px;\"><a id=\"" + this.ID + "_PrevPage\" title=\"前一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(" + IconImageUrl + ") no-repeat -96px -2px;\" href=\"javascript:" + Page.ClientScript.GetPostBackEventReference(this, "PrevPage") + "\"></a></td>");
            }
            else
            {
                output.Write("<td style=\"padding:0px;\"><a id=\"" + this.ID + "_FirstPage\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(" + IconImageUrl + ") no-repeat -16px -2px;\" href=\"javascript:return false;\"></a></td>");
                output.Write("<td style=\"padding:0px;\"><a id=\"" + this.ID + "_PrevPage\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(" + IconImageUrl + ") no-repeat -111px -2px;\" href=\"javascript:return false;\"></a></td>");
            }

            output.Write("<td style=\"text-align:center; padding:0px 10px 0px 10px;\"><input type=\"text\" name=\"" + this.UniqueID + "\" style=\"width:20px;\" value=\"" + (CurrentPageIndex + 1).ToString() + "\"/>&nbsp;&nbsp;/&nbsp;&nbsp;" + PageCount + "页</td>");

            if (this.CurrentPageIndex < this.PageCount - 1)
            {
                output.Write("<td style=\"padding:0px;\"><a id=\"" + this.ID + "_NextPage\" title=\"下一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(" + IconImageUrl + ") no-repeat -65px -2px;\" href=\"javascript:" + Page.ClientScript.GetPostBackEventReference(this, "NextPage") + "\"></a></td>");
                output.Write("<td style=\"padding:0px;\"><a id=\"" + this.ID + "_LastPage\" title=\"最后页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(" + IconImageUrl + ") no-repeat -33px -2px;\" href=\"javascript:" + Page.ClientScript.GetPostBackEventReference(this, "LastPage") + "\"></a></td>");
            }
            else
            {
                output.Write("<td style=\"padding:0px;\"><a id=\"" + this.ID + "_NextPage\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(" + IconImageUrl + ") no-repeat -80px -2px;\" href=\"#\"></a></td>");
                output.Write("<td style=\"padding:0px;\"><a id=\"" + this.ID + "_LastPage\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(" + IconImageUrl + ") no-repeat -50px -2px;\" href=\"#\"></a></td>");
            }

            output.Write("<td style=\"padding:0px 0px 0px 10px;\"><a id=\"" + this.ID + "_Refresh\" style=\"display:block;height:22px; width:22px; text-decoration:none;background:url(" + IconImageUrl + ") no-repeat -136px 2px;\" href=\"javascript:" + Page.ClientScript.GetPostBackEventReference(this, "Refresh") + "\"></a></td>");

            output.Write("</tr></table></div>");
        }
        #endregion
    }
}
