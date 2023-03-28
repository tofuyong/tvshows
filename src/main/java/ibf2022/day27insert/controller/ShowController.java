package ibf2022.day27insert.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.day27insert.model.Review;
import ibf2022.day27insert.model.Show;
import ibf2022.day27insert.repository.ShowRepository;

@Controller
@RequestMapping
public class ShowController {
    
    private static final Logger logger = LoggerFactory.getLogger(ShowController.class);

    @Autowired
    ShowRepository showRepo;

    @GetMapping(path = "/allshows")
    public String listShows(Model model) {
        List<String> showNames = showRepo.findAllShows();
        model.addAttribute("showNames", showNames);
        return "index";
    }

    @GetMapping(path = "/{show}")
    public String displayShow (@PathVariable String show, Model model) {
        Show selectedShow = showRepo.findShowByName(show);
        model.addAttribute("show", selectedShow);
        model.addAttribute("review", new Review()); //empty review object
        return "updateshow";
    }

    @PostMapping(path = "/update")
    public String postForm (Review reviewForm, Model model) {
        // in the background model adds reviewForm 
        logger.info(">>>> Review form received by: " + reviewForm.toString());
        logger.info("> " + model.asMap().toString());
        showRepo.insertReview(reviewForm);
        return "updated";
    }

    
}
