require 'rails_helper'

describe 'Displaying a drug' do
  let(:event_response) { File.read(File.expand_path('../../fixtures/event_response.json', __FILE__)) }
  let(:raw_response) { File.read(File.expand_path('../../fixtures/adderall_single_response.json', __FILE__)) }
  let(:drug_id) { '2787509f-8b55-4f80-a055-970b8e2ece6f' }
  let(:drug_params) { { id: drug_id } }

  before do
    stub_request(:get, /api.fda.gov\/drug\/event.json/).
      with(:headers => {'Accept'=>'*/*', 'Accept-Encoding'=>'gzip;q=1.0,deflate;q=0.6,identity;q=0.3', 'User-Agent'=>'Faraday v0.9.1'}).
      to_return(body: event_response)

    stub_request(:get, /api.fda.gov\/drug\/label.json/).
      with(:headers => {'Accept'=>'*/*', 'Accept-Encoding'=>'gzip;q=1.0,deflate;q=0.6,identity;q=0.3', 'User-Agent'=>'Faraday v0.9.1'}).
      to_return(body: raw_response)
  end

  describe 'searching api' do
    it 'shows search results' do
      visit medication_path(drug_params)

      expect(page).to have_content('Adderall')
      expect(page).to have_content('DEXTROAMPHETAMINE SULFATE')
      expect(page).to have_content('WARNINGS Serious Cardiovascular Events Sudden Death and Preexisting Structural Cardiac Abnormalities or Other Serious Heart Problems Children and Adolescents Sudden death has been reported in association with CNS stimulant treatment at usual doses in children and adolescents with structural cardiac abnormalities or other serious heart problems.')
    end
  end

  describe 'incomplete results' do
    let(:raw_response) { File.read(File.expand_path('../../fixtures/incomplete_object_response.json', __FILE__)) }
    it 'hides the adverse reactions panel when it is empty' do
      visit medication_path(drug_params)

      expect(page).to  have_selector('.accordion-navigation', count: 3)
    end
  end

  describe 'when no result' do
    let(:raw_response) { File.read(File.expand_path('../../fixtures/error_response.json', __FILE__)) }

    it 'shows error' do
      visit medication_path(drug_params)

      expect(page).to have_content('No matches found!')
    end
  end
end
