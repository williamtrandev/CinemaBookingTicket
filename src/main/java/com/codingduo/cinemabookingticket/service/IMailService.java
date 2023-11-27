package com.codingduo.cinemabookingticket.service;

public interface IMailService {
    void sendHtmlEmail(String numSeat, String infoSeat, String infoCombo, String to);
}
