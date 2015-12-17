package com.loginext.service;

import com.loginext.dao.CoordinateDao;
import com.loginext.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoordinateServiceImpl implements CoordinateService {

    @Autowired
    private CoordinateDao coordinateDao;

    @Override
    public List<Coordinate> getAllCoordinates() throws Exception {
        return coordinateDao.getAllCoordinates();
    }
}
