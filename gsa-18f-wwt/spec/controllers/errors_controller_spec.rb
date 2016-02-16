require 'rails_helper'

describe ErrorsController do
  describe '#show' do

    it 'should set the flash and redirect' do
      get :show
      expect(subject).to redirect_to(root_path)
      expect(flash[:alert]).to eq(I18n.t('generic_error'))
    end
  end
end
