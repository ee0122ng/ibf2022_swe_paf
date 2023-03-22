package iss.ibf2022.pafworkshopday02.restcontroller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iss.ibf2022.pafworkshopday02.model.RSVP;
import iss.ibf2022.pafworkshopday02.service.RSVPService;

@RestController
@RequestMapping(path={"/api"})
public class RSVPRestController {

    @Autowired
    RSVPService rsvpSvc;

    @GetMapping(path={"/rsvps"})
    public ResponseEntity<Object> getAllRecords() {

        try {
            List<RSVP> rsvpList = rsvpSvc.retrieveAllRecords();
            return ResponseEntity.status(HttpStatus.OK).body(rsvpList);

        } catch (Exception ex) {

            // throw new ResourceNotFoundException("No record found");
            String error = "No record found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping(path={"/rsvp"})
    public ResponseEntity<Object> getRecordByName(@RequestParam String q) {

        try {
            RSVP rsvp = rsvpSvc.retrieveRecordByName(q);
            return ResponseEntity.status(HttpStatus.OK).body(rsvp);

        } catch (Exception ex) {

            // throw new ResourceNotFoundException("No record found for %s".formatted(q));
            String error = "No record found for %s".formatted(q);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping(path={"/rsvp"})
    public ResponseEntity<Object> createNewRecord(@RequestBody MultiValueMap<String, String> form) throws ParseException {

        String email = form.getFirst("email");
        Boolean existing = rsvpSvc.findRecordByEmail(email) >= 0;
        Integer updated = 0;
        // if is an existing record, update the details
        if (existing) {
            // populate form details to RSVP details
            // assumption: update are for all matching records found
            RSVP rsvp = rsvpSvc.populateToRSVP(form);
            rsvp.setId(rsvpSvc.findRecordByEmail(email));
            updated = rsvpSvc.updateRecord(rsvp);

            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully. Total updated records = %d".formatted(updated));
        }

        // if is not an existing record, populate a new RSVP object to insert into the database
        RSVP rsvp = rsvpSvc.populateToRSVP(form);
        try {
            Integer generatedId = rsvpSvc.insertNewRecord(rsvp);
            return ResponseEntity.status(HttpStatus.CREATED).body("Record inserted successfully. New Id=%d created".formatted(generatedId));

        } catch (Exception ex) {

            // throw new ResourceNotFoundException("Failed to insert the record");
            String error = "Failed to insert the record";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
            
        }
    }

    // why cannot use requestbody here???
    // form cannot only be submitted as POST / GET method
    // by default, form is submitted as GET through browser
    @PutMapping(path={"/rsvp/{email}"}, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Object> updateRecord(@PathVariable String email, @RequestParam MultiValueMap<String, String> form) throws ParseException {

        Integer matchedId = rsvpSvc.findRecordByEmail(email);

        if (matchedId >= 0) {
            RSVP rsvp = rsvpSvc.populateToRSVP(form);
            rsvp.setId(matchedId);
            Integer updated = rsvpSvc.updateRecord(rsvp);

            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully. Total updated records = %d".formatted(updated));

        } else {

            // throw new ResourceNotFoundException("No record found for email=%s".formatted(email));
            String error = "No record found for email=%s".formatted(email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping(path={"/rsvps/count"})
    public ResponseEntity<Object> countRecords() {

        Integer count = rsvpSvc.retrieveRecordCount();

        return ResponseEntity.status(HttpStatus.CREATED).body(count);
    }
    
}
