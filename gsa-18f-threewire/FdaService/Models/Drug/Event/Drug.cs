namespace FdaService.Models.Drug.Event
{
    public class Drug
    {
        public string drugtreatmentdurationunit { get; set; }
        public string drugauthorizationnumb { get; set; }
        public string drugtreatmentduration { get; set; }
        public string drugstartdateformat { get; set; }
        public string drugindication { get; set; }
        public string medicinalproduct { get; set; }        
        public string drugdosagetext { get; set; }
        public string drugstartdate { get; set; }
        public string drugenddate { get; set; }
        public string drugenddateformat { get; set; }
        public Openfda openfda { get; set; }
        public string drugcharacterization { get; set; }
        public string drugcharacterizationtext
        {
            get
            {
                switch (int.Parse(this.drugcharacterization))
                {
                    case 1:
                        return "Suspect Drug";
                    case 2:
                        return "Concomitant Drug";
                    case 3:
                        return "Interacting Drug";
                    default:
                        return "Unknown";

                }
            }
        }
        public string drugadministrationroute { get; set; }
        public string drugadministrationroutetext
        {
            get
            {
                switch (this.drugadministrationroute)
                {
                    case "001": return "Auricular(otic)";
                    case "002": return "Buccal";
                    case "003": return "Cutaneous";
                    case "004": return "Dental";
                    case "005": return "Endocervical";
                    case "006": return "Endosinusial";
                    case "007": return "Endotracheal";
                    case "008": return "Epidural";
                    case "009": return "Extra - amniotic";
                    case "010": return "Hemodialysis";
                    case "011": return "Intra corpus cavernosum";
                    case "012": return "Intra - amniotic";
                    case "013": return "Intra - arterial";
                    case "014": return "Intra - articular";
                    case "015": return "Intra - uterine";
                    case "016": return "Intracardiac";
                    case "017": return "Intracavernous";
                    case "018": return "Intracerebral";
                    case "019": return "Intracervical";
                    case "020": return "Intracisternal";
                    case "021": return "Intracorneal";
                    case "022": return "Intracoronary";
                    case "023": return "Intradermal";
                    case "024": return "Intradiscal(intraspinal)";
                    case "025": return "Intrahepatic";
                    case "026": return "Intralesional";
                    case "027": return "Intralymphatic";
                    case "028": return "Intramedullar(bone marrow)";
                    case "029": return "Intrameningeal";
                    case "030": return "Intramuscular";
                    case "031": return "Intraocular";
                    case "032": return "Intrapericardial";
                    case "033": return "Intraperitoneal";
                    case "034": return "Intrapleural";
                    case "035": return "Intrasynovial";
                    case "036": return "Intratumor";
                    case "037": return "Intrathecal";
                    case "038": return "Intrathoracic";
                    case "039": return "Intratracheal";
                    case "040": return "Intravenous bolus";
                    case "041": return "Intravenous drip";
                    case "042": return "Intravenous(not otherwise specified)";
                    case "043": return "Intravesical";
                    case "044": return "Iontophoresis";
                    case "045": return "Nasal";
                    case "046": return "Occlusive dressing technique";
                    case "047": return "Ophthalmic";
                    case "048": return "Oral";
                    case "049": return "Oropharingeal";
                    case "050": return "Other";
                    case "051": return "Parenteral";
                    case "052": return "Periarticular";
                    case "053": return "Perineural";
                    case "054": return "Rectal";
                    case "055": return "Respiratory(inhalation)";
                    case "056": return "Retrobulbar";
                    case "057": return "Sunconjunctival";
                    case "058": return "Subcutaneous";
                    case "059": return "Subdermal";
                    case "060": return "Sublingual";
                    case "061": return "Topical";
                    case "062": return "Transdermal";
                    case "063": return "Transmammary";
                    case "064": return "Transplacental";
                    case "065": return "Unknown";
                    case "066": return "Urethral";
                    case "067": return "Vaginal";
                    default: return "Unknown";
                }
            }
        }
    }
}