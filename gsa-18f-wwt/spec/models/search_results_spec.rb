require 'rails_helper'

describe SearchResults do
  let(:query) { double 'query' }
  let(:raw_response) { { results: results, meta: meta } }
  let(:meta) { double 'meta', results: meta_results }
  let(:skip) { 0 }
  let(:limit) { 20 }
  let(:meta_results) { double 'meta results', skip: skip, limit: limit, total: total }
  let(:total) { 100 }
  let(:results) { double 'search results from api' }
  let(:fda_response) { double 'fda response', error?: error?, error: fda_error, meta: meta, meta?: meta?, results: results }
  let(:fda_error) { nil }
  let(:error?) { false }
  let(:meta?) { true }

  before do
    allow(Hashie::Mash).to receive(:new).with(raw_response) { fda_response }
  end

  subject { described_class.new(raw_response, query) }

  describe '#query' do
    it 'returns original query' do
      expect(subject.query).to eq(query)
    end
  end

  describe '#results' do
    it 'returns results' do
      expect(subject.results).to eq(results)
    end
  end

  describe '#total_results' do
    it 'gets total results' do
      expect(subject.total_results).to eq(total)
    end
  end

  describe '#error_message' do
    it 'is blank' do
      expect(subject.error_message).to be_blank
    end
  end

  describe '#error' do
    before do
      allow(Hashie::Mash).to receive(:new).with({ results: { skip: 0, total: 0, limit: 20 } }) { meta }
    end

    let(:total) { 0 }
    let(:meta?) { false }
    let(:error?) { true }
    let(:fda_error) { double 'fda error', message: error_message }
    let(:error_message) { double 'error message' }
    let(:raw_response) { { error: fda_error } }

    it 'sets total_results to 0' do
      expect(subject.total_results).to eq(0)
    end

    it 'has an error' do
      expect(subject.error?).to eq(true)
    end

    it 'error message should be set' do
      expect(subject.error_message).to eq(error_message)
    end

    it 'has 0 total pages' do
      expect(subject.total_pages).to eq(0)
    end
  end

  describe '#total_pages' do
    let(:total) { 3301 }

    it 'has 166 pages' do
      expect(subject.total_pages).to eq(166)
    end
  end

  describe '#current_page' do
    context 'second_page' do
      let(:skip) { 20 }

      it 'gets current page' do
        expect(subject.current_page).to eq(2)
      end
    end

    context 'first_page' do
      let(:skip) { 0 }

      it 'gets current page' do
        expect(subject.current_page).to eq(1)
      end
    end
  end
end
