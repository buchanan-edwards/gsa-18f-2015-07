require 'rails_helper'

describe ApplicationHelper do
  module ApplicationHelper
    def panel_number
      @panel_number
    end

    def set_panel_number(value)
      @panel_number = value
    end
  end
  describe 'render_panel' do
    it 'should increment panel count' do
      helper.render_panel(field_name: 'name', field_data: ['data'])
      expect(helper.panel_number).to eq(1)
      helper.render_panel(field_name: 'name', field_data: ['data'])
      expect(helper.panel_number).to eq(2)
    end
  end

  describe 'panel_id' do
    it '' do
      panel_number = 4
      helper.set_panel_number(panel_number)

      expect(helper.panel_id).to eq("panel#{panel_number}")

    end

  end
end
