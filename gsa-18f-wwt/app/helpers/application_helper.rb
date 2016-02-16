module ApplicationHelper
  def display_field(field)
    field.join(', ') if field
  end

  def render_panel(field_name: '', field_data: '', extra_class: '')
    increment
    render(partial: 'shared/panel', locals: { field_name: field_name, field_data: field_data, extra_class: extra_class })
  end

  def panel_id
    "panel#{@panel_number}"
  end

  def increment
    @panel_number = (@panel_number || 0) + 1
  end
end
