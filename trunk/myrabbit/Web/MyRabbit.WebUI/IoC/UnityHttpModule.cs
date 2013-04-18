using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Collections;
using System.Web.UI;
using Microsoft.Practices.Unity;

namespace MyRabbit.WebUI.IoC
{
    public class UnityHttpModule:IHttpModule
    {
        public void Dispose()
        {
            //throw new NotImplementedException();
        }

        public void Init(HttpApplication context)
        {
            context.PreRequestHandlerExecute += OnPreRequestHandlerExecute;
        }

        private static IEnumerable GetControlTree(Control root)
        {
            foreach (Control child in root.Controls)
            {
                yield return child;
                foreach (Control c in GetControlTree(child))
                {
                    yield return c;
                }
            }
        }

        private static void OnPreRequestHandlerExecute(object sender, EventArgs e)
        {
            IHttpHandler handler = HttpContext.Current.Handler;
            HttpContext.Current.Application.GetContainer().BuildUp(handler.GetType(), handler);

            // User Controls are ready to be built up after the page initialization is complete
            Page page = HttpContext.Current.Handler as Page;
            if (page != null)
            {
                page.InitComplete += OnPageInitComplete;
            }
        }

        // Build up each control in the page's control tree
        private static void OnPageInitComplete(object sender, EventArgs e)
        {
            Page page = (Page)sender;
            IUnityContainer container = HttpContext.Current.Application.GetContainer();
            
            foreach (Control c in GetControlTree(page))
            {
                container.BuildUp(c.GetType(), c);
            }
        }
    }
}