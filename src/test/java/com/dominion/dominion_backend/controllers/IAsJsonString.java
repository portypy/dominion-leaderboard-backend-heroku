package com.dominion.dominion_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface IAsJsonString {

     static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
