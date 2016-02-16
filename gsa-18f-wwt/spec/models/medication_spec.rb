require 'rails_helper'

describe Medication do
  describe '.find' do
    let(:drug_id) { double 'drug id' }
    let(:medication) { double 'medication' }
    let(:search_results) { double 'search results', results: [ medication_mash ] }
    let(:medication_mash) { double 'medication mash' }

    subject { described_class }

    before do
      allow(FdaApi).to receive(:find_drug_by_id).with(drug_id) { medication }
      allow(SearchResults).to receive(:new).with(medication) { search_results }
    end

    it 'finds drug from fda api' do
      expect(subject.find(drug_id)).to eq(medication_mash)
    end

    describe "not found" do
      let(:search_results) { double 'search results', results: nil}
      it 'returns nil if not found' do
        expect(subject.find(drug_id)).to be_nil
      end
    end

  end
end
