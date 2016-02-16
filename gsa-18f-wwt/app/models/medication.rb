class Medication
  def self.find(drug_id)
    search_results = SearchResults.new(FdaApi.find_drug_by_id(drug_id))
    search_results.results.first if search_results.results
  end
end
