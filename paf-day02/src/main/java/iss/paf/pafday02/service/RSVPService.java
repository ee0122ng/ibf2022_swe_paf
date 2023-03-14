package iss.paf.pafday02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.paf.pafday02.repository.RSVPRepo;

@Service
public class RSVPService {

    @Autowired
    RSVPRepo rsvpRepo;
    
}
