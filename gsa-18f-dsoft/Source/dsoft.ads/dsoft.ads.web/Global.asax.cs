
﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;
using System.Web.Optimization;

namespace dsoft.ads.web
{
	public class MvcApplication : System.Web.HttpApplication
	{
		protected void Application_Start ()
		{
			AreaRegistration.RegisterAllAreas ();
			RegisterGlobalFilters (GlobalFilters.Filters);
			RegisterRoutes (RouteTable.Routes);
			RegisterBundles (BundleTable.Bundles);
		}

		public static void RegisterRoutes (RouteCollection routes)
		{
			routes.IgnoreRoute ("{resource}.axd/{*pathInfo}");

			routes.MapRoute (
				"Default",
				"{controller}/{action}/{id}",
				new { controller = "Home", action = "Index", id = "" }
			);

		}

		public static void RegisterGlobalFilters (GlobalFilterCollection filters)
		{
			filters.Add (new HandleErrorAttribute ());
		}

		public static void RegisterBundles (BundleCollection bundles)
		{
			// javascript
			bundles.Add (new ScriptBundle ("~/scriptbundles/bootstrap").Include (
				"~/Scripts/jquery/jquery-2.1.4.min.js",
				"~/Scripts/bootstrap/*.min.js"));
			bundles.Add (new ScriptBundle ("~/scriptbundles/site").Include ("~/Scripts/site/*.js"));

			// css
			bundles.Add (new StyleBundle("~/stylebundles/bootstrap").Include("~/Content/bootstrap/css/*.min.css"));
			bundles.Add (new StyleBundle ("~/stylebundles/site").Include ("~/Content/site/css/*.css"));

			#if Release // Enable bundling / minification for release versions only
			BundleTable.EnableOptimizations = true;
			#endif
		}
	}
}
