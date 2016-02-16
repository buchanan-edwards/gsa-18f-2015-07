require 'rails_helper'

describe HomepageController do
  describe '#show' do
    let(:search) { double 'search' }

    before do
      allow(Search).to receive(:new) { search }
    end

    it 'initializes new search query' do
      get :show
      expect(assigns(:search)).to eq(search)
    end
  end
end
