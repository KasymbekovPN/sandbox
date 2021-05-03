package org.kpn.ch16.web;

import org.kpn.ch16.entities.Singer;
import org.kpn.ch16.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/singers")
public class SingerController {

    private static final Logger logger = LoggerFactory.getLogger(SingerController.class);

    @Autowired
    private SingerService singerService;

    @GetMapping
    public String list(Model uiModel){
        logger.info("Listing singers");
        List<Singer> singers = singerService.findAll();
        uiModel.addAttribute("singers", singers);
        logger.info("No. of singers: {}", singers.size());

        return "singers";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model uiModel){
        logger.info("Showing singer");
        uiModel.addAttribute("singer", singerService.findById(id));

        return "show";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable Long id, Model uiModel){
        logger.info("Form updating");
        uiModel.addAttribute("singer", singerService.findById(id));

        return "update";
    }

    @GetMapping("/new")
    public String createForm(Model uiModel){
        logger.info("new");
        uiModel.addAttribute("singer", new Singer());

        return "update";
    }

    @PostMapping
    public String saveSinger(@Valid Singer singer){
        logger.info("Save singer");
        singerService.save(singer);

        return "redirect:/singers/" + singer.getId();
    }
}
