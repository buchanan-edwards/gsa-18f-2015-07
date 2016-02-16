package label

import geb.spock.GebSpec
import page.HomePage

class HomePageSpec extends GebSpec {
    def setup() {
    }

    def cleanup() {
    }

    void "Test the home page renders correctly"() {
        when:"I hit application default url"
        go '/LABEL/'

        then:"I should be on homepage"
        at HomePage
    }

    void "Test to search drug label with valid value"() {
        when:'I search drug label and hit search button'
        $('#termText').value('mortin')
        $('#searchButton').click()

        then:"System should found results and display them in datatable"
        waitFor {$('table', id:'labelTable').size() > 0 }
        waitFor { $('#labelTable').find('tbody').find('tr').size() > 0 }
        $('#labelTable').find('tbody').find('tr').find('td').find('a').text() != null
    }

    void "Test to search drug lable with invalid value"() {
        when:'I search drug label with dummy value and hit search button'
        $('#termText').value('sssssssss')
        $('#searchButton').click()

        then:"System should display no results found message"
        waitFor { $('table', id:'labelTable').size() > 0 }
        waitFor { $('#labelTable').find('tbody').find('tr').size() > 0 }
        $('#labelTable').find('tbody').find('tr').find('td').text() == "No results found. Please update your query."
    }
}
