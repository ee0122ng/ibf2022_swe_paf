package iss.ibf2022.pafday07practice.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iss.ibf2022.pafday07practice.model.Feedback;
import iss.ibf2022.pafday07practice.model.Show;
import iss.ibf2022.pafday07practice.service.ShowService;

@Controller
@RequestMapping(path={""})
public class ShowController {

    @Autowired
    ShowService showSvc;
    
    @GetMapping(path={"/shows"})
    public String getShowMainPage(Model model) {

        List<String> shows = showSvc.getUniqueShowName();

        model.addAttribute("shows", shows);

        return "shows";
        
    }

    @GetMapping(path={"/show"})
    public String getShowDetails(@RequestParam String name, Model model) {

        Document showDetails = showSvc.getShowDetailsByName(name);
        Show show = showSvc.convertDocumentToShow(showDetails);

        model.addAttribute("feedback", new Feedback());
        model.addAttribute("show", show);

        return "user-feedback";
    }

    @PostMapping(path={"/show/{id}/comment"})
    public String updateUserFeedback(@ModelAttribute Feedback form, @PathVariable Integer id, Model model) {

        Feedback feedback = form;
        feedback.setShowId(id);

        Boolean updateResult = showSvc.updateFeedback(feedback) > 0L ? true: false;

        model.addAttribute("result", updateResult);
        return "result";

    }
    
}
