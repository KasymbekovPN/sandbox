package org.kpn.ch13.mockito;

import org.junit.Before;
import org.junit.Test;
import org.kpn.ch13.SingerController;
import org.kpn.ch13.entities.Singer;
import org.kpn.ch13.entities.Singers;
import org.kpn.ch13.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SingerControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(SingerControllerTest.class);

    private final List<Singer> singers = new ArrayList<>();

    @Before
    public void initSingers(){
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singers.add(singer);
    }

    @Test
    public void testList(){
        SingerService singerService = mock(SingerService.class);
        logger.info("singerService: {}", singerService);
        logger.info("singerService class: {}", singerService.getClass());
        when(singerService.findAll()).thenReturn(singers);

        SingerController singerController = new SingerController();

        ReflectionTestUtils.setField(singerController, "singerService", singerService);

        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("singers", singerController.listData());

        Singers modelSingers = (Singers) uiModel.get("singers");
        logger.info("Singers: {}", singers);

        assertEquals(1, modelSingers.getSingers().size());
    }

    @Test
    public void testCreate(){
        Singer newSinger = new Singer();
        newSinger.setId(999L);
        newSinger.setFirstName("Stevie");
        newSinger.setLastName("Vaughan");

        SingerService singerService = mock(SingerService.class);
        when(singerService.save(newSinger)).thenAnswer(invocationOnMock -> {
            singers.add(newSinger);
            return newSinger;
        });

        SingerController singerController = new SingerController();

        ReflectionTestUtils.setField(singerController, "singerService", singerService);

        Singer singer = singerController.create(newSinger);
        assertEquals(Long.valueOf(999L), singer.getId());
        assertEquals("Stevie", singer.getFirstName());
        assertEquals("Vaughan", singer.getLastName());

        assertEquals(2, singers.size());
    }
}
