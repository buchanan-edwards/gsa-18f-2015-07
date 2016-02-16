require 'rails_helper'

describe Interaction do

  describe '.find' do
    let(:medication) { double 'medication' }
    let(:raw_response) { double 'response' }

    let(:interaction) { double 'interaction' }

    subject { described_class }

    before do
      allow(FdaApi).to receive(:find_interactions_by_drug).with(medication) { raw_response }
      allow(Interaction).to receive(:new).with(raw_response) { interaction }
    end

    it 'finds drug from fda api' do
      expect(subject.find(medication)).to eq(interaction)
    end
  end

  describe '#count' do
  end
end
