package ee.agy.prolink;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OverlayService {
    @POST("/api/new-track")
    Call<NewTrackResponse> newTrack(@Body NewTrackBody body);

    @POST("/api/add-image")
    Call<NewTrackResponse> addImage(@Body AddImageBody body);
}
