package de.kanoune.spring.boot.kafka.twitter.to.kafka.service.runner;

import org.springframework.stereotype.Component;
import twitter4j.TwitterException;


public interface StreamRunner {

    void run() throws TwitterException;
}
