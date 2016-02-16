using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNet.Mvc;
using FdaService;
using FdaService.Models.Drug.Event;
using FaNgMvcBs2.ViewModels;

namespace FaNgMvcBs2.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {


            return View();
        }


        public IActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public IActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }

        public IActionResult Error()
        {
            return View("~/Views/Shared/Error.cshtml");
        }
    }
}
