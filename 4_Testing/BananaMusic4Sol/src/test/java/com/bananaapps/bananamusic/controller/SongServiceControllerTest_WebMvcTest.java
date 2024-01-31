package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.config.SpringRepositoryConfig;
import com.bananaapps.bananamusic.config.SpringServicesConfig;
import com.bananaapps.bananamusic.domain.music.Song;
import com.bananaapps.bananamusic.domain.music.SongCategory;
import com.bananaapps.bananamusic.persistence.music.SongRepository;
import com.bananaapps.bananamusic.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(SongServiceController.class)
@Import(SpringServicesConfig.class)
//@Sql(value = "classpath:testing.sql")
//@Sql(value = "classpath:testing_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"dev"})
class SongServiceControllerTest_WebMvcTest {

    @BeforeEach
    public void setUp() {
        List<Song> songs = Arrays.asList(
                new Song(1L, "Diva", "Annie Lennox", "1992-01-04", new BigDecimal("13.99"), SongCategory.POP),
                new Song(2L, "Dream of the Blue Turtles", "Sting", "1985-02-05", new BigDecimal("14.99"), SongCategory.POP),
                new Song(3L, "Trouble is...", "Kenny Wayne Shepherd Band", "1997-08-08", new BigDecimal("14.99"), SongCategory.BLUES),
                new Song(4L, "Lie to Me", "Jonny Lang", "1997-08-26", new BigDecimal("17.97"), SongCategory.BLUES),
                new Song(5L, "Little Earthquakes", "Tori Amos", "1992-01-18", new BigDecimal("14.99"), SongCategory.ALTERNATIVE),
                new Song(6L, "Seal", "Seal", "1991-08-18", new BigDecimal("17.97"), SongCategory.POP)
        );

        Mockito.when(repository.findByKeyword("Fake"))
                .thenReturn(songs);

        Mockito.when(repository.save(Mockito.any(Song.class)))
                .thenAnswer(elem -> {
                    Song aS = (Song) elem.getArguments()[0];
                    aS.setId(100L);
                    return aS;
                });
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SongServiceController controller;

    @MockBean
    private SongRepository repository;


    @Test
    void getSongsByKeywords() throws Exception {
        String keyword = "Fake";

        mvc.perform(get("/catalog/keyword/" + keyword).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].title", hasItem("Diva")));
    }


    @Test
    void createSong() throws Exception {
        Song newSong = new Song("Sonata", "Beethoven", "2023-01-04", new BigDecimal("13.99"), SongCategory.CLASSICAL);

        mvc.perform(post("/catalog")
                        .content(JsonUtil.asJsonString(newSong))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id", is(greaterThanOrEqualTo(1))));

    }
}