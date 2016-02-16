package page

import geb.Page

/**
 * User: Janakiram Gollapudi
 * Date: 6/23/15
 */
class HomePage extends Page {
    static at = {
        title.equals("Label and Barcode Excerpt Locator")
    }

    static content = {
        searchButton(required: true){$('input', id:'searchButton')}
        searchBox(required: true) {$('input', name:'term')}
    }
}
