class MedicationsController < ApplicationController
  def show
    @medication = Medication.find(params[:id])
    if !@medication.nil? && @medication.openfda.brand_name?
      @interaction = Interaction.find(@medication)
    end
  end

end
