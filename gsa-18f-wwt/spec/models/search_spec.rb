require 'rails_helper'

describe Search do
  describe 'initialize' do

    it 'should default generic name to blank' do
      search = Search.new

      expect(search.generic_name).to eq('')
    end

    it 'should default generic_name' do
      expected_name = 'generic name'
      search = Search.new(expected_name)

      expect(search.generic_name).to eq(expected_name)
    end
  end

  describe '.search_generic_name' do
    let(:query) { double 'query' }
    let(:page) { double 'page' }
    let(:results) { double 'results' }
    let(:search_results) { double 'search_results' }

    subject { described_class }

    before do
      allow(FdaApi).to receive(:get_label).with(query, page) { results }
      allow(SearchResults).to receive(:new).with(results, query) { search_results }
    end

    it 'gets results from FDA api' do
      expect(subject.search_generic_name(query, page)).to eq(search_results)
    end
  end
end
