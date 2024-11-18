package com.example.demo.dto;

import java.util.List;


public record SpaceList(
    List<SpaceReturn> Spaces,
    String message
) {}