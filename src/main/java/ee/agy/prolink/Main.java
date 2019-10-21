package ee.agy.prolink;

import org.deepsymmetry.beatlink.*;
import org.deepsymmetry.beatlink.data.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost:3000")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

            final OverlayService overlay = retrofit.create(OverlayService.class);

            VirtualCdj.getInstance().start();
            CrateDigger.getInstance().start();
            ArtFinder.getInstance().start();

            ArtFinder.getInstance().addAlbumArtListener(new AlbumArtListener() {
                public void albumArtChanged(AlbumArtUpdate albumArtUpdate) {
                    if(albumArtUpdate.art == null)
                        return;

                    System.out.println("Album art changed");

                    String encodedString = Base64.getEncoder().encodeToString(albumArtUpdate.art.getRawBytes().array());
                    try {
                        overlay.addImage(new AddImageBody(albumArtUpdate.player, encodedString)).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            MetadataFinder.getInstance().addTrackMetadataListener(new TrackMetadataListener() {
                public void metadataChanged(TrackMetadataUpdate trackMetadataUpdate) {
                    System.out.println(trackMetadataUpdate);
                    TrackMetadata metadata = trackMetadataUpdate.metadata;
                    if(metadata == null)
                        return;

                    try {
                        overlay.newTrack(new NewTrackBody(trackMetadataUpdate.player, metadata.getArtist().label, metadata.getTitle()))
                            .execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
