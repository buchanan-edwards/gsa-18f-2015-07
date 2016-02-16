require 'rails_helper'

describe MedicationsController do
  describe '#show' do
    let(:medication) { double 'medication' }
    let(:drug_id) { 'drug-id' }
    let(:drug_params) { { id: drug_id } }

    before do
      allow(Medication).to receive(:find).with(drug_id) { medication }
    end

    it 'gets drug info' do
      get :show, drug_params
      expect(assigns(:medication)).to eq(medication)
    end
  end
end
