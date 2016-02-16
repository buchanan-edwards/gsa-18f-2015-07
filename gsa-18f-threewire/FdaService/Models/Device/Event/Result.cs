using System.Collections.Generic;

namespace FdaService.Models.Device.Event
{
    public class Result
    {
        public string report_number { get; set; }
        public string manufacturer_contact_state { get; set; }
        public string device_date_of_manufacturer { get; set; }
        public string event_type { get; set; }
        public List<string> source_type { get; set; }
        public string manufacturer_g1_address_1 { get; set; }
        public string manufacturer_contact_pcountry { get; set; }
        public string report_to_manufacturer { get; set; }
        public string previous_use_code { get; set; }
        public string manufacturer_postal_code { get; set; }
        public string manufacturer_city { get; set; }
        public string reporter_occupation_code { get; set; }
        public string manufacturer_contact_extension { get; set; }
        public string manufacturer_state { get; set; }
        public string event_location { get; set; }
        public string date_of_event { get; set; }
        public string manufacturer_g1_country { get; set; }
        public string removal_correction_number { get; set; }
        public string manufacturer_g1_city { get; set; }
        public List<MdrText> mdr_text { get; set; }
        public string distributor_zip_code_ext { get; set; }
        public string date_received { get; set; }
        public string manufacturer_zip_code { get; set; }
        public string manufacturer_contact_address_2 { get; set; }
        public string date_report { get; set; }
        public string manufacturer_contact_address_1 { get; set; }
        public string manufacturer_contact_plocal { get; set; }
        public string manufacturer_name { get; set; }
        public List<string> type_of_report { get; set; }
        public string reprocessed_and_reused_flag { get; set; }
        public string manufacturer_link_flag { get; set; }
        public string manufacturer_contact_zip_code { get; set; }
        public string manufacturer_g1_zip_code_ext { get; set; }
        public string manufacturer_contact_country { get; set; }
        public string distributor_zip_code { get; set; }
        public string report_source_code { get; set; }
        public string distributor_name { get; set; }
        public string date_manufacturer_received { get; set; }
        public string remedial_action { get; set; }
        public string manufacturer_contact_postal_code { get; set; }
        public string manufacturer_contact_city { get; set; }
        public string product_problem_flag { get; set; }
        public string event_key { get; set; }
        public string manufacturer_g1_zip_code { get; set; }
        public string manufacturer_g1_state { get; set; }
        public List<Patient> patient { get; set; }
        public string manufacturer_contact_phone_number { get; set; }
        public string manufacturer_zip_code_ext { get; set; }
        public string manufacturer_contact_pcity { get; set; }
        public string manufacturer_g1_postal_code { get; set; }
        public string manufacturer_contact_area_code { get; set; }
        public string adverse_event_flag { get; set; }
        public string single_use_flag { get; set; }
        public string distributor_city { get; set; }
        public string manufacturer_country { get; set; }
        public string manufacturer_contact_f_name { get; set; }
        public string distributor_state { get; set; }
        public string number_devices_in_event { get; set; }
        public string manufacturer_contact_exchange { get; set; }
        public string distributor_address_2 { get; set; }
        public string manufacturer_contact_t_name { get; set; }
        public string distributor_address_1 { get; set; }
        public string manufacturer_g1_address_2 { get; set; }
        public string health_professional { get; set; }
        public string initial_report_to_fda { get; set; }
        public string number_patients_in_event { get; set; }
        public string mdr_report_key { get; set; }
        public string report_to_fda { get; set; }
        public string manufacturer_address_2 { get; set; }
        public string manufacturer_address_1 { get; set; }
        public string manufacturer_g1_name { get; set; }
        public string manufacturer_contact_zip_ext { get; set; }
        public string manufacturer_contact_l_name { get; set; }
        public List<Device> device { get; set; }

        public string term { get; set; }
        public string time { get; set; }
        public int count { get; set; }
    }
}