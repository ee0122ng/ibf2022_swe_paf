package iss.ibf2022.pafworkshopday02.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import iss.ibf2022.pafworkshopday02.model.RSVP;
import iss.ibf2022.pafworkshopday02.repository.RSVPRepository;

@Service
public class RSVPService {

    @Autowired
    RSVPRepository rsvpRepo;

    public List<RSVP> retrieveAllRecords() {

        return rsvpRepo.getAllRsvp();
    }
    
    public List<RSVP> retrieveRecordByName(String name) {

        return rsvpRepo.getRsvpByName(name);
    }

    public Integer insertNewRecord(RSVP rsvp) {

        return rsvpRepo.addNewRecord(rsvp);
    }

    public Integer updateRecord(RSVP rsvp) {

        return rsvpRepo.updateRecord(rsvp);
    }

    public Integer retrieveRecordCount() {

        return rsvpRepo.countRecord();
    }

    public RSVP populateToRSVP(MultiValueMap<String, String> form) throws ParseException {

        RSVP rsvp = new RSVP();

        rsvp.setCustomerName(form.getFirst("customerName"));
        rsvp.setPhone(form.getFirst("phone"));
        rsvp.setEmail(form.getFirst("email"));
        rsvp.setComments(form.getFirst("comments"));
        if (null != form.getFirst("confirmationDate")) {
            rsvp.setConfirmationDate(java.sql.Date.valueOf(form.getFirst("confirmationDate")));
        }
        
        return rsvp;
    }

    public Integer findRecordByEmail(String email) {

        // assume only one record found per email
        List<RSVP> matchedRecord = retrieveAllRecords().stream().filter(rsvp -> rsvp.getEmail().equalsIgnoreCase(email)).toList();
        
        if (matchedRecord.size() > 0) {
            Integer returnedId = matchedRecord.get(0).getId();
            return returnedId;
        } else {
            return -1000;
        }
    }
}
