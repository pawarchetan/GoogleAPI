package com.loginext.dao;

import com.loginext.model.Coordinate;

import java.util.List;

public interface CoordinateDao {
    public List<Coordinate> getAllCoordinates() throws Exception;
}
