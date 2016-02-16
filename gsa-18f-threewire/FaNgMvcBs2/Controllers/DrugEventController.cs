using System.Linq;
using Microsoft.AspNet.Mvc;
using FdaService;
using FdaService.Models.Drug.Event;
using FaNgMvcBs2.ViewModels;
using System.Collections.Generic;


// For more information on enabling MVC for empty projects, visit http://go.microsoft.com/fwlink/?LinkID=397860

namespace FaNgMvcBs2.Controllers
{
    public class DrugEventController : Controller
    {
        public IActionResult Index()
        {
            var drugEventViewModel = new DrugEventViewModel();
            drugEventViewModel.SearchCriteria = SearchLookups.GetResultFields().ToList();

            return View(drugEventViewModel);
        }
        [HttpPost]
        public IActionResult Index(DrugEventViewModel model)
        {
            var drugEventViewModel = new DrugEventViewModel();
            drugEventViewModel.SearchCriteria = SearchLookups.GetResultFields().ToList();

            drugEventViewModel.RootObject = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/event.json?",
                string.Format("search={0}:\"{1}\"&limit=50", model.SearchCriteriaSelected, model.SearchInfo)); //"search=patient.drug.openfda.pharm_class_epc:\"nonsteroidal+anti-inflammatory+drug\"");
                                                                                                               //"search=patient.drug.openfda.brand_name:\"allegra\"&limit=50");

            //drugEventViewModel.RootObject.results = drugEventViewModel.RootObject.results.Take<Result>(5).ToList();
            return View(drugEventViewModel);
        }

        public IActionResult Search()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Search(DrugEventViewModel model)
        {
            if (!string.IsNullOrWhiteSpace(model.SearchInfo))
            {
                return Display(model);
            }
            else
            {
                return Search();
            }
        }

        public IActionResult Display(DrugEventViewModel model)
        {
            model.RootObject = ServiceHelper.GetData<RootObject>("https://api.fda.gov",
                "/drug/event.json?",
                string.Format("search={0}:\"{1}\"&limit=50", "patient.drug.openfda.brand_name", model.SearchInfo)); //"search=patient.drug.openfda.pharm_class_epc:\"nonsteroidal+anti-inflammatory+drug\"");
            model.Drugs = new List<FdaService.Models.Drug.Event.Drug>();
            model.Reactions = new List<Reaction>();

            if (model.RootObject != null && model.RootObject.results != null)
            {
                foreach (var result in model.RootObject.results)
                {
                    foreach (var drug in result.patient.drug)
                    {
                        if (!model.Drugs.Any(d => d.medicinalproduct == drug.medicinalproduct) && model.Drugs.Count < 100)
                        {
                            model.Drugs.Add(drug);
                        }
                    }
                    foreach (var reaction in result.patient.reaction)
                    {
                        if (!model.Reactions.Any(r => r.reactionmeddrapt == reaction.reactionmeddrapt) && model.Reactions.Count < 100)
                        {
                            model.Reactions.Add(reaction);
                        }
                    }
                }

                model.Drugs = model.Drugs.OrderBy(a => a.medicinalproduct).ToList();
                model.Reactions = model.Reactions.OrderBy(r => r.reactionmeddrapt).ToList();
            }
            return View("Display", model);
        }
    }
}
