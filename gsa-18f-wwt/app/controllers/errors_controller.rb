class ErrorsController < ApplicationController
  def show
    flash[:alert] = t('generic_error')
    redirect_to root_path
  end
end

