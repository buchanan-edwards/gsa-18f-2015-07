require 'rails_helper'

describe SearchesController do
  describe '#show' do
    let(:search_query) { 'some query' }
    let(:page) { '1' }
    let(:search_params) { { search: { generic_name: search_query }, page: page } }
    let(:search) { double 'search' }
    let(:search_results) { double 'search_results' }

    it 'gets search results' do
      expect(Search).to receive(:search_generic_name).with(search_query, page) { search_results }
      expect(Search).to receive(:new).with(search_query) { search }
      get :show, search_params
      expect(assigns(:search_results)).to eq(search_results)
      expect(assigns(:search)).to eq(search)
    end

    context 'no query given' do
      it 'redirects to home page and shows flash alert' do
        get :show, {}
        expect(page).to redirect_to(root_path)
      end
    end
  end
end
