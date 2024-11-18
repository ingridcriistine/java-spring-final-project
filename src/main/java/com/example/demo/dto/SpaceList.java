package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Space;

public record SpaceList(
    List<Space> Spaces,
    String message
) {}