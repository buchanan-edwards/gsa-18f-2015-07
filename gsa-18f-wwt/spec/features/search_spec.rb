require 'rails_helper'

describe 'Searching for a drug' do
  let(:raw_response) { File.read(File.expand_path('../../fixtures/adderall.json', __FILE__)) }
  let(:event_response) { File.read(File.expand_path('../../fixtures/event_response.json', __FILE__)) }
  let(:query) { 'amphetamine' }
  let(:current_page) { '1' }
  let(:search_params) { { search: { generic_name: query }, page: current_page } }

  before do
    stub_request(:get, /api.fda.gov\/drug\/event.json/).
      with(:headers => {'Accept'=>'*/*', 'Accept-Encoding'=>'gzip;q=1.0,deflate;q=0.6,identity;q=0.3', 'User-Agent'=>'Faraday v0.9.1'}).
      to_return(body: event_response)

    stub_request(:get, /api.fda.gov\/drug\/label.json/).
      with(:headers => {'Accept'=>'*/*', 'Accept-Encoding'=>'gzip;q=1.0,deflate;q=0.6,identity;q=0.3', 'User-Agent'=>'Faraday v0.9.1'}).
      to_return(body: raw_response)
  end

  describe 'searching api' do
    let(:total_results) { 29 }

    it 'shows search results' do
      visit searches_path(search_params)

      expect(page).to have_content("#{total_results} Results found for \"#{query}\"")
      expect(page).to have_content('DEXTROAMPHETAMINE SULFATE')
    end

    it 'shows the manufacturer' do
      visit searches_path(search_params)

      expect(page).to have_content("Independence Pharmaceuticals, LLC")
    end

    describe 'clicking a result' do
      it 'goes to medication detail page' do
        visit searches_path(search_params)

        click_link 'Procentra'

        expect(page).to have_content('Procentra')
        expect(page).to have_content('Independence Pharmaceuticals, LLC')
        expect(page).to have_content('Central Nervous System Stimulant [EPC]')
      end

    end

    describe 'allows you to search again' do
      it 'shows search results' do
        visit searches_path(search_params)

        fill_in 'Search', with: 'adderall'
        click_button 'search-button'

        expect(page).to have_content('Results found for "adderall"')
      end
      it 'presets the search text to previous search' do
        visit searches_path(search_params)

        expect(page).to have_field('Search', with: query)
      end
    end
  end

  describe 'when no results' do
    let(:raw_response) { File.read(File.expand_path('../../fixtures/error_response.json', __FILE__)) }
    let(:total_results) { 0 }

    it 'shows error' do
      visit searches_path(search_params)

      expect(page).to have_content("#{total_results} Results found for \"#{query}\"")
      expect(page).to have_content('No matches found!')
    end
  end

  describe 'clicking logo' do
    it 'goes to homepage' do
      visit searches_path(search_params)

      click_link 'GSA Prototype'

      expect(page.current_path).to eq(root_path)
    end
  end
end
