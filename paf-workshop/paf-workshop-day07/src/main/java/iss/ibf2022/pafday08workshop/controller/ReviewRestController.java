package iss.ibf2022.pafday08workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iss.ibf2022.pafday08workshop.model.Review;
import iss.ibf2022.pafday08workshop.payload.HistoricalCommentPayload;
import iss.ibf2022.pafday08workshop.payload.LatestCommentPayload;
import iss.ibf2022.pafday08workshop.payload.UpdatePayload;
import iss.ibf2022.pafday08workshop.service.ReviewService;

@RestController
@RequestMapping()
public class ReviewRestController {

    @Autowired
    ReviewService reviewSvc;

    @PostMapping(path={"/review"})
    public ResponseEntity<Object> submitReview(@RequestBody MultiValueMap<String, String> form ) {

        try {
            Review review = reviewSvc.insertReview(form);
            return ResponseEntity.status(HttpStatus.CREATED).body(review);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());

        }
        
    }

    @PutMapping(path={"/review/{review_id}"})
    public ResponseEntity<Object> updateReview(@PathVariable String review_id, @RequestBody String updateinfo) {

        try {
            UpdatePayload payload = reviewSvc.updateReview(review_id, updateinfo);
            return ResponseEntity.status(HttpStatus.OK).body(payload);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());

        } 
        
    }

    @GetMapping(path={"/review/{review_id}"})
    public ResponseEntity<Object> getLatestComment(@PathVariable String review_id) {

        try{
            LatestCommentPayload payload = reviewSvc.retrieveLatestComment(review_id);
            return ResponseEntity.status(HttpStatus.OK).body(payload);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
        }
    }

    @GetMapping(path={"/review/{review_id}/history"})
    public ResponseEntity<Object> getHistoricalComment(@PathVariable String review_id) {

        try {
            HistoricalCommentPayload payload = reviewSvc.retrieveHistoricalComment(review_id);
            return ResponseEntity.status(HttpStatus.OK).body(payload);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
        }
    }
    
}
