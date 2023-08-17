package com.juaracoding;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestAPITMDB {
    // Membuat variabel baseURL untuk menampung base url api the movie db
    String baseUrl = "https://api.themoviedb.org";
    // Membuat variabel myToken untuk menampung token api the movie db
    String myToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNzhmODI5NThkNjI1MDE2OGRlYjA2ZDIzNmYwMTQ5NCIsInN1YiI6IjY0ZGI3MWRiZDEwMGI2MDEzOTVmMTlhNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-qzIjtFndEkaU76zHC6wzsBoEvBZ3HEjK8MZ7T0b_Do";
    // Membuat method endPoint untuk menjalankan base url api + endpoint sesuai yang diuji dari the movie db
    String endPoint(String endPoint) {
        return baseUrl + endPoint;
    }

    // Mmebuat method testGetNowPlayingStatusCode untuk menguji respon status code dari api Now Playing
    @Test
    public void testGetNowPlayingStatusCode() {
        given()
                .header("Authorization", "Bearer " + myToken) // set header authorization dengan token
                .when() // saat
                .get(endPoint("/3/movie/now_playing")) // menuju api Now Playing
                .then() // maka
                .statusCode(200); // cek respon status code = 200
    }
    // Membuat method testValidasiIdAndLanguageMoviePageOneMoviePopular untuk validasi respon body dari api MoviePopular halaman 1 id 976573 language en
    @Test
    public void testValidasiIdAndLanguageNowPlayingPageOneNowPlaying() {
        given()
                .header("Authorization", "Bearer " + myToken) // set header authorization dengan token
                .queryParams("language", "en-US").queryParams("page", "1") // set params url dengan language en-US dan Page 1
                .when() // saat
                .get(endPoint("/3/movie/now_playing")) // menuju api Now Playing
                .then() // maka
                .body("results[0].id", equalTo(976573))// cek respon body id dengan 976573
                .body("results[0].original_language", equalTo("en")); // cek respon body original language dengan en
    }
    // Mmebuat method testGetMoviePopularStatusCode untuk menguji respon status code dari api Movie Popular
    @Test
    public void testGetMoviePopularStatusCode() {
        given()
                .header("Authorization", "Bearer " + myToken) // set header authorization dengan token
                .when() // saat
                .get(endPoint("/3/movie/popular")) // menuju api Movie Popular
                .then() // maka
                .statusCode(200); // cek respon status code = 200
    }
    // Membuat method testValidasiIdAndLanguageMoviePageOneMoviePopular untuk validasi respon body dari api MoviePopular halaman 1 id 569094 language en
    @Test
    public void testValidasiIdAndLanguageMoviePageOneMoviePopular() {
        given()
                .header("Authorization", "Bearer " + myToken) // set header authorization dengan token
                .queryParams("language", "en-US").queryParams("page", "1") // set params url dengan language en-US dan Page 1
                .when() // saat
                .get(endPoint("/3/movie/popular")) // menuju api Movie Popular
                .then() // maka
                .body("results[0].id", equalTo(569094)) // cek respon body id dengan 569094
                .body("results[0].original_language", equalTo("en")); // cek respon body original language dengan en
    }
    // Mmebuat method testPostAddRatingMovieStatusCode untuk menguji respon status code dari api PostAddRatingMovie
    @Test
    public void testPostAddRatingMovieStatusCode() {
        JSONObject request = new JSONObject(); // Membuat variabel request dengan type data object dari kelas JSONObject untuk membuat request
        request.put("value", "8.50"); // set request dengan Map yaitu key = value dan value = 8.50
        given()
                .header("Authorization", "Bearer " + myToken) // set header authorization dengan token
                .header("Content-Type", "application/json") // set header content type dengan application/json
                .body(request.toJSONString()) // set body dengan request yang diubah menjadi JSONString
                .when() // saat
                .post(endPoint("/3/movie/678512/rating"))// menuju api post Add Rating
                .then() // maka
                .statusCode(201); // cek status code = 200
    }

    // Membuat method testValidasiResponseBodyPostAddRating untuk validasi respon body dari api Add Rating
    @Test
    public void testValidasiResponseBodyPostAddRating() {
        JSONObject request = new JSONObject(); // Membuat variabel request dengan type data object dari kelas JSONObject untuk membuat request
        request.put("value", "8.50"); // set request dengan Map yaitu key = value dan value = 8.50
        given()
                .header("Authorization", "Bearer " + myToken) // set header authorization dengan token
                .header("Content-Type", "application/json") // set header content type dengan application/json
                .body(request.toJSONString()) // set body dengan request yang diubah menjadi JSONString
                .when() // saat
                .post(endPoint("/3/movie/678512/rating")) // menuju api post Add Rating
                .then() // maka
                .body("success",equalTo(true)) // cek respon body success = true
                .body("status_code",equalTo(12)) // cek respon body status code = 12
                .body("status_message",equalTo("The item/record was updated successfully.")); // cek respon body statu message = The item/record was updated successfully.
    }
}

