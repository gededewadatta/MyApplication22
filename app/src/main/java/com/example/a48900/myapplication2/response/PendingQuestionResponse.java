package com.example.a48900.myapplication2.response;


import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class PendingQuestionResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    public String userName;
    @JsonProperty("questions")
    public String questions;
    @JsonProperty("answer1")
    public String answer1;
    @JsonProperty("answer2")
    public String answer2;
    @JsonProperty("answer3")
    public String answer3;
    @JsonProperty("answer4")
    public String answer4;
    @JsonProperty("answer5")
    public String answer5;
    @JsonProperty("correctanswer")
    public String correctAnswer;
    @JsonProperty("createddate")
    public Date createdDate;
    @JsonProperty("createdby")
    public String createdBy;
    @JsonProperty("competency")
    public String competency;
    @JsonProperty("level")
    public String level;
    @JsonProperty("answertype")
    public String answerType;

    public String answerType() {

        return null;
    }
}

