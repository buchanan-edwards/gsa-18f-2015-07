class HomepageController < ApplicationController
  def show
    @search = Search.new
  end
end
