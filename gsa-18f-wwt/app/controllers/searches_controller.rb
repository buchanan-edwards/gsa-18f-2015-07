class SearchesController < ApplicationController
  def show
    begin
      @search_results = Search.search_generic_name(search_params, params[:page])
      @search = Search.new(search_params)
    rescue ActionController::ParameterMissing
      redirect_to root_path, alert: 'Please enter search criteria'
    end
  end

  def search_params
    params.require(:search).require(:generic_name)
  end
end
