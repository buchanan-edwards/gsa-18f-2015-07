class SearchResults
  attr_reader :query, :fda_response

  delegate :results, :error, :error?, to: :fda_response

  def initialize(raw_response, query = nil)
    @raw_response = raw_response
    @query = query
    @fda_response = Hashie::Mash.new(@raw_response)
  end

  def meta
    @meta ||= @fda_response.meta? ? @fda_response.meta : Hashie::Mash.new({ results: { skip: 0, total: 0, limit: 20 } })
  end

  def error_message
    @fda_response.error.message if error?
  end

  def total_results
    meta.results.total
  end

  def total_pages
    (total_results.to_f / limit_value).ceil
  end

  def current_page
    meta.results.skip / limit_value + 1
  end

  def limit_value
    meta.results.limit
  end
end
