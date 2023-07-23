package org.example;

import org.example.models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTests {
    IMDBApiClient imdbApiClient = new IMDBApiClient();
    final String API_KEY = "k_83pv5z04";

    @Test
    public void searchTest1() throws IOException {
        SearchPayload searchPayload = imdbApiClient.searchService.searchMovie(API_KEY, "inception 2010").execute().body();
        Assert.assertNotNull(searchPayload, "Search payload is null");
        Assert.assertEquals(searchPayload.results.get(0).title, "Inception", "Incorrect title");
        Assert.assertTrue(searchPayload.results.get(0).description.contains("2010"), "Incorrect description");
    }

    @Test
    public void emptyTest() throws IOException {
        SearchPayload searchPayload = imdbApiClient.searchService.searchMovie(API_KEY, "").execute().body();
        Assert.assertNotNull(searchPayload, "Search payload is null");
        Assert.assertTrue(searchPayload.errorMessage.contains("Invalid request."), "Incorrect error message");
    }

    @Test
    public void searchTest2() throws IOException {
        SearchPayload searchPayload = imdbApiClient.searchService.searchMovie(API_KEY, "batman 1994").execute().body();
        Assert.assertNotNull(searchPayload, "Search payload is null");
        Assert.assertTrue(searchPayload.results.get(0).title.toLowerCase().contains("batman"), "Title does not contain batman");
        Assert.assertTrue(searchPayload.results.get(0).description.contains("1994"), "Description does not contain 1994");
        Assert.assertFalse(searchPayload.results.get(0).title.toLowerCase().contains("1994"), "Title contains 1994");
    }

    @Test
    public void advancedSearchTest() throws IOException {
        SearchPayload searchPayloadTwoTypes = imdbApiClient.searchService.searchMovieAdvanced(API_KEY, "batman", "tv_movie,tv_series", null).execute().body();
        SearchPayload searchPayloadOneType = imdbApiClient.searchService.searchMovieAdvanced(API_KEY, "batman", "tv_movie", null).execute().body();
        Assert.assertNotNull(searchPayloadOneType, "Search payload is null");
        Assert.assertNotNull(searchPayloadTwoTypes, "Search payload is null");
        Assert.assertTrue(searchPayloadTwoTypes.results.size() > searchPayloadOneType.results.size(), "Incorrect results size");
    }

}
