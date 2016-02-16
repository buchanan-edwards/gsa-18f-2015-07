require 'faraday'
require 'json'
require 'active_support'

class DoNotEncoder
  def self.encode(params)
    buffer = ''
    params.each do | k,v |
      buffer << "#{k}=#{v}&"
    end
    return buffer.chop
  end
end

class FdaApi

  def self.get_label(query, page = nil)
    response = connection.get "/drug/label.json", build_params(search: parse_query(query), limit: limit, skip: get_skip_for_page(page))
    parse_response(response.body)
  end

  def self.find_drug_by_id(drug_id)
    response = connection.get "/drug/label.json", build_params(search: "id:#{drug_id}")
    parse_response(response.body)
  end

  def self.find_interactions_by_drug(drug)
    brand_name = drug.openfda.brand_name.first
    terms = brand_name.gsub(/[^0-9a-zA-Z ]/i, '').gsub(/\s+/m, ' ').strip.split(' ')

    query_string = "patient.drug.medicinalproduct:\"#{terms.join("+")}\""
    query_string += "&count=patient.reaction.reactionmeddrapt.exact"
    final_query =  build_params(search: query_string )
    Rails.logger.info("\n\n\n\n\n|#{final_query}|\n\n\n")
    response = connection.get "/drug/event.json", final_query
    parse_response(response.body)
  end

  private

  def self.get_skip_for_page(page)
    page ||= 1
    page = page.to_i

    if page == 1
      0
    else
      (page - 1) * limit
    end
  end

  def self.limit
    20
  end

  def self.parse_query(query)
    terms = query.gsub(/[^0-9a-zA-Z ]/i, '').gsub(/\s+/m, ' ').strip.split(' ')
    generic_name = terms.collect { |x| "generic_name:#{x}" }.join("+AND+")
    brand_name = terms.collect { |x| "brand_name:#{x}" }.join("+AND+")
    "(#{generic_name})+OR+(#{brand_name})"
  end

  def self.build_params(params)
    api_key = ENV['FDA_API_KEY']
    api_key_params = api_key.blank? ? {} : { api_key: api_key }
    api_key_params.merge(params)
  end

  def self.parse_response(response)
    JSON.parse(response, symbolize_names: true)
  end

  def self.connection
    @connection ||= Faraday.new(url: "https://api.fda.gov") do |faraday|
      faraday.request  :url_encoded             # form-encode POST params
      faraday.response :logger
      faraday.adapter  Faraday.default_adapter  # make requests with Net::HTTP
      faraday.options.params_encoder = DoNotEncoder
    end
  end
end
