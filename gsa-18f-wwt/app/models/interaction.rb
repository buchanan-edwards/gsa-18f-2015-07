class Interaction < Hashie::Mash
  def self.find(drug)
    new(FdaApi.find_interactions_by_drug(drug))
  end

  def end_number
    9
  end

  def chart_format
    @chart_format ||= self.results.collect { |k| [k.term, k[:count]] }
  end

  def total_count
    @total_count ||= self.results.inject(0){|num,item| num + item[:count]}
  end

  def pie_count
    @pie_count ||= self.results[0..end_number].inject(0){ |num, item| num + item[:count]}
  end

  def pie_format
    chart_format[0..end_number] << ["Other", total_count - pie_count]
  end
end

