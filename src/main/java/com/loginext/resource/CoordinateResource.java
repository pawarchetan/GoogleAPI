package com.loginext.resource;

import com.loginext.model.Coordinate;
import com.loginext.service.CoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/GoogleAPI")
public class CoordinateResource {

    @Autowired
    private CoordinateService coordinateService;

    @GET
    @RequestMapping("/getAllCoordinates")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coordinate> getAllCoordinates() throws Exception {
        return coordinateService.getAllCoordinates();
    }
}