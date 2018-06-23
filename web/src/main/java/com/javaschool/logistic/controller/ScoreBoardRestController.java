package com.javaschool.logistic.controller;

import com.javaschool.logistic.models.JsonResponse;
import com.javaschool.logistic.service.api.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ScoreBoardRestController {

    private ScoreboardService scoreboardService;

    @Autowired
    public ScoreBoardRestController(ScoreboardService scoreboardService) {
        this.scoreboardService = scoreboardService;
    }

    @GetMapping(value = "/test")
    public @ResponseBody
    JsonResponse getTest(){
        return scoreboardService.getResponse();
    }


}
