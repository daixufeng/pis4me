using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Practices.Unity;
using System.Web;

namespace MyRabbit.WebUI
{
    /// <summary>
    /// HttpApplication Extenstion
    /// </summary>
    public static class HttpApplicationStateExtensions
    {
        private const string GlobalContainerKey = "GlobalUnityContainerKey";

        public static IUnityContainer GetContainer(this HttpApplicationState application)
        {
            IUnityContainer container = application[GlobalContainerKey] as IUnityContainer;
            if (container == null)
            {
                application.Lock();
                try
                {
                    container = application[GlobalContainerKey] as IUnityContainer;
                    if (container == null)
                    {
                        container = new UnityContainer();
                        application[GlobalContainerKey] = container;
                    }
                }
                finally
                {
                    application.UnLock();
                }
            }
            return container;
        }
    }
}
