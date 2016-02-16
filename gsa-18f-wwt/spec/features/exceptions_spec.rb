require 'rails_helper'

describe 'Catch errors' do

  it 'should redirect to search screen' do
    visit 'some_bad_path'

    error_message = I18n.t('generic_error')
    welcome_text =  I18n.t('find_a_medication')
    expect(page).to have_content(error_message)
    expect(page).to have_content(welcome_text)
  end

end
