package iss.ibf2022.pafworkshopday02app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iss.ibf2022.pafworkshopday02app.model.RSVP;
import iss.ibf2022.pafworkshopday02app.payload.RequestPayload;
import iss.ibf2022.pafworkshopday02app.service.RSVPApiService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path={""})
public class RSVPController {

    @Autowired
    RSVPApiService rsvpApiSvc;

    @GetMapping(path={"/rsvps"})
    public String getAllRecords(Model model) {

        RequestPayload payload = rsvpApiSvc.retrieveAllRecords();
        String body = payload.getRequestBody();
        Integer status = payload.getRequestStatus();

        if (status == 200) {
            List<RSVP> rsvpList = rsvpApiSvc.convertPayloadToCollection(body);
            model.addAttribute("rsvps", rsvpList);

        } else {
            model.addAttribute("error", body);

        }

        model.addAttribute("status", status);
        return "rsvprecord";
    }

    @GetMapping(path={"/rsvp"})
    public String getRecordByName(@RequestParam(name="q") String name, Model model) {

        RequestPayload payload = rsvpApiSvc.retrieveRecordByName(name);
        String body = payload.getRequestBody();
        Integer status = payload.getRequestStatus();

        if (status == 200) {
            RSVP rsvp = rsvpApiSvc.convertPayloadToObject(body);
            model.addAttribute("rsvps", rsvp);

        } else {
            model.addAttribute("error", body);

        }

        model.addAttribute("status", status);
        return "rsvprecord";
    }

    @GetMapping(path={"/rsvp/addnew"})
    public String addNewRecord(Model model) {
        RSVP rsvp = new RSVP();
        model.addAttribute("rsvp", rsvp);
        return "rsvpform";
    }

    @PostMapping(path={"/rsvp/addnew"})
    public String insertRecords(@Valid @ModelAttribute("rsvp") RSVP rsvp, BindingResult result, @RequestBody MultiValueMap<String, String> form,Model model) {

        if (result.hasErrors()) {
            return "rsvpform";
        }

        // check if there is similar rsvp record found
        // verify via email
        RequestPayload allRecordPayload = rsvpApiSvc.retrieveAllRecords();
        List<RSVP> rsvpList = rsvpApiSvc.convertPayloadToCollection(allRecordPayload.getRequestBody());
        Boolean existing = rsvpList.stream().anyMatch(r -> r.getEmail().equalsIgnoreCase(rsvp.getEmail()));

        Integer status = 0;
        String message = "";
        if (existing) {
            RequestPayload payload = rsvpApiSvc.updateRecord(form);
            status = payload.getRequestStatus();
            message = payload.getRequestBody();

        } else {
            RequestPayload payload = rsvpApiSvc.createNewRecord(form);
            status = payload.getRequestStatus();
            message = payload.getRequestBody();

        }

        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "rsvpstatus";
    }

    @GetMapping(path={"/rsvp/{email}"})
    public String updateRecord(@PathVariable String email, Model model) {
        RequestPayload allRecordPayload = rsvpApiSvc.retrieveAllRecords();
        List<RSVP> rsvpList = rsvpApiSvc.convertPayloadToCollection(allRecordPayload.getRequestBody());

        try {
            RSVP rsvp = rsvpList.stream().filter(r -> r.getEmail().equalsIgnoreCase(email)).toList().get(0);

            model.addAttribute("rsvp", rsvp);
            return "rsvpform";

        } catch (Exception ex) {

            RequestPayload payload = new RequestPayload("The email provided is not found", 404);
            model.addAttribute("status", payload.getRequestStatus());
            model.addAttribute("message", payload.getRequestBody());
            return "rsvpstatus";
        }
    }

    @GetMapping(path={"/rsvps/count"})
    public String countTotalRecords(Model model) {

        RequestPayload payload = rsvpApiSvc.countRecords();
        String message = payload.getRequestBody();
        Integer status = payload.getRequestStatus();

        model.addAttribute("status", status);
        model.addAttribute("message", "Total record found = %s".formatted(message));

        return "rsvpstatus";
    }

    @GetMapping(path={"/rsvp/home"})
    public String getHomePage() {

        return "redirect:/rsvps";
    }
    
}
