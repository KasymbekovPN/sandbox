package org.kpn.ch13;

import org.kpn.ch13.entities.Singer;
import org.kpn.ch13.entities.Singers;
import org.kpn.ch13.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/singer")
public class SingerController {

    private static final Logger logger = LoggerFactory.getLogger(SingerController.class);

    @Autowired
    private SingerService singerService;

    @RequestMapping(value = "/listdata", method = RequestMethod.GET)
    @ResponseBody
    public Singers listData(){
        logger.info("Method listData id called");
        return new Singers(singerService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Singer findSingerById(@PathVariable("id") Long id){
        return singerService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Singer create(@RequestBody Singer singer){
        logger.info("Create singer: {}", singer);
        singerService.save(singer);
        logger.info("Singer created successfully with info : {}", singer);

        return singer;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Singer singer,
                       @PathVariable("id") Long id){
        logger.info("Updating singer: {}", singer);
        singerService.save(singer);
        logger.info("Singer updated successfully with info: {}", singer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id){
        logger.info("Deleting singer with id: {}", singerService);
        Singer singer = singerService.findById(id);
        singerService.delete(singer);
        logger.info("Singer deleted successfully");
    }
}
